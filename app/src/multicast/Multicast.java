package multicast;

import server.model.User;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Multicast {
	private MulticastPublisherThread multicastPublisherThread;
	private MulticastReceiverThread multicastReceiverThread;
	private String name;
	private InetAddress group;
	private Integer port;

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