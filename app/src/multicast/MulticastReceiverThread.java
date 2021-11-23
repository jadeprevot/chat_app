package stream.multicast;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiverThread extends Thread {
	InetAddress group;
	int port;
	String nom;
	MulticastSocket socketReception;

	MulticastReceiverThread(InetAddress group, int port, String nom) throws Exception {
		this.group = group;
		this.port = port;
		this.nom = nom;
		socketReception = new MulticastSocket(port);
		socketReception.joinGroup(group);
		start();
	}

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
				if (texte.startsWith(nom)) continue;
				System.out.println(texte);
			} catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}
}