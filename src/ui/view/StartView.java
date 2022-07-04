package ui.view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Sound;

public class StartView {
	JButton openButton;
	JLabel label;
	Sound sounds;
	
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
		
		openButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
            }
        });
	}
}
