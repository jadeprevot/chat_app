package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;

public class ConnexionPanel extends JPanel {
	private JTextField username;
	private JTextField password;
	private JButton connexion;

	public ConnexionPanel(Chat chat) {
		this.setBackground(Color.RED);
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

	public JTextField getHostname() {
		return this.username;
	}

	public JTextField getPort() {
		return this.password;
	}

	public JButton getConnexion() {
		return this.connexion;
	}
}
