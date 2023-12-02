package com.example.ui.view;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.player.Me;
import com.example.player.PlayerBase;
import com.example.util.Img;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class GamingView {
	Img imgs;
	
    public JLabel bStone;
    public JLabel wStone;
    JLabel myStoneLabel;
    JLabel comStoneLabel;
    public JLabel emptyFrame;
    public JLabel myTurnLabel;
    public JLabel computerTurnLabel;
    public JLabel myScoreLabel;
    public JLabel myPassesLabel;
    public JLabel computerScoreLabel;
    public JLabel computerPassesLabel;
    public JLabel labelb;
    public JLabel canntPlacingError;
    public JLabel canntPassError;
    public JLabel enemyPassedLabel;
    public JLabel llLliB[][];
    public JLabel llLliW[][];
    public JButton passButton;
    public ImageIcon icon3;
    public ImageIcon bStoneIcon;
    public ImageIcon wStoneIcon;
    ImageIcon bStoneIcon2;
    ImageIcon wStoneIcon2;
    public ImageIcon placingError;
    public ImageIcon passError;
    public ImageIcon enemyPassed;
    public boolean isfinish;
    String MS_GOTHIC = "ＭＳ ゴシック";

    public GamingView(JPanel panel3,PlayerBase me, PlayerBase computer) {
        llLliB = new JLabel[8][8];
        llLliW = new JLabel[8][8];
        
        myTurnLabel = new JLabel("あなたのターン");
        myTurnLabel.setFont(new Font(MS_GOTHIC, Font.BOLD, 20));
        computerTurnLabel = new JLabel("相手のターン");
        computerTurnLabel.setFont(new Font(MS_GOTHIC, Font.BOLD, 20));
        
        imgs = new Img();
        bStoneIcon = new ImageIcon(new ImageIcon(imgs.img[0]).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        wStoneIcon = new ImageIcon(new ImageIcon(imgs.img[7]).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        bStoneIcon2 = new ImageIcon(new ImageIcon(imgs.img[0]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        wStoneIcon2 = new ImageIcon(new ImageIcon(imgs.img[7]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        icon3 = new ImageIcon(new ImageIcon(imgs.img[2]).getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        placingError = new ImageIcon(new ImageIcon(imgs.img[9]).getImage().getScaledInstance(200, 80, Image.SCALE_DEFAULT));
        passError = new ImageIcon(new ImageIcon(imgs.img[10]).getImage().getScaledInstance(200, 80, Image.SCALE_DEFAULT));
        enemyPassed = new ImageIcon(new ImageIcon(imgs.img[11]).getImage().getScaledInstance(200, 80, Image.SCALE_DEFAULT));
        
        panel3.setLayout(null);
        bStone = new JLabel();
        wStone = new JLabel();
        emptyFrame = new JLabel(icon3);
        emptyFrame.setBounds(100, 100, 500, 500);
    
        labelb = new JLabel();
        labelb.setBounds(100, 100, 500, 500);

        myScoreLabel = new JLabel(me.name + "のスコア : " + me.getScore());
        myScoreLabel.setBounds(100, 650, 200, 30);
        myScoreLabel.setFont(new Font(MS_GOTHIC, Font.PLAIN, 20));
        computerScoreLabel = new JLabel(computer.name + "のスコア : " + computer.getScore());
        computerScoreLabel.setBounds(400, 650, 200, 30);
        computerScoreLabel.setFont(new Font(MS_GOTHIC, Font.PLAIN, 20));
        myPassesLabel = new JLabel(me.name + "のパス回数 : " + me.getPasses());
        myPassesLabel.setBounds(100, 680, 150, 30);
        myPassesLabel.setFont(new Font(MS_GOTHIC, Font.PLAIN, 15));
        computerPassesLabel = new JLabel(computer.name + "のパス回数 : " + computer.getPasses());
        computerPassesLabel.setBounds(400, 680, 150, 30);
        computerPassesLabel.setFont(new Font(MS_GOTHIC, Font.PLAIN, 15));

        // 全てのコマを配置
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                llLliB[i][j] = new JLabel(bStoneIcon);
                llLliB[i][j].setVerticalAlignment(JLabel.CENTER);
                llLliB[i][j].setBounds(105 + 63 * i, 105 + 63 * j, 50, 50);
                llLliB[i][j].setVisible(false);
                panel3.add(llLliB[i][j]);

                llLliW[i][j] = new JLabel(wStoneIcon);
                llLliW[i][j].setVerticalAlignment(JLabel.CENTER);
                llLliW[i][j].setBounds(105 + 63 * i, 105 + 63 * j, 50, 50);
                llLliW[i][j].setVisible(false);
                panel3.add(llLliW[i][j]);

            }
        }
        // コマの初期配置の表示
        llLliB[3][4].setVisible(true);
        llLliB[4][3].setVisible(true);
        llLliW[3][3].setVisible(true);
        llLliW[4][4].setVisible(true);

        canntPlacingError = createNotificationLabel(placingError);
        canntPassError = createNotificationLabel(passError);

        //通知表示の設定
        enemyPassedLabel = createNotificationLabel(enemyPassed);

        // パスのボタン
        passButton = new JButton("パスをする");
        passButton.setBounds(100, 710, 100, 50);

        // 手番の表示
        myTurnLabel.setBounds(400, 50, 150, 30);
        computerTurnLabel.setBounds(400, 50, 150, 30);
        
        // テスト用
        JLabel num = new JLabel(
                "0              1              2               3              4              5              6             7");
        num.setBounds(100, 80, 500, 50);
//        panel3.add(num);
        
        panel3.add(myScoreLabel);
        panel3.add(computerScoreLabel);
        panel3.add(myPassesLabel);
        panel3.add(computerPassesLabel);
        panel3.add(canntPlacingError);
        panel3.add(canntPassError);
        panel3.add(passButton);      
        panel3.add(myTurnLabel);
        panel3.add(computerTurnLabel);
        panel3.add(labelb);
        panel3.add(emptyFrame);
        panel3.add(enemyPassedLabel);
    }

    private JLabel createNotificationLabel(ImageIcon imageIcon) {
            JLabel label = new JLabel(imageIcon);
            label.setBounds(100, 10, 200, 80);
            label.setVisible(false);
            return label;
    }

    public void started(Me me,JPanel panel3) {
    	// 初期配置の表示
        myStoneLabel = new JLabel(me.getFirst() ? bStoneIcon2 : wStoneIcon2);
        comStoneLabel = new JLabel(!me.getFirst() ? bStoneIcon2 : wStoneIcon2);

        myStoneLabel.setBounds(70, 650, 30, 30);
        comStoneLabel.setBounds(370, 650, 30, 30);

        myTurnLabel.setVisible(me.getFirst());
        computerTurnLabel.setVisible(!me.getFirst());
        
        panel3.add(myStoneLabel);
        panel3.add(comStoneLabel);
	}

    public void cannotPlacingError() {
    	canntPlacingError.setVisible(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                canntPlacingError.setVisible(false);
            }
        }, 5000);
	}
    
    public void placeAnimation(int[] decidedPosition,PlayerBase player) {
    	int x = decidedPosition[0];
    	int y = decidedPosition[1];
    	
    	//コマの種類を判定
        JLabel lliitem = player.getFirst() ? llLliB[x][y] : llLliW[x][y];
        lliitem.setVisible(true);

        JLabel myScoreJLabel =  player.id == 0 ? myScoreLabel : computerScoreLabel;
        JLabel myTurnJLabel = player.id == 0 ? myTurnLabel : computerTurnLabel;
        JLabel enemyTurnJLabel = player.id == 0 ? computerTurnLabel : myTurnLabel;
    	
    	myScoreJLabel.setText(player.name + "のスコア : " + player.getScore());
        myTurnJLabel.setVisible(false);
        enemyTurnJLabel.setVisible(true);
	}

    public void turnOverAnimation(PlayerBase me, PlayerBase enemy, ArrayList<ArrayList<int[]>> turnOverList, GamingView gamingView) {
        JLabel[][] lliitem = me.getFirst() ? gamingView.llLliB : gamingView.llLliW;
        JLabel[][] enemyLliitem = me.getFirst() ? gamingView.llLliW : gamingView.llLliB;
		
    	for(int i=0; i<turnOverList.size(); i++) {
			for(int j=0; j<turnOverList.get(i).size(); j++) {
				int x = turnOverList.get(i).get(j)[0];
				int y = turnOverList.get(i).get(j)[1];
				if(enemy.position[x][y] && !(me.position[x][y])) {
					//表示
			        lliitem[x][y].setVisible(true);
			        enemyLliitem[x][y].setVisible(false);
			
				}else if(me.position[x][y] && !(enemy.position[x][y])) {
					break;
				}
			}
		}
	}

    public void turnActionAnimation(int[] decidePosition,PlayerBase computer,PlayerBase me,GamingView gamingView) {
    	if(decidePosition[0] == 8 && decidePosition[1] == 8) {
    		computerPassesLabel.setText(computer.name + "のパス回数 : " + computer.getPasses());
    		enemyPassedLabel.setVisible(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    enemyPassedLabel.setVisible(false);
                }
            }, 1000);
    	} else if(
    			(boolean) computer.canPlacing(decidePosition, computer, me).get("result")
    			){
    		Map<String,Object> values = computer.canPlacing(decidePosition, computer, me);
        	ArrayList<ArrayList<int[]>> turnOverList = (ArrayList<ArrayList<int[]>>) values.get("turnPosition");
        	
            placeAnimation(decidePosition,computer);
            turnOverAnimation(computer, me, turnOverList, gamingView);
    	} else {
    		computerPassesLabel.setText(computer.name + "のパス回数 : " + computer.getPasses());
    	}
	}
}
