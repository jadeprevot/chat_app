package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;

public class MemberPanel extends JPanel {

    public MemberPanel(Chat chat) {
        this.setBackground(Color.PINK);
        this.setVisible(true);
        this.setMaximumSize(new Dimension(200, 10000));
        this.setPreferredSize(new Dimension(200, 10000));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
