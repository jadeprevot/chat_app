package client.stream;

import client.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends  Thread {
	private final Client client;
	private final Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public ClientThread(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch (Exception e) {
			System.err.println("Error in ClientThread: " + e);
		}

		client.onServerConnected(this);

		try {
			String line;
			while ((line = in.readLine()) != null) {
				client.onServerReply(this, line);
			}
		}
		catch (Exception e) {
			System.err.println("Error in ClientThread:" + e);
		}

		client.onServerDisconnected(this);
	}

	public void echo(String msg) {
		out.println(msg);
	}

	public void sendMessage(String message) {
		this.echo(message);
	}
}
