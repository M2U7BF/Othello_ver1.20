package com.example.ui.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartView {
	JButton openButton;
	JLabel label;
	
	public StartView(JPanel panel1) {
		
		panel1.setLayout(null);
		openButton = new JButton("はじめる");
		openButton.setBounds(300, 500, 100, 50);
			
		label = new JLabel("OTHELLO");
		label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(150, 200, 400, 100);
		panel1.add(openButton);
		panel1.add(label);
	}
}
