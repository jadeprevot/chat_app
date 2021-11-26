package server;

import server.model.Canal;
import server.model.State;
import server.model.User;
import server.stream.ClientThread;
import server.stream.ServerThread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Take care of managing the different connections and allow communication between the different threads.
 * */
public class Server {
    /**
     * Thread that manage new connections.
     */
    private final ServerThread serverThread;
    /**
     * Lisy of threads that manage client connections.
     */
    private final List<ClientThread> clientThreadList;
    /**
     * List of channels.
     */
    private final List<Canal> channelList;
    /**
     * Indicates if the server is running or not.
     */
    private Boolean isRunning;
    /**
     * Path to the folder that contains historic files.
     */
    private String historicFolder;

    /**
     * Construct a server.
     * @param port Port where the server listen to new connections.
     * @throws IOException
     */
    public Server(Integer port) throws IOException {
        this.serverThread = new ServerThread(this, port);
        this.clientThreadList = new ArrayList<>();
        this.channelList = new ArrayList<>();
        this.isRunning = Boolean.FALSE;
        this.historicFolder = "app/historic/";
        this.addCanals();
    }

    /**
     * Add a channel to the list of cannals of the server.
     */
    private void addCanals() {
        this.channelList.add(new Canal("science", "Forum basé sur la science"));
        this.channelList.add(new Canal("nature", "Forum basé sur la nature"));
        this.channelList.add(new Canal("espace", "Forum basé sur l'espace"));
        this.channelList.add(new Canal("technologie", "Forum basé sur la technologie"));
        this.channelList.add(new Canal("people", "Forum basé sur les peoples"));
        this.channelList.add(new Canal("automobile", "Forum basé sur l'automobile"));
        this.channelList.add(new Canal("vacances", "Forum basé sur les vacances"));
        this.channelList.add(new Canal("littérature", "Forum basé sur la littérature"));
        this.channelList.add(new Canal("informatique", "Forum basé sur l'informatique"));
    }

    /**
     * Start the server.
     */
    public void start() {
        if (this.isRunning) {
            System.err.println("Server already running");
        }
        else {
            this.serverThread.start();
        }
    }

    /**
     * Executed when a new connection happens.
     * @param clientThread
     */
    public void onClientConnected(ClientThread clientThread) {
        this.clientThreadList.add(clientThread);
    }

    /**
     * Executed when a connection closes.
     * @param clientThread A thread that manages a client connection.
     */
    public void onClientDisconnected(ClientThread clientThread) {
        this.clientThreadList.remove(clientThread);
    }

    /**
     * Executed when a request from a client arrives.
     * @param clientThread A thread that manages a client connection.
     * @param request A client request.
     */
    public void onClientRequest(ClientThread clientThread, String request) {
        String[] args = request.split(" ");
        String cmd = args[0];
        switch (cmd) {
            case "IDENTIFIER": {
                try {
                    String login = args[1];
                    String password = args[2];
                    this.authenticate(clientThread, login, password);
                } catch (Exception e) {
                    clientThread.reply("-ERR_SYNTAXE");
                }
                break;
            }
            case "QUITTER": {
                this.quit(clientThread);
                break;
            }
            case "LISTER": {
                this.list(clientThread);
                break;
            }
            case "REJOINDRE": {
                try {
                    String channelName = args[1];
                    this.join(clientThread, channelName);
                } catch (Exception e) {
                    clientThread.reply("-ERR_SYNTAXE");
                }
                break;
            }
            case "SORTIR": {
                this.leave(clientThread);
                break;
            }
            case "MESSAGE": {
                try {
                    String message = request.substring(request.indexOf(" "));
                    this.message(clientThread, message);
                } catch (Exception e) {
                    clientThread.reply("-ERR_SYNTAXE");
                }
                break;
            }
            case "MEMBRES": {
                this.members(clientThread);
                break;
            }
            case "CONNEXION": {
                try {
                    String login = args[1];
                    this.connect(clientThread, login);
                } catch (Exception e) {
                    clientThread.reply("-ERR_SYNTAXE");
                }
                break;
            }
            case "DECONNEXION": {
                this.disconnect(clientThread);
                break;
            }
            case "HISTORIQUE": {
                this.historic(clientThread);
                break;
            }
            default: {
                clientThread.reply("-ERR_CMDINCONNUE");
            }
        }
    }

    /**
     * Send the historic of a conversation to the client.
     * @param clientThread A thread that manages a client connection.
     */
    private void historic(ClientThread clientThread) {
        if (clientThread.getUser().getState() == State.AUTHENTICATED) {
            clientThread.reply("-KO_MESSAGE: " + "Utilisateur non connecté à un cannal ou à un utilisateur");
        }
        else if (clientThread.getUser().getState() == State.UNAUTHENTICATED) {
            clientThread.reply("-KO_MESSAGE: " + "Utilisateur non connecté");
        }
        else if (clientThread.getUser().getState() == State.CONNECTED_CANAL) {
            String user = clientThread.getUser().getLogin();
            String channel = clientThread.getUser().getCanal().getName();

            String historic = "";

            File file = new File(this.historicFolder + channel);
            if (file.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        historic += line + " | ";
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            clientThread.reply("+OK_HISTORIQUE: " + historic);
        }
        else if (clientThread.getUser().getState() == State.CONNECTED_DIRECT) {
            String name = clientThread.getUser().getLogin();
            String other = clientThread.getDirectMessage().getUser().getLogin();

            String fileName = name.compareTo(other) <= 0 ? name + "-" + other : other + "-" + name;

            String historic = "";

            File file = new File(this.historicFolder + fileName);
            if (file.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        historic += line + " | ";
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            clientThread.reply("+OK_HISTORIQUE: " + historic);
        }
    }

    /**
     * Authenticate the client
     * @param clientThread A thread that manages a client connection.
     * @param login Login of the client.
     * @param password Password of the client.
     */
    private void authenticate(ClientThread clientThread, String login, String password) {
        if (clientThread.getUser().getState() == State.UNAUTHENTICATED) {
            User user = new User(login, password);
            clientThread.authenticateUser(user);
            clientThread.reply("+OK_IDENTIFIER: " + user.getLogin() + " " + user.getRights());
        }
        else {
            clientThread.reply("-KO_IDENTIFIER: " + "Utilisateur déjà connecté");
        }
    }

    /**
     * Disconnect a client.
     * @param clientThread A thread that manages a client connection.
     */
    private void quit(ClientThread clientThread) {
        if (clientThread.getUser().getState() == State.AUTHENTICATED) {
            clientThread.quit();
            clientThread.reply("+OK_QUITTER");
        }
        else {
            clientThread.reply("-KO_QUITTER: " + "Utilisateur non connecté");
        }

    }

    /**
     * List the channels of the server.
     * @param clientThread A thread that manages a client connection.
     */
    private void list(ClientThread clientThread) {
        if (clientThread.getUser().getState() == State.AUTHENTICATED) {
            String reply = "+OK_LISTER: ";
            for (Canal channel : this.channelList) {
                reply += channel.getName() + "(" + channel.getTopic() + "), ";
            }
            reply = reply.substring(0, reply.length() - 2);
            clientThread.reply(reply);
        }
        else {
            clientThread.reply("-KO_LISTER: " + "Utilisateur non connecté");
        }
    }

    /**
     * Let a client to join a channel.
     * @param clientThread A thread that manages a client connection.
     * @param channelName Name of the channel to join.
     */
    private void join(ClientThread clientThread, String channelName) {
        if (clientThread.getUser().getState() == State.AUTHENTICATED) {
            for (Canal channel : this.channelList) {
                if (channel.getName().equals(channelName)) {
                    clientThread.join(channel);
                    User user = clientThread.getUser();
                    channel.addUser(clientThread);
                    clientThread.reply("+OK_REJOINDRE: " + user.getCanal().getName());
                    return;
                }
            }
            Canal channel = new Canal(channelName, "");
            this.channelList.add(channel);
            clientThread.join(channel);
            User user = clientThread.getUser();
            channel.addUser(clientThread);
            clientThread.reply("+OK_REJOINDRE: " + user.getCanal().getName());
        }
        else {
            clientThread.reply("-KO_REJOINDRE: " + "Utilisateur non connecté");
        }
    }

    /**
     * Let a client to leave a channel.
     * @param clientThread A thread that manages a client connection.
     */
    private void leave(ClientThread clientThread) {
        if (clientThread.getUser().getState() == State.CONNECTED_CANAL) {
            String name = clientThread.getUser().getCanal().getName();
            clientThread.leave();

            for (Canal channel : this.channelList) {
                if (channel.getName().equals(name)) {
                    channel.removeUser(clientThread);
                    clientThread.reply("+OK_SORTIR: " + name);
                    return;
                }
            }
        }
        else {
            clientThread.reply("-KO_REJOINDRE: " + "Utilisateur non connecté");
        }
    }

    /**
     * Let a client to send a message.
     * @param clientThread A thread that manages a client connection.
     * @param message Message to send.
     */
    private void message(ClientThread clientThread, String message) {
        if (clientThread.getUser().getState() == State.AUTHENTICATED) {
            clientThread.reply("-KO_MESSAGE: " + "Utilisateur non connecté à un cannal ou à un utilisateur");
        }
        else if (clientThread.getUser().getState() == State.UNAUTHENTICATED) {
            clientThread.reply("-KO_MESSAGE: " + "Utilisateur non connecté");
        }
        else if (clientThread.getUser().getState() == State.CONNECTED_CANAL) {
            User user = clientThread.getUser();
            String channelName = user.getCanal().getName();
            for (Canal channel : this.channelList) {
                if (channel.getName().equals(channelName)) {
                    for (ClientThread other : channel.getUserList()) {
                        if (!clientThread.getUser().getLogin().equals(other.getUser().getLogin())) {
                            other.message("NOTIFIER: CANNAL " + channelName + " PARLE: " + user.getLogin() + " << " + message + " >>");
                        }
                    }
                }
            }
            clientThread.reply("+OK_MESSAGE: CANNAL " + channelName + " PARLE: " + user.getLogin() + " << " + message + " >>");
            this.saveCanalMessage(user.getLogin(), channelName, message);
        }
        else if (clientThread.getUser().getState() == State.CONNECTED_DIRECT) {
            ClientThread ct =  clientThread.getDirectMessage();
            String other =  clientThread.getDirectMessage().getUser().getLogin();
            String name = clientThread.getUser().getLogin();
            ct.message("NOTIFIER: DM " + name + " PARLE: " + name + " << " + message + " >>");
            clientThread.reply("+OK_MESSAGE: DM " + other + " PARLE: " + name + " << " + message + " >>");
            this.saveMessage(name, other, message);
        }
    }

    /**
     * List the members of a channel or of the servers connected.
     * @param clientThread A thread that manages a client connection.
     */
    private void members(ClientThread clientThread) {
        if (clientThread.getUser().getState() == State.AUTHENTICATED) {
            String reply = "";
            for (ClientThread ct : this.clientThreadList) {
                reply += ct.getUser().getLogin() + ", ";
            }
            reply = reply.substring(0, reply.length() - 2);
            clientThread.reply("+OK_MEMBRES: " + reply);
        }
        else if (clientThread.getUser().getState() == State.UNAUTHENTICATED) {
            clientThread.reply("-KO_MESSAGE: " + "Utilisateur non connecté");
        }
        else if (clientThread.getUser().getState() == State.CONNECTED_CANAL) {
            User user = clientThread.getUser();
            String channelName = user.getCanal().getName();

            for (Canal channel : this.channelList) {
                if (channel.getName().equals(channelName)) {
                    String reply = "";
                    for (ClientThread ct : channel.getUserList()) {
                        User other = ct.getUser();
                        reply += other.getLogin() + ", ";
                    }
                    reply = reply.substring(0, reply.length() - 2);
                    clientThread.reply("+OK_MEMBRES: " + reply);
                }
            }
        }
        else if (clientThread.getUser().getState() == State.CONNECTED_DIRECT) {
            String login1 = clientThread.getUser().getLogin();
            String login2 = clientThread.getDirectMessage().getUser().getLogin();
            clientThread.reply("+OK_MEMBRES: " + login1 + ", " + login2);
        }
    }

    /**
     * Connect a client to another one.
     * @param clientThread A thread that manages a client connection.
     * @param login Login of a client.
     */
    private void connect(ClientThread clientThread, String login) {
        if (clientThread.getUser().getState() == State.AUTHENTICATED) {
            ClientThread other = this.getClientThread(login);
            if (other != null) {
                clientThread.connect(other);
                clientThread.message("+OK_CONNEXION: " + login);
                String name = clientThread.getUser().getLogin();
                other.message("+OK_CONNEXION: " + name);
            }
        }
        else if (clientThread.getUser().getState() == State.UNAUTHENTICATED) {
            clientThread.reply("-KO_CONNEXION: " + "Utilisateur non connecté");
        }
        else if (clientThread.getUser().getState() == State.CONNECTED_CANAL) {
            clientThread.reply("-KO_CONNEXION: " + "Utilisateur non connecté à un cannal");
        }
        else if (clientThread.getUser().getState() == State.CONNECTED_DIRECT) {
            clientThread.reply("-KO_CONNEXION: " + "Utilisateur déjà connecté à un autre utilisateur");
        }
    }

    /**
     * Disconnect a client of another one.
     * @param clientThread A thread that manages a client connection.
     */
    private void disconnect(ClientThread clientThread) {
        if (clientThread.getUser().getState() != State.CONNECTED_DIRECT) {
            clientThread.reply("-KO_DECONNEXION: " + "Utilisateur non connecté à un autre utilisateur");
        }
        else {
            clientThread.disconnect();
        }
    }

    /**
     * Return the thread that manages a client connection of the specified login.
     * @param login Login of a client.
     * @return A thread that manages a client connection.
     */
	public ClientThread getClientThread(String login) {
		for (ClientThread ct : this.clientThreadList) {
			if (ct.getUser().getLogin().equals(login)) {
				return ct;
			}
		}
		return null;
	}

    /**
     * Save the messages of direct messages.
     * @param name Name of the message sender.
     * @param other Name of the message receiver.
     * @param message Message to save.
     */
    private void saveMessage(String name, String other, String message) {
        String fileName = name.compareTo(other) <= 0 ? name + "-" + other : other + "-" + name;
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(this.historicFolder + fileName, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.println(name + " : " + message);
        printWriter.close();
    }

    /**
     * Save the messages of channels.
     * @param name Name of the message sender.
     * @param channel Name of the message receiver.
     * @param message Message to save.
     */
    private void saveCanalMessage(String name, String channel, String message) {
        String fileName = channel;
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(this.historicFolder + fileName, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.println(name + " : " + message);
        printWriter.close();
    }

    /**
     * Run the server.
     * @param args Command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        Server server = new Server(portNumber);
        server.start();
    }
}
