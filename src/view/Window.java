package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	private ConnexionPanel connexionPanel;
	private DataPanel dataPanel;
	private ChatPanel chatPanel;

	public Window(Chat chat) {
		this.setSize(new Dimension(800, 800));
		this.setMinimumSize(new Dimension(500, 500));
		this.setLocation(new Point(5, 30));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Chat");
		this.getContentPane().setBackground(Color.YELLOW);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.connexionPanel = new ConnexionPanel(chat);
		this.getContentPane().add(connexionPanel);
		this.dataPanel = new DataPanel(chat);
		this.getContentPane().add(dataPanel);
		this.chatPanel = new ChatPanel(chat);
		this.getContentPane().add(chatPanel);

		this.setVisible(true);
	}

	public ConnexionPanel getConnexionPanel() {
		return this.connexionPanel;
	}

	public DataPanel getDataPanel() {
		return this.dataPanel;
	}

	public ChatPanel getChatPanel() {
		return this.chatPanel;
	}
}
