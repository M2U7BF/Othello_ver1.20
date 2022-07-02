package ui.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultView {
	public JLabel myResultScoreLabel;
	public JLabel computerResultScoreLabel;
	public JLabel myResultPassesLabel;
	public JLabel computerResultPassesLabel;
	public JLabel turnsLabel;
	public JLabel winlose;
	public JButton gameRestart;
	public JButton finishButton;

	public ResultView(JPanel panel4) {
		myResultScoreLabel = new JLabel();
		computerResultScoreLabel = new JLabel();
		myResultPassesLabel = new JLabel();
		computerResultPassesLabel = new JLabel();
		turnsLabel = new JLabel();
		winlose = new JLabel();

		panel4.setLayout(null);
		myResultScoreLabel.setBounds(100, 400, 150, 30);

		computerResultScoreLabel.setBounds(350, 400, 150, 30);

		myResultPassesLabel.setBounds(100, 430, 150, 30);

		computerResultPassesLabel.setBounds(350, 430, 150, 30);

		turnsLabel.setBounds(100, 450, 150, 30);

		winlose.setBounds(250, 200, 500, 100);
		winlose.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));

		gameRestart = new JButton("新しいゲームを開始");
		gameRestart.setBounds(450, 600, 150, 30);

		finishButton = new JButton("終了する");
		finishButton.setBounds(80, 600, 80, 30);

		panel4.add(myResultScoreLabel);
		panel4.add(computerResultScoreLabel);
		panel4.add(myResultPassesLabel);
		panel4.add(computerResultPassesLabel);
		panel4.add(turnsLabel);
		panel4.add(winlose);
		panel4.add(gameRestart);
		panel4.add(finishButton);
	}
}
