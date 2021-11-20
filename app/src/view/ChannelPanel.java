package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChannelPanel extends JPanel {
    private List<JMenu> channelList;
    private JMenuBar channels;
    private Chat chat;

    public ChannelPanel(Chat chat) {
        this.setBackground(Color.ORANGE);
        this.setMaximumSize(new Dimension(200, 10000));
        this.setPreferredSize(new Dimension(200, 10000));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.chat = chat;

        this.channels = new JMenuBar();
        this.channels.setLayout(new GridLayout(0,1));
        this.channels.add(new JMenu("Channels:"));
        this.add(this.channels);

        this.channelList = new ArrayList<>();
    }

    public void addChannel(String channel) {
        JMenu menu = new JMenu(channel);
        menu.addMenuListener(this.chat);
        this.channels.add(menu);

    }

    public List<JMenu> getChannelList() {
        return this.channelList;
    }

    public JMenuBar getChannels() {
        return channels;
    }
}


//        JMenuBar menuBar = new JMenuBar();
//        menuBar.setLayout(new GridLayout(0,1));
//        JMenu menuOne = new JMenu("Menu 1");
//        menuBar.add(menuOne);
//        menuOne.addActionListener(chat);
//        JMenu menuTwo = new JMenu("Menu 1");
//        menuBar.add(menuTwo);
//        JMenu menuThree = new JMenu("Menu 1");
//        menuBar.add(menuThree);


//        this.channel = new JTextField();
//        this.add(this.channel);
//        this.select = new JButton("select");
//        this.add(this.select);
//
//        this.select.addActionListener(chat);