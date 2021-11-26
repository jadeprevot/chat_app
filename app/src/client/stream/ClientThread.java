package client.stream;

import client.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Implements a thread, with the aim of managing a client.
 */
public class ClientThread extends  Thread {
	/**
	 * Client accessing to the thread.
	 */
	private final Client client;
	/**
	 * Socket linked to the client requesting the server.
	 */
	private final Socket socket;
	/**
	 * Socket read buffer.
	 */
	private BufferedReader in;
	/**
	 * Socket write buffer.
	 */
	private PrintWriter out;

	/**
	 * Constructs a thread.
	 * @param socket : Socket associated with a client.
	 * @param client : Client accessing to the thread linked to the socket.
	 */
	public ClientThread(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;
	}

	/**
	 * Launches the client thread.
	 */
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

	/**
	 * Sends a reply to the client.
	 * @param msg : Reply sent.
	 */
	public void echo(String msg) {
		out.println(msg);
	}

	/**
	 * Sends a message to the client.
	 * @param message : Message sent.
	 */
	public void sendMessage(String message) {
		this.echo(message);
	}
}
