package ui.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import logic.Logic;
import player.Computer;
import player.Me;
import ui.controller.GameController;
import util.Img;
import util.Sound;

public class View extends JFrame {

//    JButton openButton;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel emptyFrame;
    CardLayout layout;
    PreparationView preparationView;
    GamingView gamingView;
    ResultView resultView;
    StartView startView;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    int placedPosition[] = new int[2];
    JLabel[][] lli = new JLabel[8][8];
    int order;

    public static String title = "othello";

    public static void main(String[] args) {
        View frame = new View("othello");
        frame.setVisible(true);
    }

    private Me me;
    private Computer computer;
    private Logic logic;
    private Sound sounds;
    private Img imgs;
    private GameController controller;

    View(String title) {

        me = new Me();
        computer = new Computer();
        logic = new Logic();
        sounds = new Sound();
        imgs = new Img();
        controller = new GameController();

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        
        startView = new StartView(panel1); 
        preparationView = new PreparationView(panel2);
        gamingView = new GamingView(panel3,me,computer);
        resultView = new ResultView(panel4);
        
        setTitle(title);
        setBounds(200, 100, 700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        startView.openButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
                
            	layout.show(getContentPane(), "panel2");
            }
        });
        
        preparationView.orderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
            	
                logic.decideFirst(computer, me, preparationView.orderLabel);
                
                preparationView.orderDecided();
            }
        });
        
        preparationView.startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
            	
            	gamingView.started(me,panel3);
                layout.show(getContentPane(), "panel3");
            }
        });

        gamingView.passButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!(me.meCanPlacing(me, computer))) {
                    me.Pass(me, computer);
                    gamingView.myPassesLabel.setText("自分のパス回数 : " + String.valueOf(me.getPasses()));
                } else {
                	gamingView.canntPassError.setVisible(true);
                    TimerTask task = new TimerTask() {
                        public void run() {
                        	gamingView.canntPassError.setVisible(false);
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(task, 1000);
                }
            }
        });

        gamingView.labelb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Point point2 = e.getPoint();
                // テスト用
//                labelb.setText("全体: x:" + point2.x + ",y:" + point2.y);
                // どのマスが押されたのか
                controller.clickedFrame(placedPosition, point2.x, point2.y);
                if (logic.canClick) {
                	//テスト用
//                	while(!(logic.isFinish(logic, me, computer)) && logic.turns < 80) {
                    // コマを置く
                    if (me.isMyTurn) {
                    	//テスト用
//                    	int[] myDecidePosition = computer.decidePosition(me, computer);
                        if (
                        		(boolean) me.canPlacing(placedPosition, computer, me).get("result")	
                        		//テスト
//                        		(boolean) me.canPlacing(myDecidePosition, computer, me).get("result")	
                        ) {
                            me.placing(placedPosition, me, computer, gamingView.canntPlacingError, gamingView.llLliB, gamingView.llLliW);
                        	//テスト用
//                        	me.placing(myDecidePosition, me, computer, canntPlacingError, llLliB, llLliW);
                            gamingView.myScoreLabel.setText("自分のスコア : " + String.valueOf(me.getScore()));
                            
                            gamingView.myTurnLabel.setVisible(false);
                            gamingView.computerTurnLabel.setVisible(true);
                        } else {
//                            System.out.println("エラー@View@Me");
                        	gamingView.canntPlacingError.setVisible(true);
                            TimerTask task = new TimerTask() {
                                public void run() {
                                	gamingView.canntPlacingError.setVisible(false);
                                }
                            };
                            Timer timer = new Timer();
                            timer.schedule(task, 1000);
                        }
                        
                    }
                    if (computer.isMyTurn) {
                        logic.canClick = false;
                        TimerTask task = new TimerTask() {
                            public void run() {
                            	int[] decidePosition = computer.decidePosition(computer, me);
                            	if(decidePosition[0] == 8 && decidePosition[1] == 8) {
                            		computer.Pass(computer, me);
                            		gamingView.computerPassesLabel.setText("相手のパス回数 : " + String.valueOf(computer.getPasses()));
                            	} else {
                            		computer.placing(decidePosition, computer, me, gamingView.canntPlacingError, gamingView.llLliB,
                            				gamingView.llLliW);
                            	}
                                
                            	gamingView.computerScoreLabel.setText("相手のスコア : " + String.valueOf(computer.getScore()));
                                logic.canClick = true;
                                
                                gamingView.computerTurnLabel.setVisible(false);
                                gamingView.myTurnLabel.setVisible(true);
                            }
                        };
                        Timer timer = new Timer();
                        //テスト用
//                        timer.schedule(task, 100);
                        timer.schedule(task, 800);
                    }

                    logic.turns++;
                    //テスト用
//                	}

                    // ゲームの進行状況
                    if (
                    		 //テスト用
//                    		true
                    		logic.isFinish(logic, me, computer)
                    		) {
                        layout.show(getContentPane(), "panel4");

                        resultView.myResultScoreLabel.setText("自分の総スコア : " + String.valueOf(me.getScore()));
                        resultView.computerResultScoreLabel.setText("相手の総スコア : " + String.valueOf(computer.getScore()));
                        resultView.turnsLabel.setText(String.valueOf(logic.turns) + "ターンで終了");
                        if (me.getScore() > computer.getScore()) {
                        	sounds = new Sound();
                            sounds.setFile(5);
                            sounds.play();
                        	
                            resultView.winlose.setText("勝利");
                            resultView.winlose.setForeground(Color.ORANGE);
                        } else if (me.getScore() < computer.getScore()) {
                        	sounds.setFile(2);
                        	sounds.play();
                        	
                        	resultView.winlose.setText("敗北");
                        	resultView.winlose.setForeground(Color.BLUE);
                        } else if (me.getScore() == computer.getScore()) {
                        	sounds.setFile(1);
                        	sounds.play();
                    		
                        	resultView.winlose.setText("相討ち");
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


        resultView.finishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
            	
                Component c = (Component) e.getSource();
                Window w = SwingUtilities.getWindowAncestor(c);
                w.dispose();
            }
        });

        resultView.gameRestart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
            	
                Component c = (Component) e.getSource();
                Window w = SwingUtilities.getWindowAncestor(c);
                w.dispose();

                main(null);
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