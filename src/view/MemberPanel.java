package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;

public class MemberPanel extends JPanel {
    private JTextField members;

    public MemberPanel(Chat chat) {
        this.setBackground(Color.PINK);
        this.setVisible(true);
//        this.setPreferredSize(new Dimension(800, 50));
//        this.setMaximumSize(new Dimension(800, 50));

        this.members = new JTextField();

        this.add(this.members);
    }
}
