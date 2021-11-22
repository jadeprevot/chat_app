package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MemberPanel extends JPanel {
    private JMenuBar menu;
    private List<JButton> members;
    private Chat chat;

    public MemberPanel(Chat chat) {
        this.setBackground(Color.PINK);
        this.setLayout(new GridLayout(0,1));

        this.chat = chat;

        this.members = new ArrayList<>();

        this.menu = new JMenuBar();
        this.menu.setLayout(new GridLayout(0,1));
        JButton menu = new JButton("Members");
        menu.setEnabled(false);
        this.menu.add(menu);
        this.add(this.menu);
    }

    public void addMember(String channel, String user) {
        JButton menu = new JButton(channel);
        menu.addActionListener(this.chat);
        if (channel.equals(user)) {
            menu.setEnabled(false);
        }
        this.menu.add(menu);
        this.members.add(menu);
    }

    public JMenuBar getMenu() {
        return this.menu;
    }

    public List<JButton> getMembers() {
        return this.members;
    }

    public void setMembers(List<JButton> members) {
        this.members = members;
    }
}
