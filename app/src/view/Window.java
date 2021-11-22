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
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Chat");
		this.getContentPane().setBackground(Color.YELLOW);
		this.getContentPane().setLayout(new BorderLayout());

		this.connexionPanel = new ConnexionPanel(chat);
		this.getContentPane().add(connexionPanel, BorderLayout.NORTH);

		this.channelPanel = new ChannelPanel(chat);
		JScrollPane channelScrollPanel = new JScrollPane(this.channelPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		channelScrollPanel.getVerticalScrollBar().setUnitIncrement(30);
		this.getContentPane().add(channelScrollPanel, BorderLayout.WEST);

		this.dataPanel = new DataPanel(chat);
		JScrollPane dataScrollPanel = new JScrollPane(this.dataPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dataScrollPanel.getVerticalScrollBar().setUnitIncrement(30);
		this.getContentPane().add(dataScrollPanel, BorderLayout.CENTER);

		this.memberPanel = new MemberPanel(chat);
		JScrollPane memberScrollPanel = new JScrollPane(this.memberPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		memberScrollPanel.getVerticalScrollBar().setUnitIncrement(30);
		this.getContentPane().add(memberScrollPanel, BorderLayout.EAST);

		this.chatPanel = new ChatPanel(chat);
		this.getContentPane().add(chatPanel, BorderLayout.SOUTH);

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

	public MemberPanel getMemberPanel() {
		return this.memberPanel;
	}
}
