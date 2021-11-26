package multicast;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Thread that receive messages from the group.
 */
public class MulticastReceiverThread extends Thread {
	/**
	 * Group of the multicast connection.
	 */
	InetAddress group;
	/**
	 * Port of the multicast connection.
	 */
	int port;
	/**
	 * Name of the user.
	 */
	String name;
	/**
	 * Socket that communicates (receive) with the group.
	 */
	MulticastSocket socketReception;

	/**
	 * Constructs a multicast receiver thread.
	 * @param group Group of the multicast connection.
	 * @param port Port of the multicast connection.
	 * @param name Name of the user.
	 * @throws Exception
	 */
	MulticastReceiverThread(InetAddress group, int port, String name) throws Exception {
		this.group = group;
		this.port = port;
		this.name = name;
		socketReception = new MulticastSocket(port);
		socketReception.joinGroup(group);
		start();
	}

	/**
	 * Run the thread.
	 */
	public void run() {
		DatagramPacket message;
		byte[] contenuMessage;
		String texte;
		ByteArrayInputStream lecteur;

		while (true) {
			contenuMessage = new byte[1024];
			message = new DatagramPacket(contenuMessage, contenuMessage.length);
			try {
				socketReception.receive(message);
				texte = (new DataInputStream(new ByteArrayInputStream(contenuMessage))).readUTF();
				if (texte.startsWith(name)) continue;
				System.out.println(texte);
			} catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}
}