package server.stream;

import server.model.Canal;
import server.model.User;
import server.Server;

import java.io.*;
import java.net.*;

import static server.model.State.*;

/**
 * Thread which is responsible for listening to client requests.
 * */
public class ClientThread extends Thread {
    /**
     * Server that manages connections and communications.
     */
    private final Server server;
    /**
     * Socket that communicates with the client.
     */
    private final Socket clientSocket;
    /**
     * Buffer that read the client requests.
     */
    private BufferedReader in;
    /**
     * Buffer that send to client responses.
     */
    private PrintWriter out;
    /**
     * User associated with the client thread.
     */
    private User user;
    /**
     * Client thread associated with another client (for direct messages).
     */
    private ClientThread directMessage;

    /**
     * Construct a Client thread.
     * @param server The server that manages connections and communications between client threads.
     * @param clientSocket Socket that communicates with the client.
     */
    public ClientThread(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.user = new User();
        this.directMessage = null;
    }

    /**
     * Run the client thread.
     */
    @Override
    public void run() {
        try {
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch (Exception e) {
            System.err.println("Error in ClientThread: " + e);
        }

        server.onClientConnected(this);

        try {
            String line;
            while ((line = in.readLine()) != null) {
                server.onClientRequest(this, line);
            }
        }
        catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }

        server.onClientDisconnected(this);
    }

    /**
     * Send a reply to the client.
     * @param msg Message to be send.
     */
    public void reply(String msg) {
        out.println(msg);
    }

    /**
     * Authenticate a user.
     * @param user User to be authenticated.
     */
    public void authenticateUser(User user) {
        this.user = user;
    }

    /**
     * Return the user of the client thread.
     * @return The user of the client thread.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Creates a new user.
     */
    public void quit() {
        this.user = new User();
    }

    /**
     * Join a channel.
     * @param channel Canal to be joined.
     */
    public void join(Canal channel) {
        this.user.setCanal(channel);
        this.user.setState(CONNECTED_CANAL);
    }

    /**
     * Leave a channel.
     */
    public void leave() {
        this.user.setCanal(null);
        this.user.setState(AUTHENTICATED);
    }

    /**
     * Send a message.
     * @param s Message to be sent.
     */
    public void message(String s) {
        out.println(s);
    }

    /**
     * Connect the client to another one.
     * @param other The client to be connected with.
     */
    public void connect(ClientThread other) {
        this.directMessage = other;
        this.user.setState(CONNECTED_DIRECT);
    }

    /**
     * Return the client who are connected with the client thread.
     * @return
     */
    public ClientThread getDirectMessage() {
        return this.directMessage;
    }

    /**
     * Disconnect the client with the connected one.
     */
    public void disconnect() {
        this.directMessage = null;
        this.user.setState(AUTHENTICATED);
    }
}


