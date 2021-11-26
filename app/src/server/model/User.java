package server.model;

import java.util.Objects;

/**
 * Represents a user.
 */
public class User {
	/**
	 * Login of the user.
	 */
	private String login;
	/**
	 * Password of the user.
	 */
	private String password;
	/**
	 * True if the user is an admin, false otherwise.
	 */
	private Boolean isAdmin;
	/**
	 * State of the user.
	 */
	private State state;
	/**
	 * Canal in where the user is.
	 */
	private Canal canal;

	/**
	 * Constructs a user.
	 */
	public User() {
		this.state = State.UNAUTHENTICATED;
	}

	/**
	 * Constructs a user.
	 * @param login Login of the user.
	 * @param password Password of the user.
	 */
	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.isAdmin = Boolean.FALSE;
		this.state = State.AUTHENTICATED;
	}

	/**
	 * Return the login of the user.
	 * @return The login of the user.
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Return the rights of the user.
	 * @return The rights of the user.
	 */
	public String getRights() {
		return this.isAdmin ? "ADMIN" : "USER";
	}

	/**
	 * Return the state of the user.
	 * @return The state of the user.
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Update the state of the user.
	 * @param state The new state of the user.
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Return the canal where the user is.
	 * @return The canal where the user is.
	 */
	public Canal getCanal() {
		return this.canal;
	}

	/**
	 * Update the canal of the user.
	 * @param canal The new canal of the user.
	 */
	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	/**
	 * Return true if an object is equal to the user, false otherwise.
	 * @param o An object to be compared with.
	 * @return True if an object is equal to the user, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(isAdmin, user.isAdmin) && state == user.state && Objects.equals(canal, user.canal);
	}
}
