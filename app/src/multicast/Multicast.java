package multicast;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Multicast client connection.
 * https://perso.telecom-paristech.fr/hudry/coursJava/reseau/multicast.html
 */
public class Multicast {
	/**
	 * Multicast publisher thread.
	 */
	private MulticastPublisherThread multicastPublisherThread;
	/**
	 * Multicast receiver thread
	 */
	private MulticastReceiverThread multicastReceiverThread;
	/**
	 * Name of the user.
	 */
	private String name;
	/**
	 * Group of the multicast connection.
	 */
	private InetAddress group;
	/**
	 * Port of the multicast connection.
	 */
	private Integer port;

	/**
	 * Constructs a multicast connection.
	 * @param group Group of the multicast connection.
	 * @param port Port of the multicast connection.
	 * @param name Name of the user.
	 */
	public Multicast(InetAddress group, Integer port, String name) {
		this.name = name;
		this.port = port;
		this.group = group;
		try {
			this.multicastPublisherThread = new MulticastPublisherThread(this.group, this.port, this.name);
			this.multicastReceiverThread = new MulticastReceiverThread(this.group, this.port, this.name);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Run the multicast.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}

		String nom = args[0];
		InetAddress groupeIP = null;
		Integer port = 28084;

		try {
			groupeIP = InetAddress.getByName("239.255.80.84");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		new Multicast(groupeIP, port, nom);
	}
}