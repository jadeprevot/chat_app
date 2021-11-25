package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MemberPanel extends JPanel {
    private JMenuBar menu;
    private List<JButton> members;
    private Chat chat;
    private JButton refresh;

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

    public JButton getRefresh() {
        return this.refresh;
    }

    public void setRefresh(JButton refresh) {
        this.refresh = refresh;
    }
}
