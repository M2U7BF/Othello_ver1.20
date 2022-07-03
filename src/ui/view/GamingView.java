package ui.view;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import player.Me;
import player.PlayerBase;
import util.Img;

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
    public boolean isfinish;

    public GamingView(JPanel panel3,PlayerBase me, PlayerBase computer) {
        llLliB = new JLabel[8][8];
        llLliW = new JLabel[8][8];
        
        myTurnLabel = new JLabel("あなたのターン");
        computerTurnLabel = new JLabel("相手のターン");
        
        imgs = new Img();
        bStoneIcon = new ImageIcon(new ImageIcon(imgs.img[0]).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        wStoneIcon = new ImageIcon(new ImageIcon(imgs.img[7]).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        bStoneIcon2 = new ImageIcon(new ImageIcon(imgs.img[0]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        wStoneIcon2 = new ImageIcon(new ImageIcon(imgs.img[7]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        icon3 = new ImageIcon(new ImageIcon(imgs.img[2]).getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        placingError = new ImageIcon(new ImageIcon(imgs.img[9]).getImage().getScaledInstance(200, 80, Image.SCALE_DEFAULT));
        passError = new ImageIcon(new ImageIcon(imgs.img[10]).getImage().getScaledInstance(200, 80, Image.SCALE_DEFAULT));

        panel3.setLayout(null);
        bStone = new JLabel();
        wStone = new JLabel();
        emptyFrame = new JLabel(icon3);
        emptyFrame.setBounds(100, 100, 500, 500);
    
        labelb = new JLabel();
        labelb.setBounds(100, 100, 500, 500);

        myScoreLabel = new JLabel(me.name + "のスコア : " + String.valueOf(me.getScore()));
        myScoreLabel.setBounds(100, 650, 150, 30);
        computerScoreLabel = new JLabel(computer.name + "のスコア : " + String.valueOf(computer.getScore()));
        computerScoreLabel.setBounds(400, 650, 150, 30);
        myPassesLabel = new JLabel(me.name + "のパス回数 : " + String.valueOf(me.getPasses()));
        myPassesLabel.setBounds(100, 680, 150, 30);
        computerPassesLabel = new JLabel(computer.name + "のパス回数 : " + String.valueOf(computer.getPasses()));
        computerPassesLabel.setBounds(400, 680, 150, 30);

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

        // エラー文の設定
        canntPlacingError = new JLabel(placingError);
        canntPlacingError.setBounds(100, 10, 200, 80);
        canntPlacingError.setVisible(false);
        canntPassError = new JLabel(passError);
        canntPassError.setBounds(100, 10, 200, 80);
        canntPassError.setVisible(false);

        // パスのボタン
        passButton = new JButton("パスをする");
        passButton.setBounds(100, 720, 100, 30);

        // 手番の表示
        myTurnLabel.setBounds(400, 50, 100, 30);
        computerTurnLabel.setBounds(400, 50, 100, 30);
        
        // テスト用
        JLabel num = new JLabel(
                "0              1              2               3              4              5              6             7");
        num.setBounds(100, 80, 500, 50);
        panel3.add(num);
        
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
        
        
        passButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(!(me.isMyTurn)){
            		//DO NOTHING
            	}else if (!(me.somewhereCanPlacing(me, computer))) {
                    me.Pass(me, computer);
                    myPassesLabel.setText(me.name + "のパス回数 : " + String.valueOf(me.getPasses()));
                } else {
                	canntPassError.setVisible(true);
                    TimerTask task = new TimerTask() {
                        public void run() {
                        	canntPassError.setVisible(false);
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(task, 1000);
                }
            }
        });
    }
    
    public void started(Me me,JPanel panel3) {
    	// 初期配置の表示
        if (me.getFirst()) {
            myStoneLabel = new JLabel(bStoneIcon2);
            comStoneLabel = new JLabel(wStoneIcon2);
        } else {
            myStoneLabel = new JLabel(wStoneIcon2);
            comStoneLabel = new JLabel(bStoneIcon2);
        }
        myStoneLabel.setBounds(70, 650, 30, 30);
        comStoneLabel.setBounds(370, 650, 30, 30);

        if(me.getFirst()) {
            myTurnLabel.setVisible(true);
            computerTurnLabel.setVisible(false);
        }else if(!(me.getFirst())) {
        	myTurnLabel.setVisible(false);
        	computerTurnLabel.setVisible(true);
        }
        
        panel3.add(myStoneLabel);
        panel3.add(comStoneLabel);
	}

    public void canntPlacingError() {
    	canntPlacingError.setVisible(true);
        TimerTask task = new TimerTask() {
            public void run() {
            	canntPlacingError.setVisible(false);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000);
	}
    
    public void placeAnimation(int[] decidedPosition,PlayerBase player) {
    	JLabel myScoreJLabel = new JLabel();
    	JLabel myTurnJLabel = new JLabel();
    	JLabel enemyTurnJLabel = new JLabel();
    	int x = decidedPosition[0];
    	int y = decidedPosition[1];
    	
    	//コマの種類を判定
    	if (player.getFirst()) {
            JLabel lliitem = llLliB[x][y];
            lliitem.setVisible(true);
        } else {
            JLabel lliitem = llLliW[x][y];
            lliitem.setVisible(true);
        }
    	
    	
    	if(player.id == 0) {
    		myScoreJLabel =  myScoreLabel;
    		
    		myTurnJLabel = myTurnLabel;
    		enemyTurnJLabel = computerTurnLabel;
    	}else {
    		myScoreJLabel = computerScoreLabel;
    		
    		myTurnJLabel = computerTurnLabel;
    		enemyTurnJLabel = myTurnLabel;
    	}
    	
    	myScoreJLabel.setText(player.name + "のスコア : " + String.valueOf(player.getScore()));
        myTurnJLabel.setVisible(false);
        enemyTurnJLabel.setVisible(true);
	}

    public void turnOverAnimation(PlayerBase me, PlayerBase enemy, ArrayList<ArrayList<int[]>> turnOverList, GamingView gamingView) {
		JLabel[][] lliitem = new JLabel[8][8];
		JLabel[][] enemyLliitem = new JLabel[8][8];

    	if (me.getFirst()) {
            lliitem = gamingView.llLliB;
            enemyLliitem = gamingView.llLliW;
        } else if (!(me.getFirst())){
            lliitem = gamingView.llLliW;
            enemyLliitem = gamingView.llLliB;
        }
		
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
}
