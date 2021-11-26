package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the UI representing the online members.
 */
public class MemberPanel extends JPanel {
    /**
     * Represents the menu of online members.
     */
    private JMenuBar menu;
    /**
     * Represents the list of online members.
     */
    private List<JButton> members;
    /**
     * Represents the chat.
     */
    private Chat chat;
    /**
     * Represents the button to refresh the list of online members.
     */
    private JButton refresh;

    /**
     * Constructs the members panel.
     * @param chat : Chat.
     */
    public MemberPanel(Chat chat) {
        this.setBackground(Color.PINK);
        this.setLayout(new GridLayout(0,1));

        this.chat = chat;

        this.members = new ArrayList<>();

        this.menu = new JMenuBar();
        this.menu.setLayout(new GridLayout(0,1));

        this.refresh = new JButton("Members");
        this.refresh.addActionListener(this.chat);
        this.menu.add(this.refresh);

        this.add(this.menu);
    }

    /**
     * Adds a member online to the menu.
     * @param member : Member added to the menu.
     * @param user : Actual user.
     */
    public void addMember(String member, String user) {
        JButton menu = new JButton(member);
        menu.addActionListener(this.chat);
        if (member.equals(user)) {
            menu.setEnabled(false);
        }
        this.menu.add(menu);
        this.members.add(menu);
    }

    /**
     * Gets the menu of online members.
     * @return : Returns the menu of online members.
     */
    public JMenuBar getMenu() {
        return this.menu;
    }

    /**
     * Gets the list of online members.
     * @return : Returns the list of online members.
     */
    public List<JButton> getMembers() {
        return this.members;
    }

    /**
     * Sets the list of online members.
     * @param members : Online members updated.
     */
    public void setMembers(List<JButton> members) {
        this.members = members;
    }

    /**
     * Gets the button to refresh the list of online members.
     * @return : Returns the button to refresh the list of online members.
     */
    public JButton getRefresh() {
        return this.refresh;
    }

    /**
     * Sets the button to refresh the list of online members.
     * @param refresh Button to refresh the list of online  updated.
     */
    public void setRefresh(JButton refresh) {
        this.refresh = refresh;
    }
}
