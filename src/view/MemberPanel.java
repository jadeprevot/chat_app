package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;

public class MemberPanel extends JPanel {
    private JTextField members;

    public MemberPanel(Chat chat) {
        this.setBackground(Color.PINK);
        this.setVisible(true);
        this.setMaximumSize(new Dimension(200, 10000));
        this.setPreferredSize(new Dimension(200, 10000));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.setPreferredSize(new Dimension(800, 50));
//        this.setMaximumSize(new Dimension(800, 50));

        this.members = new JTextField();

        this.add(this.members);
    }
}
