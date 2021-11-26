package server.model;

/**
 * Enumeration of the states of a client.
 */
public enum State {
	/**
	 * The client is authenticated.
	 */
	AUTHENTICATED,
	/**
	 * The client is unauthenticated.
	 */
	UNAUTHENTICATED,
	/**
	 * The client is connected to a channel.
	 */
	CONNECTED_CANAL,
	/**
	 * The client is connected to another client.
	 */
	CONNECTED_DIRECT
}
