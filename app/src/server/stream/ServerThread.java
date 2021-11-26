package server.stream;

import server.Server;
import server.stream.ClientThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Thread which is responsible for listening to new connection requests from clients.
 * */
public class ServerThread extends Thread {
	/**
	 * Server that manages connections and communications.
	 */
	public Server server;
	/**
	 * Socket used to listen to new connections.
	 */
	public ServerSocket serverSocket;
	/**
	 * Port where the socket is listening.
	 */
	public Integer port;

	/**
	 * Construct a server.
	 * @param server The server that manage connections and communications.
	 * @param port Port where the socket is listening.
	 * @throws IOException
	 */
	public ServerThread(Server server, Integer port) throws IOException {
		this.server = server;
		this.serverSocket = new ServerSocket(port);
		this.port = port;
		System.out.println("Server ready...");
	}

	/**
	 * Run the ServerThread.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();

				InetAddress clientInetAddress = clientSocket.getInetAddress();
				System.out.println("Connexion from:" + clientInetAddress);

				ClientThread clientThread = new ClientThread(server, clientSocket);
				clientThread.start();
			}
			catch (IOException e) {
				System.err.println("Exception caught when trying to listen on port " + this.port + " or listening for a connection");
				System.err.println(e.getMessage());
			}
		}
	}
}
