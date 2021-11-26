package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;

/**
 * Implements the UI of the chat conversation.
 */
public class DataPanel extends JPanel {
	/**
	 * Represents the text area where the messages are displayed.
	 */
	JTextArea data = new JTextArea("");

	/**
	 * Constructs the chat conversation panel.
	 * @param chat : Chat.
	 */
	public DataPanel(Chat chat) {
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.data.setWrapStyleWord(true);
		this.data.setLineWrap(true);
		this.data.setEditable(false);

		this.add(this.data);
	}

	/**
	 * Adds data to the text area.
	 * @param s : Data added to the text area.
	 */
	public void addData(String s) {
		this.data.append(s);
	}

	/**
	 * Gets the text area where the messages are displayed.
	 * @return : Returns the text area where the messages are displayed.
	 */
	public JTextArea getData() {
		return this.data;
	}
}
