package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	private ConnexionPanel connexionPanel;
	private DataPanel dataPanel;
	private ChatPanel chatPanel;
	private ChannelPanel channelPanel;
	private MemberPanel memberPanel;

	public Window(Chat chat) {
		this.setSize(new Dimension(800, 800));
		this.setMinimumSize(new Dimension(500, 500));
		this.setLocation(new Point(5, 30));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Chat");
		this.getContentPane().setBackground(new Color(79, 118, 157));
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.connexionPanel = new ConnexionPanel(chat);
		this.getContentPane().add(connexionPanel);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		this.channelPanel = new ChannelPanel(chat);
		panel.add(channelPanel);
		this.dataPanel = new DataPanel(chat);
		panel.add(dataPanel);
		this.memberPanel = new MemberPanel(chat);
		panel.add(memberPanel);
		this.getContentPane().add(panel);
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

	public ChannelPanel getChannelPanel(){
		return this.channelPanel;
	}
}
