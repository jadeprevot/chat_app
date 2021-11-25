package client.controller;

import client.Client;
import client.view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Chat implements ActionListener {
	private Client client;
	private Window window;
	private String user;

	public Chat(String hostname, int port) throws IOException {
		this.client = new Client(this, hostname, port);
		this.window = new Window(this);

		this.client.start();
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
		else {
			for (JButton button : this.window.getChannelPanel().getChannels()) {
				if (e.getSource() == button) {
					button.setEnabled(false);
					this.client.selectChannel(button.getText());
				} else {
					button.setEnabled(true);
				}
			}
			for (JButton button : this.window.getMemberPanel().getMembers()) {
				if (e.getSource() == button) {
					button.setEnabled(false);
					this.client.selectMember(button.getText());
				} else if (!button.getText().equals(this.user) && !button.getText().equals("Members")) {
					button.setEnabled(true);
				}
			}
		}
	}

	public void displayChannel(String channel, boolean b) {
		this.window.getChannelPanel().addChannel(channel, b);
		this.window.getChannelPanel().repaint();
		this.window.getChannelPanel().revalidate();
	}

	public void displayMember(String member) {
		this.window.getMemberPanel().addMember(member, this.user);
		this.window.getMemberPanel().repaint();
		this.window.getMemberPanel().revalidate();
	}

	public void displayMessage(boolean isCannal, String name, String user, String message) {
		JTextArea area = new JTextArea("\n" + user+ ": " + message);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);

		this.window.getDataPanel().add(area);
		this.window.getDataPanel().repaint();
		this.window.getDataPanel().revalidate();
	}

	public void resetMembers() {
		this.window.getMemberPanel().getMenu().removeAll();
		this.window.getMemberPanel().setMembers(new ArrayList<>());
		JButton menu = new JButton("Members");
		menu.setEnabled(false);
		this.window.getMemberPanel().getMenu().add(menu);
		this.window.getMemberPanel().getMembers().add(menu);
		this.window.getDataPanel().repaint();
		this.window.getDataPanel().revalidate();
	}

	public void blockAuthenticate() {
		this.window.getConnexionPanel().getUsername().setEnabled(false);
		this.window.getConnexionPanel().getPassword().setEnabled(false);
		this.window.getConnexionPanel().getConnexion().setEnabled(false);
	}

	public void setUser(String user) {
		this.user = user;
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
