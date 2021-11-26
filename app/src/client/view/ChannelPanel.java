package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the UI of the channels.
 */
public class ChannelPanel extends JPanel {
    /**
     * Represents the menu of channels.
     */
    private JMenuBar menu;
    /**
     * Represents the list of channels.
     */
    private List<JButton> channels;
    /**
     * Represents the chat.
     */
    private Chat chat;
    /**
     * Represents the button to leave the channels.
     */
    private JButton leave;
    /**
     * Represents the button to create a new channel.
     */
    private JButton create;
    /**
     * Represents a text field to input the name of the new channel.
     */
    private JTextField input;

    /**
     * Constructs the channel panel.
     * @param chat : Chat.
     */
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

    /**
     * Adds a channel to the menu.
     * @param channel : Channel added to the menu.
     * @param b : Tells if the channel is available or not.
     */
    public void addChannel(String channel, boolean b) {
        JButton menu = new JButton(channel);
        menu.setEnabled(b);
        menu.addActionListener(this.chat);
        this.channels.add(menu);
        this.menu.add(menu);
    }

    /**
     * Gets the channels.
     * @return : Returns the channels.
     */
    public List<JButton> getChannels() {
        return this.channels;
    }

    /**
     * Gets the button to leave the channels.
     * @return : Returns the button to leave the channels.
     */
    public JButton getLeave() {
        return this.leave;
    }

    /**
     * Gets the button to create a new channel.
     * @return : Returns the button to create a new channel.
     */
    public JButton getCreate() {
        return this.create;
    }

    /**
     * Gets the text field to input the name of the new channel.
     * @return : Returns the text field to input the name of the new channel.
     */
    public JTextField getInput() {
        return this.input;
    }

    /**
     * Gets the menu of channels.
     * @return : Returns the meu of channels.
     */
    public JMenuBar getMenu() {
        return this.menu;
    }

    /**
     * Sets the channels.
     * @param channels : Channels updated.
     */
    public void setChannels(List<JButton> channels) {
        this.channels = channels;
    }

    /**
     * Sets the button to leave the channels.
     * @param leave : Button of leaving updated.
     */
    public void setLeave(JButton leave) {
        this.leave = leave;
    }

    /**
     * Sets the button to create a new channel.
     * @param create : Button of creation updated.
     */
    public void setCreate(JButton create) {
        this.create = create;
    }

    /**
     * Sets the text field to input the name of the new channel.
     * @param input : Text field of input updated.
     */
    public void setInput(JTextField input) {
        this.input = input;
    }
}
