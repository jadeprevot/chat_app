package server.stream;

import server.model.Canal;
import server.model.User;
import server.Server;

import java.io.*;
import java.net.*;

import static server.model.State.*;

/**
 * Thread qui se charge d'écouter les requetes des clients.
 * */
public class ClientThread extends Thread {
    private final Server server;
    private final Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private User user;
    private ClientThread directMessage;

    public ClientThread(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.user = new User();
        this.directMessage = null;
    }

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

    public void reply(String msg) {
        out.println(msg);
    }

    public void authenticateUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void quit() {
        this.user = new User();
    }

    public void join(Canal canal) {
        this.user.setCanal(canal);
        this.user.setState(CONNECTED_CANAL);
    }

    public void leave() {
        this.user.setCanal(null);
        this.user.setState(AUTHENTICATED);
    }

    public void message(String s) {
        out.println(s);
    }

    public void connect(ClientThread other) {
        this.directMessage = other;
        this.user.setState(CONNECTED_DIRECT);
    }

    public ClientThread getDirectMessage() {
        return this.directMessage;
    }

    public void disconnect() {
        this.directMessage = null;
        this.user.setState(AUTHENTICATED);
    }
}


