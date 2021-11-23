package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
	private JTextField message;
	protected JButton send;

	public ChatPanel(Chat chat) {
		this.setBackground(Color.RED);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(800, 50));
		this.setMaximumSize(new Dimension(800, 50));

		JLabel jLabelMessage = new JLabel("Message");

		this.message = new JTextField(50);
		this.send = new JButton("Send");

		this.add(jLabelMessage);
		this.add(this.message);
		this.add(this.send);

		this.send.addActionListener(chat);
	}

	public JTextField getMessage() {
		return this.message;
	}

	public JButton getSend() {
		return this.send;
	}
}
