package view;

import controller.Chat;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
	private JTextArea data;

	public DataPanel(Chat chat) {
		this.setBackground(Color.WHITE);
		this.setVisible(true);
//		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.data = new JTextArea();

		this.add(this.data);
	}

	public JTextArea getData() {
		return this.data;
	}
}
