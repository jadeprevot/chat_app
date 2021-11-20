package stream.client;

import controller.Chat;
import stream.server.Server;

import java.io.*;
import java.net.*;

public class Client {
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
			case "+OK_IDENTIFIER":
				this.getChannels();
				System.out.println(reply);
				break;
			case "+OK_QUITTER":

				System.out.println(reply);
				break;
			case "+OK_LISTER":
				this.displayChannels(reply);
				System.out.println(reply);
				break;
			case "+OK_REJOINDRE":
				this.getMembers();
				System.out.println(reply);
				break;
			case "SORTIR":

				System.out.println(reply);
				break;
			case "NOTIFIER":
				this.chat.displayReply(reply);
				System.out.println(reply);
				break;
			case "+OK_MESSAGE":

				System.out.println(reply);
				break;
			case "+OK_MEMBRES":
				this.displayMembers(reply);
				System.out.println(reply);
				break;
			case "CONNEXION":

				System.out.println(reply);
				break;
			case "DECONNEXION":

				System.out.println(reply);
				break;
			default:
				System.out.println(reply);
		}
	}

	private void displayMembers(String reply) {
		reply = reply.substring(reply.indexOf(" ") + 1);
		String[] members = reply.split(", ");
		for (String member : members) {
			this.chat.displayMember(member);
		}
	}

	private void getMembers() {
		this.clientThread.echo("MEMBRES");
	}

	private void displayChannels(String reply) {
		reply = reply.substring(reply.indexOf(" ") + 1);
		String[] split1 = reply.split(", ");
		for (String s : split1) {
			String[] split2 = s.split("\\(");
			this.chat.displayChannel(split2[0]);
		}
	}

	private void getChannels() {
		this.clientThread.echo("LISTER");
	}

	public void onServerConnected(ClientThread clientThread) {
	}

	public void onServerDisconnected(ClientThread clientThread) {
	}

	public void sendMessage(String message) {
		this.clientThread.echo("MESSAGE " + message);
	}

	public void connect(String username, String password) {
		this.clientThread.echo("IDENTIFIER " + username + " " + password);
	}

	public void selectChannel(String channel) {
		this.clientThread.echo("SORTIR");
		this.clientThread.echo("REJOINDRE " + channel);
	}
}
