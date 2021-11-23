package stream.multicast;

import model.User;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Multicast {
	private MulticastPublisherThread multicastPublisherThread;
	private MulticastReceiverThread multicastReceiverThread;
	private String name;
	private InetAddress group;
	private Integer port;

	public Multicast(String group, Integer port, String name) {
		this.name = name;
		this.port = 8084;
		try {
			this.group = InetAddress.getByName("239.255.80.84");
			this.multicastPublisherThread = new MulticastPublisherThread(this.group, this.port, this.name);
			this.multicastReceiverThread = new MulticastReceiverThread(this.group, this.port, this.name);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
