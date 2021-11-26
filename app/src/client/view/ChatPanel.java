package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;

/**
 * Implements the UI of the chat writing.
 */
public class ChatPanel extends JPanel {
	/**
	 * Represents a text field to input the message.
	 */
	private JTextField message;
	/**
	 * Represents the button to send the message.
	 */
	private JButton send;

	/**
	 *Constructs the chat writing panel.
	 * @param chat : Chat.
	 */
	public ChatPanel(Chat chat) {
		this.setBackground(new Color(222, 64, 105));
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

	/**
	 * Gets the message.
	 * @return: Returns the message.
	 */
	public JTextField getMessage() {
		return this.message;
	}

	/**
	 * Gets the button to send the message.
	 * @return : Returns the button to send the message.
	 */
	public JButton getSend() {
		return this.send;
	}
}
