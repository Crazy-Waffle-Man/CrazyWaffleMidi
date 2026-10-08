package com.cwm.CrazyWaffleMidi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Main Window
        JFrame frame = new JFrame();
        JButton repoButton = new JButton("GitHub Repo for this Project");
        repoButton.setBounds(150, 200, 220, 50);
        repoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DesktopUtils.openWebpage(Properties.repo);
            }
        });
        frame.add(repoButton);
        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}