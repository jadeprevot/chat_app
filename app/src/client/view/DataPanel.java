package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
	JTextArea data = new JTextArea("");

	public DataPanel(Chat chat) {
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.data.setWrapStyleWord(true);
		this.data.setLineWrap(true);
		this.data.setEditable(false);

		this.add(this.data);
	}

	public void addData(String s) {
		this.data.append(s);
	}

	public JTextArea getData() {
		return this.data;
	}
}
