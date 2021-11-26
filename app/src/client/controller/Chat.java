package client.controller;

import client.Client;
import client.view.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements the user interface
 */
public class Chat implements ActionListener {
	private Client client;
	private Window window;
	private String user;

	/**
	 * Constructs a user interface
	 * @param hostname : Hostname of the server
	 * @param port : Port we connect to
	 * @throws IOException
	 */
	public Chat(String hostname, int port) throws IOException {
		this.client = new Client(this, hostname, port);
		this.window = new Window(this);

		this.client.start();
	}

	/**
	 * Describes the actions we can do in the app
	 * @param e : Action currently done
	 */
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
		else if (e.getSource() == this.window.getMemberPanel().getRefresh()) {
			this.client.getMembers();
		}
		else if (e.getSource() == this.window.getChannelPanel().getLeave()) {
			this.client.leave();
			this.clearHistoric();
		}
		else if (e.getSource() == this.window.getChannelPanel().getCreate()) {
			String input = this.window.getChannelPanel().getInput().getText();
			if (!input.equals("")) {
				this.client.selectChannel(input);
				this.client.setChannel(input);
				this.client.setDm(null);
				this.client.getHistoric();
			}
		}
		else {
			for (JButton button : this.window.getChannelPanel().getChannels()) {
				if (e.getSource() == button) {
					button.setEnabled(false);
					this.client.selectChannel(button.getText());
					this.client.setChannel(button.getText());
					this.client.setDm(null);
					this.client.getHistoric();
				} else {
					button.setEnabled(true);
				}
			}
			for (JButton button : this.window.getMemberPanel().getMembers()) {
				if (e.getSource() == button) {
					button.setEnabled(false);
					if (button.getBackground().equals(Color.RED)) button.setBackground(null);
					this.client.selectMember(button.getText());
					this.client.setChannel(null);
					this.client.setDm(button.getText());
					this.client.getHistoric();
				} else if (!button.getText().equals(this.user) && !button.getText().equals("Members")) {
					button.setEnabled(true);
				}
			}
		}
	}

	/**
	 * Displays the channels of the app
	 * @param channel : Channel currently displayed
	 * @param b : Tells if the channel is available or not
	 */
	public void displayChannel(String channel, boolean b) {
		this.window.getChannelPanel().addChannel(channel, b);
		this.window.getChannelPanel().repaint();
		this.window.getChannelPanel().revalidate();
	}

	/**
	 * Displays the members who are connected to the app
	 * @param member : Member currently displayed
	 */
	public void displayMember(String member) {
		this.window.getMemberPanel().addMember(member, this.user);
		this.window.getMemberPanel().repaint();
		this.window.getMemberPanel().revalidate();
	}

	/**
	 * Displays the messages sent by the members in the chat
	 * @param isCannal : //TODO
	 * @param name : //TODO
	 * @param user : User who sent the message
	 * @param message : Content of the message
	 */
	public void displayMessage(boolean isCannal, String name, String user, String message) {
		this.window.getDataPanel().addData("\n" + user+ ": " + message);
		this.window.getDataPanel().repaint();
		this.window.getDataPanel().revalidate();
	}

	/**
	 * Updates the list of members connected
	 */
	public void resetMembers() {
		this.window.getMemberPanel().getMenu().removeAll();
		this.window.getMemberPanel().setMembers(new ArrayList<>());
		JButton menu = new JButton("Members");
		menu.addActionListener(this);
		this.window.getMemberPanel().setRefresh(menu);
		this.window.getMemberPanel().getMenu().add(menu);
		this.window.getMemberPanel().repaint();
		this.window.getMemberPanel().revalidate();
	}

	/**
	 * Updates the list of channels available
	 */
	public void resetChannels() {
		this.window.getChannelPanel().getMenu().removeAll();
		this.window.getChannelPanel().setChannels(new ArrayList<>());

		JButton menu = new JButton("Channels");
		menu.addActionListener(this);
		this.window.getChannelPanel().setLeave(menu);
		this.window.getChannelPanel().getMenu().add(menu);

		JButton create = new JButton("+ Add");
		create.addActionListener(this);
		this.window.getChannelPanel().setCreate(create);
		this.window.getChannelPanel().getMenu().add(create);

		JTextField input = new JTextField();
		this.window.getChannelPanel().setInput(input);
		this.window.getChannelPanel().getMenu().add(input);

		this.window.getChannelPanel().repaint();
		this.window.getChannelPanel().revalidate();
	}

	/**
	 * //TODO
	 */
	public void blockAuthenticate() {
		this.window.getConnexionPanel().getUsername().setEnabled(false);
		this.window.getConnexionPanel().getPassword().setEnabled(false);
		this.window.getConnexionPanel().getConnexion().setEnabled(false);
	}

	/**
	 * Sets the user
	 * @param user : User selected
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Reinitializes the historic
	 */
	public void clearHistoric() {
		this.window.getDataPanel().getData().setText("");
	}

	/**
	 * Notifies the user if they receive a message from another one
	 * @param name : The name of the sender.
	 */
	public void popup(String name) {
		for (JButton button : this.window.getMemberPanel().getMembers()) {
			if (button.getText().equals(name)) {
				button.setBackground(Color.RED);
			}
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
}
