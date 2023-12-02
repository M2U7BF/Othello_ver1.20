package com.example.ui.view;

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

import com.example.logic.Logic;
import com.example.main.Application;
import com.example.player.Computer;
import com.example.player.Me;
import com.example.util.Sound;

public class ResultView {
	public JLabel myResultScoreLabel;
	public JLabel enemyResultScoreLabel;
	public JLabel myResultPassesLabel;
	public JLabel enemyResultPassesLabel;
	public JLabel turnsLabel;
	public JLabel winlose;
	public JButton gameRestart;
	public JButton finishButton;

	public ResultView(JPanel panel4) {
		myResultScoreLabel = new JLabel();
		enemyResultScoreLabel = new JLabel();
		myResultPassesLabel = new JLabel();
		enemyResultPassesLabel = new JLabel();
		turnsLabel = new JLabel();
		winlose = new JLabel();

		panel4.setLayout(null);
		myResultScoreLabel.setBounds(100, 400, 260, 30);
		myResultScoreLabel.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 20));
		enemyResultScoreLabel.setBounds(350, 400, 260, 30);
		enemyResultScoreLabel.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 20));
		myResultPassesLabel.setBounds(100, 450, 260, 30);
		myResultPassesLabel.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 20));
		enemyResultPassesLabel.setBounds(350, 450, 260, 30);
		enemyResultPassesLabel.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 20));
		turnsLabel.setBounds(100, 500, 200, 30);
		turnsLabel.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 20));
		winlose.setBounds(100, 200, 500, 100);
		winlose.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));
		winlose.setHorizontalAlignment(JLabel.CENTER);
		gameRestart = new JButton("新しいゲームを開始");
		gameRestart.setBounds(400, 600, 200, 60);
		gameRestart.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 15));
		finishButton = new JButton("終了する");
		finishButton.setBounds(80, 600, 120, 60);
		finishButton.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 15));

		panel4.add(myResultScoreLabel);
		panel4.add(enemyResultScoreLabel);
		panel4.add(myResultPassesLabel);
		panel4.add(enemyResultPassesLabel);
		panel4.add(turnsLabel);
		panel4.add(winlose);
		panel4.add(gameRestart);
		panel4.add(finishButton);
	}

	public void started(Logic logic, Me me, Computer computer) {
		Sound sounds = new Sound();
		
		myResultScoreLabel.setText(me.name + "の総スコア : " + me.getScore());
		enemyResultScoreLabel.setText(computer.name + "の総スコア : " + computer.getScore());
		myResultPassesLabel.setText(me.name + "の総パス回数 : " + me.getPasses());
		enemyResultPassesLabel.setText(computer.name + "の総パス回数 : " + computer.getPasses());
		turnsLabel.setText(logic.turns + "ターンで終了");
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

				Application.shutdown();
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

				Application.restart();
            }
        });
	}
}
