package server.model;

import server.stream.ClientThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Canal {
	private final String name;
	private final String topic;
	private final List<ClientThread> userList;

	public Canal(String name, String topic) {
		this.name = name;
		this.topic = topic;
		this.userList = new ArrayList<>();
	}

	public String getName() {
		return this.name;
	}

	public String getTopic() {
		return this.topic;
	}

	public List<ClientThread> getUserList() {
		return this.userList;
	}

	public void addUser(ClientThread user) {
		this.userList.add(user);
	}

	public void removeUser(User user) {
		this.userList.remove(user);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Canal canal = (Canal) o;
		return Objects.equals(name, canal.name);
	}
}
