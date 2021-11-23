package client;

import client.controller.Chat;
import client.observer.Observable;
import client.stream.ClientThread;
import client.view.MemberPanel;

import java.io.*;
import java.net.*;

public class Client extends Observable {
	private Chat chat;
	private ClientThread clientThread;

	public Client(Chat chat, String hostName, int portNumber) throws IOException {
		this.chat = chat;
		Socket socket = new Socket(hostName, portNumber);
		this.clientThread = new ClientThread(socket, this);
	}

	public void start() {
		this.clientThread.start();
	}

	public void onServerReply(ClientThread clientThread, String reply) {
		String[] args = reply.split(":");
		String cmd = args[0];
		switch (cmd) {
			case "+OK_IDENTIFIER": {
				this.getChannels();
				this.getMembers();
				this.setUser(reply);
				System.out.println(reply);
				break;
			}
			case "+OK_LISTER": {
				this.displayChannels(reply);
				System.out.println(reply);
				break;
			}
			case "+OK_REJOINDRE": {
				this.getMembers();
				System.out.println(reply);
				break;
			}
			case "NOTIFIER", "+OK_MESSAGE": {
				this.displayMessage(reply);
				System.out.println(reply);
				break;
			}
			case "+OK_MEMBRES": {
				this.displayMembers(reply);
				System.out.println(reply);
				break;
			}
			case "CONNEXION": {

				System.out.println(reply);
				break;
			}
			default: {
				System.out.println(reply);
			}
		}
	}

	public void onServerConnected(ClientThread clientThread) {
	}

	public void onServerDisconnected(ClientThread clientThread) {
	}

	private void getChannels() {
		this.clientThread.echo("LISTER");
	}

	private void getMembers() {
		this.clientThread.echo("MEMBRES");
	}

	private void displayMembers(String reply) {
		this.chat.resetMembers();

		reply = reply.substring(reply.indexOf(" ") + 1);
		String[] members = reply.split(", ");
		for (String member : members) {
			this.chat.displayMember(member);
		}
	}

	private void displayChannels(String reply) {
		reply = reply.substring(reply.indexOf(" ") + 1);
		String[] split1 = reply.split(", ");
		for (String s : split1) {
			String[] split2 = s.split("\\(");
			this.chat.displayChannel(split2[0]);
		}
	}

	private void displayMessage(String reply) {
		String[] data = reply.split(" ");
		boolean isCannal = data[1].equals("CANNAL") ? true : false;
		String name = data[2];
		String user = data[4];
		data = reply.split("<<");
		String message = data[1].substring(0, data[1].length() - 2);
		this.chat.displayMessage(isCannal, name, user, message);
	}

	public void sendMessage(String message) {
		System.out.println(message);
		this.clientThread.echo("MESSAGE " + message);
	}

	public void connect(String username, String password) {
		this.clientThread.echo("IDENTIFIER " + username + " " + password);
	}

	public void selectChannel(String channel) {
		this.clientThread.echo("SORTIR");
		this.clientThread.echo("DECONNEXION");
		this.clientThread.echo("REJOINDRE " + channel);
	}

	public void selectMember(String member) {
		this.clientThread.echo("SORTIR");
		this.clientThread.echo("DECONNEXION");
		this.clientThread.echo("CONNEXION " + member);
	}

	private void setUser(String reply) {
		this.chat.blockAuthenticate();
		String user = reply.split(" ")[1];
		this.chat.setUser(user);
	}

	public void addObserver(MemberPanel memberPanel) {
		super.addObserver(memberPanel);
	}
}
