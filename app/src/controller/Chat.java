package controller;

import stream.client.Client;
import view.Window;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Chat implements ActionListener, MenuListener {
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

	public void displayReply(String line) {
		this.window.getDataPanel().getData().append(line);
	}

	public void displayChannel(String channel) {
		this.window.getChannelPanel().addChannel(channel);
		this.window.getChannelPanel().repaint();
		this.window.getChannelPanel().revalidate();
	}

	public void displayMember(String member) {
		this.window.getMemberPanel().addMember(member);
		this.window.getMemberPanel().repaint();
		this.window.getMemberPanel().revalidate();
	}

	public void resetMembers() {
		this.window.getMemberPanel().getMembers().removeAll();
		JMenu menu = new JMenu("Members");
		menu.setEnabled(false);
		this.window.getMemberPanel().getMembers().add(menu);
	}

	@Override
	public void menuSelected(MenuEvent e) {
		for (int i = 0; i < this.window.getChannelPanel().getChannels().getMenuCount(); ++i) {
			if (e.getSource() == this.window.getChannelPanel().getChannels().getMenu(i)) {
				this.client.selectChannel(this.window.getChannelPanel().getChannels().getMenu(i).getText());
			}
		}
		for (int i = 0; i < this.window.getMemberPanel().getMembers().getMenuCount(); ++i) {
			if (e.getSource() == this.window.getMemberPanel().getMembers().getMenu(i)) {
				this.client.selectMember(this.window.getMemberPanel().getMembers().getMenu(i).getText());
			}
		}
	}

	@Override
	public void menuDeselected(MenuEvent e) {

	}

	@Override
	public void menuCanceled(MenuEvent e) {

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
