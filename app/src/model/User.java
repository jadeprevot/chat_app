package model;

import java.util.Objects;

public class User {
	private String login;
	private String password;
	private Boolean isAdmin;
	private State state;
	private Canal canal;

	public User() {
		this.state = State.UNAUTHENTICATED;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.isAdmin = Boolean.FALSE;
		this.state = State.AUTHENTICATED;
	}

	public String getLogin() {
		return this.login;
	}

	public String getRights() {
		return this.isAdmin ? "ADMIN" : "USER";
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Canal getCanal() {
		return this.canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(isAdmin, user.isAdmin) && state == user.state && Objects.equals(canal, user.canal);
	}
}
