package ui.view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	PreparationView(JPanel panel2, Me me, Computer computer) {
		panel2.setLayout(null);
		orderLabel = new JLabel();
		orderLabel.setBounds(200, 300, 400, 50);
		orderLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
		orderButton = new JButton("順番を決める");
		orderButton.setBounds(300, 500, 100, 50);
		startButton = new JButton("ゲームスタート");
		startButton.setBounds(400, 500, 150, 50);
		startButton.setVisible(false);
		orderLabel.setVisible(false);

		panel2.add(orderLabel);
		panel2.add(startButton);
		panel2.add(orderButton);

		orderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sounds = new Sound();
				sounds.setFile(0);
				sounds.play();
				
				logic = new Logic();
				logic.decideFirst(computer, me, orderLabel);
				orderDecided();
			}
		});
	}

	public void orderDecided() {
		orderLabel.setVisible(true);
		startButton.setVisible(true);
		orderButton.setVisible(false);
	}
}
