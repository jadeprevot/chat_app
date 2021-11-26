package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;

/**
 * Implements the UI of the connexion.
 */
public class ConnexionPanel extends JPanel {
	/**
	 * Represents a text field to input the username.
	 */
	private JTextField username;
	/**
	 * Represents a text field to input the password.
	 */
	private JTextField password;
	/**
	 * Represents the button to connect.
	 */
	private JButton connexion;

	/**
	 * Constructs the connexion panel.
	 * @param chat : Chat.
	 */
	public ConnexionPanel(Chat chat) {
		this.setBackground(new Color(80, 95, 248));
		this.setVisible(true);
		this.setPreferredSize(new Dimension(800, 50));
		this.setMaximumSize(new Dimension(800, 50));

		JLabel jLabelHostname = new JLabel("Username");
		JLabel jLabelPort = new JLabel("Password");

		this.username = new JTextField(20);
		this.password = new JTextField(10);
		this.connexion = new JButton("Connexion");

		this.add(jLabelHostname);
		this.add(this.username);
		this.add(jLabelPort);
		this.add(this.password);
		this.add(this.connexion);

		this.connexion.addActionListener(chat);
	}

	/**
	 * Gets the text field to input the username.
	 * @return : Returns the text field to input the username.
	 */
	public JTextField getUsername() {
		return this.username;
	}

	/**
	 * Gets the text field to input the password.
	 * @return : Returns the text field to input the password.
	 */
	public JTextField getPassword() {
		return this.password;
	}

	/**
	 * Gets the button to connect.
	 * @return : Returns the button to connect.
	 */
	public JButton getConnexion() {
		return this.connexion;
	}
}
