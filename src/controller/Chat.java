package controller;

import stream.client.Client;
import view.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Chat implements ActionListener {
	private Client client;
	private Window window;

	public Chat(String hostname, int port) throws IOException {
		this.client = new Client(this, hostname, port);
		this.client.start();

		this.window = new Window(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.window.getConnexionPanel().getConnexion()) {
			String username = this.window.getConnexionPanel().getUsername().getText();
			String password = this.window.getConnexionPanel().getPassword().getText();
			this.client.connect(username, password);
		}
		else if (e.getSource() == this.window.getChatPanel().getSend()) {
			String message = this.window.getChatPanel().getMessage().getText();
			this.client.sendMessage(message);
		}
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: java Client <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		new Chat(hostName, portNumber);
	}

	public void displayReply(String line) {
		this.window.getDataPanel().getData().append(line);
	}
}
