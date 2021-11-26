package multicast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Thread that publish messages to the group.
 */
public class MulticastPublisherThread extends Thread {
	/**
	 * Name of the user.
	 */
	String name;
	/**
	 * Group of the multicast connection.
	 */
	InetAddress group;
	/**
	 * Port of the multicast connection.
	 */
	Integer port;
	/**
	 * Socket that communicates (publish) with the group.
	 */
	MulticastSocket publisherSocket;

	/**
	 * Constructs a multicast publisher thread.
	 * @param group Group of the multicast connection.
	 * @param port Port of the multicast connection.
	 * @param name Name of the user.
	 * @throws Exception
	 */
	MulticastPublisherThread(InetAddress group, Integer port, String name) throws Exception {
		this.group = group;
		this.port = port;
		this.name = name;
		this.publisherSocket = new MulticastSocket();
		this.publisherSocket.setTimeToLive(15); // pour un site
		this.start();
	}

	/**
	 * Run the thread.
	 */
	public void run() {
		BufferedReader input;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String text = input.readLine();
				publish(text);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Publish a message.
	 * @param texte Message to be published.
	 * @throws Exception
	 */
	void publish(String texte) throws Exception {
		byte[] contenuMessage;
		DatagramPacket message;

		ByteArrayOutputStream sortie = new ByteArrayOutputStream();
		texte = this.name + " : " + texte;
		(new DataOutputStream(sortie)).writeUTF(texte);
		contenuMessage = sortie.toByteArray();
		message = new DatagramPacket(contenuMessage, contenuMessage.length, group, port);
		this.publisherSocket.send(message);
	}
}