package multicast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastPublisherThread extends Thread {
	String name;
	InetAddress group;
	Integer port;
	MulticastSocket publisherSocket;

	MulticastPublisherThread(InetAddress group, Integer port, String name) throws Exception {
		this.group = group;
		this.port = port;
		this.name = name;
		this.publisherSocket = new MulticastSocket();
		this.publisherSocket.setTimeToLive(15); // pour un site
		this.start();
	}

	public void run() {
		BufferedReader input;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String text = input.readLine();
				emettre(text);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void emettre(String texte) throws Exception {
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