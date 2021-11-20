package stream.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Thread qui se charge d'écouter les nouvelles requetes de connexions de la part des clients.
 * */
public class ServerThread extends Thread {
	public Server server;
	public ServerSocket serverSocket;
	public Integer port;

	public ServerThread(Server server, Integer port) throws IOException {
		this.server = server;
		this.serverSocket = new ServerSocket(port);
		this.port = port;
		System.out.println("Server ready...");
	}

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
