package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
	public DataPanel(Chat chat) {
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
}
