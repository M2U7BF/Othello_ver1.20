package ui.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import logic.Logic;
import player.Computer;
import player.Me;
import ui.controller.PlayingController;

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
    JLabel myTurnLabel = new JLabel("あなたのターン");
    JLabel computerTurnLabel = new JLabel("相手のターン");

    public static String title = "othello";

    public static void main(String[] args) {
        View frame = new View("othello");
        frame.setVisible(true);
    }

    private Me me;
    private Computer computer;
    private Logic logic;
    private PlayingController controller;

    View(String title) {

        me = new Me();
        computer = new Computer();
        logic = new Logic();
        controller = new PlayingController();

        ImageIcon bStoneIcon = new ImageIcon(
                new ImageIcon("src/util/img/blackStone.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon wStoneIcon = new ImageIcon(
                new ImageIcon("src/util/img/whiteStone.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon bStoneIcon2 = new ImageIcon(
                new ImageIcon("src/util/img/blackStone.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        ImageIcon wStoneIcon2 = new ImageIcon(
                new ImageIcon("src/util/img/whiteStone.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        ImageIcon icon3 = new ImageIcon(
                new ImageIcon("src/util/img/board.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));

        JLabel myResultScoreLabel = new JLabel();
        JLabel computerResultScoreLabel = new JLabel();
        JLabel myResultPassesLabel = new JLabel();
        JLabel computerResultPassesLabel = new JLabel();
        JLabel turnsLabel = new JLabel();
        JLabel winlose = new JLabel();

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
                logic.decideFirst(computer, me, orderLabel);

                // 表示処理
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

                JLabel myStoneLabel;
                JLabel comStoneLabel;

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
                panel3.add(myStoneLabel);
                panel3.add(comStoneLabel);
                
                if(me.getFirst()) {
        	        myTurnLabel.setVisible(true);
        	        computerTurnLabel.setVisible(false);
                }else if(!(me.getFirst())) {
                	myTurnLabel.setVisible(false);
        	        computerTurnLabel.setVisible(true);
                }
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

        JLabel labelb = new JLabel("座標を表示");
        labelb.setBounds(100, 100, 500, 500);

        JLabel myScoreLabel = new JLabel("自分のスコア : " + String.valueOf(me.getScore()));
        myScoreLabel.setBounds(100, 650, 150, 30);
        panel3.add(myScoreLabel);
        JLabel computerScoreLabel = new JLabel("相手のスコア : " + String.valueOf(computer.getScore()));
        computerScoreLabel.setBounds(400, 650, 150, 30);
        panel3.add(computerScoreLabel);
        JLabel myPassesLabel = new JLabel("自分のパス回数 : " + String.valueOf(me.getPasses()));
        myPassesLabel.setBounds(100, 680, 150, 30);
        panel3.add(myPassesLabel);
        JLabel computerPassesLabel = new JLabel("相手のパス回数 : " + String.valueOf(computer.getPasses()));
        computerPassesLabel.setBounds(400, 680, 150, 30);
        panel3.add(computerPassesLabel);

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
        JLabel error = new JLabel("コマを置けません");
        error.setBounds(200, 40, 200, 50);
        panel3.add(error);
        error.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
        error.setForeground(Color.RED);
        error.setVisible(false);

        // パスのボタン
        JButton passButton = new JButton("パスをする");
        passButton.setBounds(100, 720, 100, 30);
        panel3.add(passButton);
        
        //手番の表示
        myTurnLabel.setBounds(300,0,100,30);
        computerTurnLabel.setBounds(300,0,100,30);
        panel3.add(myTurnLabel);
        panel3.add(computerTurnLabel);

        passButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!(me.meCanPlacing(me, computer))) {
                    me.Pass(me, computer);
                }
            }
        });

        labelb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Point point2 = e.getPoint();
                // テスト用
//                labelb.setText("全体: x:" + point2.x + ",y:" + point2.y);
                // どのマスが押されたのか
                controller.clickedFrame(placedPosition, point2.x, point2.y);
                if (logic.canClick) {
                    // コマを置く
                    if (me.isMyTurn) {
                        System.out.println("Meのturn");
                        if ((boolean) me.canPlacing(placedPosition, computer, me).get("result")) {
                            me.placing(placedPosition, me, computer, error, llLliB, llLliW);
                            myScoreLabel.setText("自分のスコア : " + String.valueOf(me.getScore()));
                            
                            myTurnLabel.setVisible(false);
                        	computerTurnLabel.setVisible(true);
                        } else {
                            System.out.println("エラー@View@Me");
                            error.setVisible(true);
                            TimerTask task = new TimerTask() {
                                public void run() {
                                    error.setVisible(false);
                                }
                            };
                            Timer timer = new Timer();
                            timer.schedule(task, 2000);
                        }
                        
                    }
                    if (computer.isMyTurn) {
                        logic.canClick = false;
                        System.out.println("Computerのturn");
                        TimerTask task = new TimerTask() {
                            public void run() {
                                computer.placing(computer.decidePosition(computer, me), computer, me, error, llLliB,
                                        llLliW);
                                computerScoreLabel.setText("相手のスコア : " + String.valueOf(computer.getScore()));
                                logic.canClick = true;
                                
                                computerTurnLabel.setVisible(false);
                            	myTurnLabel.setVisible(true);
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(task, 800);
                        
                        
                    }

                    logic.turns++;

                    // ゲームの進行状況
                    // テスト用に表示
                    if (logic.isFinish(logic, me, computer)) {
                        layout.show(getContentPane(), "panel4");

                        myResultScoreLabel.setText("自分の総スコア : " + String.valueOf(me.getScore()));
                        computerResultScoreLabel.setText("相手の総スコア : " + String.valueOf(computer.getScore()));
                        turnsLabel.setText(String.valueOf(logic.turns) + "ターンで終了");
                        if (me.getScore() > computer.getScore()) {
                            winlose.setText("勝利");
                            winlose.setForeground(Color.ORANGE);
                        } else if (me.getScore() < computer.getScore()) {
                            winlose.setText("敗北");
                            winlose.setForeground(Color.BLUE);
                        } else if (me.getScore() == computer.getScore()) {
                            winlose.setText("相討ち");
                        }
                    }
                }
            }
        });

        // テスト用
        JLabel num = new JLabel(
                "0              1              2               3              4              5              6             7");
        num.setBounds(100, 80, 500, 50);
//        panel3.add(num);
        panel3.add(labelb);
        panel3.add(emptyFrame);

        panel4 = new JPanel();
        panel4.setLayout(null);
        myResultScoreLabel.setBounds(100, 400, 150, 30);
        panel4.add(myResultScoreLabel);
        computerResultScoreLabel.setBounds(350, 400, 150, 30);
        panel4.add(computerResultScoreLabel);
        myResultPassesLabel.setBounds(100, 430, 150, 30);
        panel4.add(myResultPassesLabel);
        computerResultPassesLabel.setBounds(350, 430, 150, 30);
        panel4.add(computerResultPassesLabel);
        turnsLabel.setBounds(100, 450, 150, 30);
        panel4.add(turnsLabel);
        winlose.setBounds(100, 200, 500, 100);
        winlose.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));
        panel4.add(winlose);

        JButton gameRestart = new JButton("新しいゲームを開始");
        gameRestart.setBounds(450, 600, 150, 30);
        panel4.add(gameRestart);

        JButton finishButton = new JButton("終了する");
        finishButton.setBounds(80, 600, 80, 30);
        panel4.add(finishButton);

        finishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component c = (Component) e.getSource();
                Window w = SwingUtilities.getWindowAncestor(c);
                w.dispose();
            }
        });

        gameRestart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component c = (Component) e.getSource();
                Window w = SwingUtilities.getWindowAncestor(c);
                w.dispose();

                main(null);
                // layout.show(getContentPane(), "panel1");
            }
        });

        layout = new CardLayout();

        Container contentPane = getContentPane();
        contentPane.setLayout(layout);
        contentPane.add(panel1, "panel1");
        contentPane.add(panel2, "panel2");
        contentPane.add(panel3, "panel3");
        contentPane.add(panel4, "panel4");
    }

}