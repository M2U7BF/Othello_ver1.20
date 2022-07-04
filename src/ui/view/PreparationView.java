package ui.view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import logic.Logic;
import player.Computer;
import player.Me;
import util.Sound;

public class PreparationView {
	public Sound sounds;
	public Logic logic;

	public JLabel orderLabel;
	public JButton orderButton;
	public JButton startButton;
	public JRadioButton[] dificultyRadio;
	private boolean clicked = false;
	ButtonGroup bgroup;

	PreparationView(JPanel panel2, Me me, Computer computer) {
		panel2.setLayout(null);
		orderLabel = new JLabel();
		orderLabel.setBounds(270, 310, 200, 30);
		orderLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
		orderButton = new JButton("順番を決める");
		orderButton.setBounds(100, 300, 150, 50);
		orderButton.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 15));
		startButton = new JButton("ゲームスタート");
		startButton.setBounds(400, 500, 150, 50);
		startButton.setVisible(false);
		orderLabel.setVisible(false);
		dificultyRadio = new JRadioButton[2];
		dificultyRadio[1] = new JRadioButton("ふつう",true);
		dificultyRadio[0] = new JRadioButton("やさしい");
		dificultyRadio[0].setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 20));
		dificultyRadio[1].setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 20));
		dificultyRadio[1].setBounds(100, 200, 150, 50);
		dificultyRadio[0].setBounds(100, 230, 150, 50);
		bgroup = new ButtonGroup();
		bgroup.add(dificultyRadio[0]);
		bgroup.add(dificultyRadio[1]);
		
		panel2.add(orderLabel);
		panel2.add(startButton);
		panel2.add(orderButton);
		panel2.add(dificultyRadio[0]);
		panel2.add(dificultyRadio[1]);
		

		orderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!(clicked)) {
					sounds = new Sound();
					sounds.setFile(0);
					sounds.play();
					
					logic = new Logic();
					logic.decideFirst(computer, me, orderLabel);
					orderDecided();
					
					clicked = true;
				}
			}
		});
		
		startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
            	
            	if(dificultyRadio[0].isSelected()) {
            		computer.strength = 0;
            	}else if(dificultyRadio[1].isSelected()) {
            		computer.strength = 1;
            	}
            }
        });
	}

	public void orderDecided() {
		orderLabel.setVisible(true);
		startButton.setVisible(true);
	}
}
