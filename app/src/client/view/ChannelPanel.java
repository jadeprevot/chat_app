package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChannelPanel extends JPanel {
    private JMenuBar menu;
    private List<JButton> channels;
    private Chat chat;

    public ChannelPanel(Chat chat) {
        this.setBackground(Color.ORANGE);
        this.setLayout(new GridLayout(0,1));

        this.chat = chat;

        this.channels = new ArrayList<>();

        this.menu = new JMenuBar();
        this.menu.setLayout(new GridLayout(0,1));
        JButton menu = new JButton("Channels");
        menu.setEnabled(false);
        this.menu.add(menu);
        this.add(this.menu);
    }

    public void addChannel(String channel) {
        JButton menu = new JButton(channel);
        menu.addActionListener(this.chat);
        this.channels.add(menu);
        this.menu.add(menu);
    }

    public JMenuBar getMenu() {
        return this.menu;
    }

    public List<JButton> getChannels() {
        return this.channels;
    }
}
