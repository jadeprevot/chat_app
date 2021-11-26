package server.model;

import server.stream.ClientThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a canal.
 */
public class Canal {
	/**
	 * Name of the canal.
	 */
	private final String name;
	/**
	 * Topic of the canal.
	 */
	private final String topic;
	/**
	 * List of client threads in the canal.
	 */
	private final List<ClientThread> userList;

	/**
	 * Constructs a canal.
	 * @param name Name of the canal.
	 * @param topic Topic of the canal.
	 */
	public Canal(String name, String topic) {
		this.name = name;
		this.topic = topic;
		this.userList = new ArrayList<>();
	}

	/**
	 * Return the name of the canal.
	 * @return Name of the canal.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the topic of the canal.
	 * @return Topic of the canal.
	 */
	public String getTopic() {
		return this.topic;
	}

	/**
	 * Return the list of client threads in the canal.
	 * @return List of client threads in the canal.
	 */
	public List<ClientThread> getUserList() {
		return this.userList;
	}

	/**
	 * Add a client thread to the canal.
	 * @param user Client thread to be added.
	 */
	public void addUser(ClientThread user) {
		this.userList.add(user);
	}

	/**
	 * Remove a client thread to the canal.
	 * @param user Client thread to be removed.
	 */
	public void removeUser(ClientThread user) {
		this.userList.remove(user);
	}

	/**
	 * Says if an object is equal to the canal.
	 * @param o Object to be compared with.
	 * @return Return true if the object is equal to the canal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Canal canal = (Canal) o;
		return Objects.equals(name, canal.name);
	}
}
