package view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	public Window() {
		this.setSize(new Dimension(800, 800));
		this.setMinimumSize(new Dimension(500, 500));
		this.setLocation(new Point(5, 30));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Chat");
	}
}
