package controller;

import stream.client.EchoClient;
import view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat implements ActionListener {
	private EchoClient echoClient;
	private Window window;

	public Chat() {
//		this.echoClient = new EchoClient();
		this.window = new Window(this);
	}

	public static void main(String[] args) {
		new Chat();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.window.getConnexionPanel().getConnexion()) {

		}
		else if (e.getSource() == this.window.getChatPanel().getSend()) {

		}
		for (JMenu menu : this.window.getChannelPanel().getChannels()){
			if (e.getSource() == menu){

			}
		}
	}
}
