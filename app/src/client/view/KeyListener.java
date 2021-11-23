package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class KeyListener implements ActionListener {

    private JButton send;

    public KeyListener(ChatPanel chatPanel){
        this.send = chatPanel.getSend();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component frame = new JFrame();
        //TODO
    }

    public void keyPressed(KeyEvent e) {
        Component frame = new JFrame();
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ((JFrame) frame).getRootPane().setDefaultButton(send);
        }
    }
}
