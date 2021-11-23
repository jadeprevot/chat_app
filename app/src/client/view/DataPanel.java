package client.view;

import client.controller.Chat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataPanel extends JPanel {
//	private HashMap<String, ArrayList<String>> data;

	public DataPanel(Chat chat) {
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//		this.data = new HashMap<>();
	}

//	public void addData(boolean isCannal, String name, String user, String message) {
//		ArrayList<String> list = this.data.get(name);
//		if (list == null) {
//			list = new ArrayList<String>();
//		}
//		list.add("\n" + user+ ": " + message);
//		this.data.put(name, list);
//	}
//
//	public HashMap<String, ArrayList<String>> getData() {
//		return this.data;
	}
}
