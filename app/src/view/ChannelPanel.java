package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;

public class ChannelPanel extends JPanel {
    private JMenuBar channels;
    private Chat chat;

    public ChannelPanel(Chat chat) {
        this.setBackground(Color.ORANGE);
        this.setLayout(new GridLayout(0,1));

        this.chat = chat;

        this.channels = new JMenuBar();
        this.channels.setLayout(new GridLayout(0,1));
        JMenu menu = new JMenu("Channels");
        menu.setEnabled(false);
        this.channels.add(menu);
        this.add(this.channels);
    }

    public void addChannel(String channel) {
        JMenu menu = new JMenu(channel);
        menu.addMenuListener(this.chat);
        this.channels.add(menu);

    }

    public JMenuBar getChannels() {
        return channels;
    }
}
