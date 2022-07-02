package ui.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import logic.Logic;
import player.Computer;
import player.Me;
import util.Sound;

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
		turnsLabel.setBounds(100, 490, 150, 30);
		winlose.setBounds(100, 200, 500, 100);
		winlose.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));
		winlose.setHorizontalAlignment(JLabel.CENTER);
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

	public void finished(Logic logic, Me me, Computer computer) {
		Sound sounds = new Sound();
		
		myResultScoreLabel.setText("自分の総スコア : " + String.valueOf(me.getScore()));
		computerResultScoreLabel.setText("相手の総スコア : " + String.valueOf(computer.getScore()));
		myResultPassesLabel.setText("自分の総パス回数 : " + String.valueOf(me.getPasses()));
		computerResultPassesLabel.setText("相手の総パス回数 : " + String.valueOf(me.getPasses()));
		turnsLabel.setText(String.valueOf(logic.turns) + "ターンで終了");
		if (me.getScore() > computer.getScore()) {
		    sounds.setFile(5);
		    sounds.play();
			
		    winlose.setText("勝利");
		    winlose.setForeground(Color.ORANGE);
		} else if (me.getScore() < computer.getScore()) {
			sounds.setFile(2);
			sounds.play();
			
			winlose.setText("敗北");
			winlose.setForeground(Color.BLUE);
		} else if (me.getScore() == computer.getScore()) {
			sounds.setFile(1);
			sounds.play();
			
			winlose.setText("相討ち");
		}
		
		finishButton.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	sounds.setFile(0);
	        	sounds.play();
	        	
	            Component c = (Component) e.getSource();
	            Window w = SwingUtilities.getWindowAncestor(c);
	            w.dispose();
	        }
	    });
		
		gameRestart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
            	
                Component c = (Component) e.getSource();
                Window w = SwingUtilities.getWindowAncestor(c);
                w.dispose();

                View view = new View("othello");
                view.main(null);
            }
        });
	}
}
