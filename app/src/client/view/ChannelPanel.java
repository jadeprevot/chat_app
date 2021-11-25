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
    private JButton leave;
    private JButton create;
    private JTextField input;

    public ChannelPanel(Chat chat) {
        this.setBackground(Color.ORANGE);
        this.setLayout(new GridLayout(0,1));

        this.chat = chat;

        this.channels = new ArrayList<>();

        this.menu = new JMenuBar();
        this.menu.setLayout(new GridLayout(0,1));

        this.leave = new JButton("Channels");
        this.leave.addActionListener(this.chat);
        this.menu.add(this.leave);
        this.add(this.menu);
    }

    public void addChannel(String channel, boolean b) {
        JButton menu = new JButton(channel);
        menu.setEnabled(b);
        menu.addActionListener(this.chat);
        this.channels.add(menu);
        this.menu.add(menu);
    }

    public List<JButton> getChannels() {
        return this.channels;
    }

    public JButton getLeave() {
        return this.leave;
    }

    public JButton getCreate() { return this.create; }

    public JTextField getInput() { return this.input; }

    public JMenuBar getMenu() {
        return this.menu;
    }

    public void setChannels(List<JButton> channels) {
        this.channels = channels;
    }

    public void setLeave(JButton leave) {
        this.leave = leave;
    }

    public void setCreate(JButton create) {
        this.create = create;
    }

    public void setInput(JTextField input) {
        this.input = input;
    }
}
