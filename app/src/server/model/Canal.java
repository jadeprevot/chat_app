package server.model;

import server.stream.ClientThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a channel.
 */
public class Canal {
	/**
	 * Name of the channel.
	 */
	private final String name;
	/**
	 * Topic of the channel.
	 */
	private final String topic;
	/**
	 * List of client threads in the channel.
	 */
	private final List<ClientThread> userList;

	/**
	 * Constructs a channel.
	 * @param name Name of the channel.
	 * @param topic Topic of the channel.
	 */
	public Canal(String name, String topic) {
		this.name = name;
		this.topic = topic;
		this.userList = new ArrayList<>();
	}

	/**
	 * Return the name of the channel.
	 * @return Name of the channel.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the topic of the channel.
	 * @return Topic of the channel.
	 */
	public String getTopic() {
		return this.topic;
	}

	/**
	 * Return the list of client threads in the channel.
	 * @return List of client threads in the channel.
	 */
	public List<ClientThread> getUserList() {
		return this.userList;
	}

	/**
	 * Add a client thread to the channel.
	 * @param user Client thread to be added.
	 */
	public void addUser(ClientThread user) {
		this.userList.add(user);
	}

	/**
	 * Remove a client thread to the channel.
	 * @param user Client thread to be removed.
	 */
	public void removeUser(ClientThread user) {
		this.userList.remove(user);
	}

	/**
	 * Says if an object is equal to the channel.
	 * @param o Object to be compared with.
	 * @return Return true if the object is equal to the channel, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Canal channel = (Canal) o;
		return Objects.equals(name, channel.name);
	}
}
