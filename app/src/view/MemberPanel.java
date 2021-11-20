package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MemberPanel extends JPanel {
    private JMenuBar members;
    private Chat chat;

    public MemberPanel(Chat chat) {
        this.setBackground(Color.PINK);
        this.setLayout(new GridLayout(0,1));

        this.chat = chat;

        this.members = new JMenuBar();
        this.members.setLayout(new GridLayout(0,1));
        JMenu menu = new JMenu("Members");
        menu.setEnabled(false);
        this.members.add(menu);
        this.add(this.members);
    }

    public void addMember(String channel) {
        JMenu menu = new JMenu(channel);
        menu.addMenuListener(this.chat);
        this.members.add(menu);
    }

    public JMenuBar getMembers() {
        return members;
    }
}
