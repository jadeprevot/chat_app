package client.view;

import client.controller.Chat;
import client.view.*;

import javax.swing.*;
import java.awt.*;

/**
 * Implements the window.
 */
public class Window extends JFrame {
	/**
	 * Connexion panel used by the window.
	 */
	private ConnexionPanel connexionPanel;
	/**
	 *  Data panel used by the window.
	 */
	private DataPanel dataPanel;
	/**
	 * Chat panel used by the window.
	 */
	private ChatPanel chatPanel;
	/**
	 * Channel panel used by the window.
	 */
	private ChannelPanel channelPanel;
	/**
	 * Member panel used by the window.
	 */
	private MemberPanel memberPanel;

	/**
	 * Constructs the window.
	 * @param chat: Chat.
	 */
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

	/**
	 * Gets the connexion panel.
	 * @return : Returns the connexion panel.
	 */
	public ConnexionPanel getConnexionPanel() {
		return this.connexionPanel;
	}

	/**
	 *Gets the data panel.
	 * @return : Returns the data panel.
	 */
	public DataPanel getDataPanel() {
		return this.dataPanel;
	}

	/**
	 * Gets the chat panel.
	 * @return : Returns the chat panel.
	 */
	public ChatPanel getChatPanel() {
		return this.chatPanel;
	}

	/**
	 * Gets the channel panel.
	 * @return : Returns the channel panel.
	 */
	public ChannelPanel getChannelPanel() {
		return this.channelPanel;
	}

	/**
	 * Gets the member panel.
	 * @return : Returns the member panel.
	 */
	public MemberPanel getMemberPanel() {
		return this.memberPanel;
	}
}
