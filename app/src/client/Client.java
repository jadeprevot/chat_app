package client;

import client.controller.Chat;
import client.stream.ClientThread;

import java.io.*;
import java.net.*;

/**
 * Client that manages the communication with the server.
 */
public class Client {
	/**
	 * Represents the chat.
	 */
	private Chat chat;
	/**
	 * Represents the client thread.
	 */
	private ClientThread clientThread;
	/**
	 * Represents the channel.
	 */
	private String channel;
	/**
	 * Represents the direct message.
	 */
	private String dm;

	/**
	 * Constructs the client.
	 * @param chat : Chat.
	 * @param hostName : Hostname of the server
	 * @param portNumber : Port we connect to.
	 * @throws IOException
	 */
	public Client(Chat chat, String hostName, int portNumber) throws IOException {
		this.chat = chat;
		Socket socket = new Socket(hostName, portNumber);
		this.clientThread = new ClientThread(socket, this);
	}

	/**
	 * Starts the client thread.
	 */
	public void start() {
		this.clientThread.start();
	}

	/**
	 * Executed when a reply arrives.
	 * @param clientThread : Represents the client thread.
	 * @param reply : Represents the reply.
	 */
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
			case "+OK_HISTORIQUE": {
				this.displayHistoric(reply);
				System.out.println(reply);
				break;
			}
			default: {
				System.out.println(reply);
			}
		}
	}

	/**
	 * Executed when the client is connected to the server.
	 * @param clientThread : Represents the client thread.
	 */
	public void onServerConnected(ClientThread clientThread) {
	}

	/**
	 * Executed when the client is disconnected of the server.
	 * @param clientThread : Represents the client thread.
	 */
	public void onServerDisconnected(ClientThread clientThread) {
	}

	/**
	 * Gets the channels.
	 */
	private void getChannels() {
		this.clientThread.echo("LISTER");
	}

	/**
	 * Gets the online members.
	 */
	public void getMembers() {
		this.clientThread.echo("MEMBRES");
	}

	/**
	 * Gets the historic.
	 */
	public void getHistoric() {
		this.clientThread.echo("HISTORIQUE");
	}

	/**
	 * Displays the members.
	 * @param reply : Represents the reply.
	 */
	private void displayMembers(String reply) {
		this.chat.resetMembers();

		reply = reply.substring(reply.indexOf(" ") + 1);
		String[] members = reply.split(", ");
		for (String member : members) {
			this.chat.displayMember(member);
		}
	}

	/**
	 * Displays the channels.
	 * @param reply : Represents the reply.
	 */
	private void displayChannels(String reply) {
		this.chat.resetChannels();

		reply = reply.substring(reply.indexOf(" ") + 1);
		String[] split1 = reply.split(", ");
		for (String s : split1) {
			String[] split2 = s.split("\\(");
			this.chat.displayChannel(split2[0], true);
		}
	}

	/**
	 * Displays the message.
	 * @param reply : Represents the reply.
	 */
	private void displayMessage(String reply) {
		String[] data = reply.split(" ");
		boolean isCannal = data[1].equals("CANNAL") ? true : false;
		String name = data[2];
		String user = data[4];
		if (isCannal || !isCannal && name.equals(this.dm)) {
			data = reply.split("<<");
			String message = data[1].substring(0, data[1].length() - 2);
			this.chat.displayMessage(isCannal, name, user, message);
		}
		else {
			this.chat.popup(name);
		}
	}

	/**
	 * Displays the historic.
	 * @param reply : Represents the reply.
	 */
	private void displayHistoric(String reply) {
		this.chat.clearHistoric();
		reply = reply.substring(reply.indexOf(" ") + 1);
		String[] data = reply.split("\\|");
		for (String message : data) {
			if (message.split(" : ").length >= 2) {
				String name = message.split(" : ")[0];
				String msg = message.split(" : ")[1];
				this.chat.displayMessage(true, this.channel, name, msg);
			}
		}
	}

	/**
	 * Sends a message.
	 * @param message : Message sent.
	 */
	public void sendMessage(String message) {
		System.out.println(message);
		this.clientThread.echo("MESSAGE " + message);
	}

	/**
	 * Connects the user.
	 * @param username : Username set.
	 * @param password : Password set.
	 */
	public void connect(String username, String password) {
		this.clientThread.echo("IDENTIFIER " + username + " " + password);
	}

	/**
	 * Connects with a channel.
	 * @param channel : Channel selected.
	 */
	public void selectChannel(String channel) {
		this.clientThread.echo("SORTIR");
		this.clientThread.echo("DECONNEXION");
		this.clientThread.echo("REJOINDRE " + channel);
	}

	/**
	 * Connects with a member.
	 * @param member : Member selected.
	 */
	public void selectMember(String member) {
		this.clientThread.echo("SORTIR");
		this.clientThread.echo("DECONNEXION");
		this.clientThread.echo("CONNEXION " + member);
	}

	/**
	 * Sets the user.
	 * @param reply : Represents the reply.
	 */
	private void setUser(String reply) {
		this.chat.blockAuthenticate();
		String user = reply.split(" ")[1];
		this.chat.setUser(user);
	}

	/**
	 * Sets the channel.
	 * @param channel : Channel updated.
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * Sets the direct message.
	 * @param dm : Direct message updated.
	 */
	public void setDm(String dm) {
		this.dm = dm;
	}

	/**
	 * Leaves the channel or direct message chat.
	 */
	public void leave() {
		this.clientThread.echo("SORTIR");
		this.clientThread.echo("DECONNEXION");
		this.clientThread.echo("LISTER");
		this.clientThread.echo("MEMBRES");
	}
}
