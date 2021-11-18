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

				break;
			case "+OK_QUITTER":

				break;
			case "+OK_LISTER":

				break;
			case "+OK_REJOINDRE":

				break;
			case "SORTIR":

				break;
			case "NOTIFIER":
				this.chat.displayReply(reply);
				break;
			case "+OK_MESSAGE":

				break;
			case "MEMBRES":

				break;
			case "CONNEXION":

				break;
			case "DECONNEXION":

				break;
			default:
		}
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
}
