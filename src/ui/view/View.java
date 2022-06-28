package ui.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import player.Computer;
import player.Me;

public class View extends JFrame {

    JButton openButton;
    JButton orderButton;
    JButton startButton;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel orderLabel;
    JLabel bStone;
    JLabel wStone;
    JLabel emptyFrame;
    CardLayout layout;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    int placedPosition[] = new int[2];
    JLabel[][] lli = new JLabel[8][8];
    JLabel llLliB[][] = new JLabel[8][8];
	JLabel llLliW[][] = new JLabel[8][8];
	int order;
    
    public static String title = "othello"; 

    public static void main(String[] args) {
        View frame = new View("go");
        frame.setVisible(true);
    }
    
    private Me me;
    private Computer computer;
    private Random rand;
    

    View(String title) {
    	
    	me = new Me();
    	computer = new Computer();
    	rand = new Random();
    	
    	 ImageIcon bStoneIcon = new ImageIcon(new ImageIcon("src/util/img/blackStone.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
         ImageIcon wStoneIcon = new ImageIcon(new ImageIcon("src/util/img/whiteStone.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
         ImageIcon bStoneIcon2 = new ImageIcon(new ImageIcon("src/util/img/blackStone.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
         ImageIcon wStoneIcon2 = new ImageIcon(new ImageIcon("src/util/img/whiteStone.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
         ImageIcon icon3 = new ImageIcon(new ImageIcon("src/util/img/board.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
    	
        setTitle(title);
        setBounds(200, 100, 700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1 = new JPanel();
        panel1.setLayout(null);
        openButton = new JButton("はじめる");
        openButton.setBounds(300, 500, 100, 50);
        openButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layout.show(getContentPane(), "panel2");
            }
        });
        JLabel label = new JLabel("OTHELLO");
	    label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
	    label.setHorizontalAlignment(JLabel.CENTER);
	    label.setBounds(150, 200, 400, 100);
	    panel1.add(openButton);
	    panel1.add(label);
	    
	    panel2 = new JPanel();
	    panel2.setLayout(null);
	    orderLabel = new JLabel();
	    orderLabel.setBounds(200, 300, 400, 50);
	    orderLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
	    orderButton = new JButton("順番を決める");
	    orderButton.setBounds(300, 500, 100, 50);
	    orderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	//順番をランダムに決定
            	order = rand.nextInt(2);
            	if(order == 1) {
	            	me.setFirst(true);
	            	computer.setFirst(false);
	            	orderLabel.setText("あなたは : 先手");
            	} else if(order == 0) {
            		me.setFirst(false);
	            	computer.setFirst(true);
	            	orderLabel.setText("あなたは : 後手");
            	}
            	System.out.println("order=" + String.valueOf(order));
            	System.out.println("私の順番は : " + me.getFirst() + " になりました");
            	System.out.println("comの順番は : " + computer.getFirst() + " になりました");
            	
            	orderLabel.setVisible(true);
                startButton.setVisible(true);
                orderButton.setVisible(false);
            }
        });
	    startButton = new JButton("ゲームスタート");
	    startButton.setBounds(400, 500, 150, 50);
	    startButton.setVisible(false);
	    orderLabel.setVisible(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layout.show(getContentPane(), "panel3");
                
                if(me.getFirst()) {
                	computer.position[3][3] = true;
        			computer.position[4][4] = true;
        			me.position[3][4] = true;
        			me.position[4][3] = true;
        		}else {
        			computer.position[4][3] = true;
        			computer.position[3][4] = true;
        			me.position[3][3] = true;
        			me.position[4][4] = true;
        		}
                
                JLabel myStoneLabel;
        		JLabel comStoneLabel;
        		
        		if(me.getFirst()) {
        			System.out.println("自分の石を黒で");
        			myStoneLabel = new JLabel(bStoneIcon2);
        			comStoneLabel = new JLabel(wStoneIcon2);
        		} else {
        			System.out.println("自分の石を白で");
        			myStoneLabel = new JLabel(wStoneIcon2);
        			comStoneLabel = new JLabel(bStoneIcon2);
        		}
        		myStoneLabel.setBounds(70,650,30,30);
        		comStoneLabel.setBounds(370,650,30,30);
        		panel3.add(myStoneLabel);
        		panel3.add(comStoneLabel);
            }
        });
        panel2.add(orderLabel);
        panel2.add(startButton);
        panel2.add(orderButton);
        

        panel3 = new JPanel();
        panel3.setLayout(null);
        bStone = new JLabel();
        wStone = new JLabel();
        emptyFrame = new JLabel(icon3);
		emptyFrame.setBounds(100, 100, 500, 500);
		
		JLabel labela = new JLabel("座標を表示");
		labela.setBounds(100, 100, 500, 500);
		JLabel labelb = new JLabel("座標を表示");
		labelb.setBounds(0, 0, 700, 800);
		
		
		JLabel myScoreLabel = new JLabel("自分のスコア : "+ String.valueOf(me.getScore()));
		myScoreLabel.setBounds(100, 650, 150, 30);
		panel3.add(myScoreLabel);
		JLabel computerScoreLabel = new JLabel("相手のスコア : "+ String.valueOf(computer.getScore()) );
		computerScoreLabel.setBounds(400, 650, 100, 30);
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				llLliB[i][j] = new JLabel(bStoneIcon);
				llLliB[i][j].setVerticalAlignment(JLabel.CENTER);
				llLliB[i][j].setBounds(105+63*i,105+63*j,50,50);
				llLliB[i][j].setVisible(false);
				panel3.add(llLliB[i][j]);
				
				llLliW[i][j] = new JLabel(wStoneIcon);
				llLliW[i][j].setVerticalAlignment(JLabel.CENTER);
				llLliW[i][j].setBounds(105+63*i,105+63*j,50,50);
				llLliW[i][j].setVisible(false);
				panel3.add(llLliW[i][j]);
				
			}
		}
//		コマの初期配置
		llLliB[3][4].setVisible(true);
		llLliB[4][3].setVisible(true);
		llLliW[3][3].setVisible(true);
		llLliW[4][4].setVisible(true);
		
		//エラー文の設定
		JLabel error = new JLabel("コマを置けません");
    	error.setBounds(200,40,200,50);
    	panel3.add(error);
    	error.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
    	error.setForeground(Color.RED);
    	error.setVisible(false);
		
//		labela.addMouseListener(new MouseAdapter() {
//		@Override
//		public void mouseClicked(MouseEvent e){
//		    Point point = e.getPoint();
//		    labela.setText("盤 : x:" + point.x + ",y:" + point.y);
//		  }
//		});
		labelb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
			    Point point2 = e.getPoint();
			    labelb.setText("全体: x:" + point2.x + ",y:" + point2.y);
			    
			    int x = point2.x;
			    int y = point2.y;
			    
			    //どのマスが押されたのか
			    for(int row =0,X1=100,Y1=160; row<8; row++,X1+=65,Y1+=65) {
				    for(int col=0,X2=100,Y2=160; col<8; col++,X2+=65,Y2+=65) {
				    	if(X2 <= x && Y2 >= x && X1 <= y && Y1 >= y) {
				    		System.out.println(String.valueOf(col) + String.valueOf(row) + "が押されました");
					    	placedPosition[0] = col;
					    	placedPosition[1] = row;
					    } 
				    }
			    }
			    
			    if(!(me.position[placedPosition[0]][placedPosition[1]]) 
			    		&& me.canPlacing(placedPosition,computer,me)
			    ) {
			    	
			    	//置く石の種類を指定し、配置
				    if(me.getFirst()) {
					    JLabel lliitem = llLliB[placedPosition[0]][placedPosition[1]];
					    lliitem.setVisible(true);
				    } else {
				    	JLabel lliitem = llLliW[placedPosition[0]][placedPosition[1]];
					    lliitem.setVisible(true);
				    }
			    	
				    //isPlaced的な判定 を trueにする
				    me.position[placedPosition[0]][placedPosition[1]] = true;
				    myScoreLabel.setText("自分のスコア : "+ String.valueOf(me.getScore()));
				    
				    ////コマを置いたときの処理
				    System.out.println(String.valueOf(placedPosition[0]) + String.valueOf(placedPosition[1]) + "に置きました。");
				    int meScore = me.getScore() +1;
				    me.setScore(meScore);
			    } else {
			    	error.setVisible(true);
			    	
			    	TimerTask task = new TimerTask() {
			    		  public void run() {
			    		    // タイマーで実行したい処理
			    			  error.setVisible(false);
			    		  }
		    		};
		    		
		    		Timer timer = new Timer();
		    		timer.schedule(task, 2000);
			    	
			    }
			}
		});
		
        emptyFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	System.out.println("clicked");
//                layout.show(getContentPane(), "panel4");
            }
        });
        
        
        panel3.add(computerScoreLabel);
        panel3.add(labela);
        panel3.add(labelb);
        panel3.add(emptyFrame);
        
        
        panel4 = new JPanel();
        label4 = new JLabel("4枚目");
        label4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layout.show(getContentPane(), "panel1");
            }
        });
        panel4.add(label4);

        layout = new CardLayout();

        Container contentPane = getContentPane();
        contentPane.setLayout(layout);
        contentPane.add(panel1, "panel1");
        contentPane.add(panel2, "panel2");
        contentPane.add(panel3, "panel3");
        contentPane.add(panel4, "panel4");
    }

}



//import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//public class View extends JFrame implements ActionListener, MouseListener{
//	  JButton button;
//	  public static String topTitle = "titleです";
//
//	  public static void main(String args[]){
//	    View frame = new View(topTitle);
//	    frame.setVisible(true);
//	    Thread.sleep(1000);
//	    frame.change(new )
//	  }
//
//	  View(String title){
//		JPanel p = new JPanel();
//		p.setLayout(null);
//		
//	    setTitle(title);
//	    setBounds(200, 100, 700, 800);
//	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	    button = new JButton("START");
//	    button.addActionListener(this);
//	    button.setBounds(300, 500, 100, 50);
//
//	    JLabel label = new JLabel("OTHELLO");
//	    label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
//	    label.setHorizontalAlignment(JLabel.CENTER);
//	    label.setBounds(150, 200, 400, 100);
//	    
//	    p.add(button);
//	    p.add(label);
//
//	    Container contentPane = getContentPane();
//	    contentPane.add(p, BorderLayout.CENTER);
//	  }
//
//	  
//	  
//	  public void actionPerformed(ActionEvent e){
////	    JOptionPane.showMessageDialog(this, "Hello");
//	  }
//
//	  public void mouseClicked(MouseEvent e){
//	    button.doClick(500);
//	  }
//
//	  public void mouseEntered(MouseEvent e){}
//	  public void mouseExited(MouseEvent e){}
//	  public void mousePressed(MouseEvent e){}
//	  public void mouseReleased(MouseEvent e){}
//}



