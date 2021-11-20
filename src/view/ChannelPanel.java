package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChannelPanel extends JPanel {
    private List<JMenu> channels;
    private JTextField channel;
    private  JButton select;

    public ChannelPanel(Chat chat) {
        this.setBackground(Color.ORANGE);
        this.setMaximumSize(new Dimension(200, 10000));
        this.setPreferredSize(new Dimension(200, 10000));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.channel = new JTextField();
        this.add(this.channel);
        this.select = new JButton("select");
        this.add(this.select);

        this.select.addActionListener(chat);
    }

    public JTextField getChannel() {
        return this.channel;
    }

    public JButton getSelect() {
        return this.select;
    }

    public ChannelPanel(Chat chat) {
        this.setBackground(new Color(217, 227, 239));
        this.setVisible(true) ;
        this.setPreferredSize(new Dimension(170, 800));
        this.setMaximumSize(new Dimension(170, 800));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(0,1));
        JMenu menuOne = new JMenu("Menu 1");
        menuBar.add(menuOne);
        menuOne.addActionListener(chat);
        JMenu menuTwo = new JMenu("Menu 1");
        menuBar.add(menuTwo);
        JMenu menuThree = new JMenu("Menu 1");
        menuBar.add(menuThree);

        this.add(menuBar);
    }

    public List<JMenu> getChannels() {
        return channels;
    }
}
