package ui.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Logic;
import player.Computer;
import player.Me;
import player.PlayerBase;
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
    
    private PlayerBase player;
    private Me me;
    private Computer computer;
    private Logic logic;
    private Sound sounds;
    private Img imgs;
    private GameController controller;

    View(String title) {
    	
    	player = new PlayerBase();
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
        preparationView = new PreparationView(panel2, me, computer);
        gamingView = new GamingView(panel3,me,computer);
        resultView = new ResultView(panel4);
        
        setTitle(title);
        setBounds(200, 100, 700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        //画面遷移1
        startView.openButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
                
            	layout.show(getContentPane(), "panel2");
            }
        });
        
        //画面遷移2
        preparationView.startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	sounds.setFile(0);
            	sounds.play();
            	
            	gamingView.started(me,panel3);
                layout.show(getContentPane(), "panel3");
            }
        });

        gamingView.labelb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Point point2 = e.getPoint();
//                labelb.setText("全体: x:" + point2.x + ",y:" + point2.y); // テスト用
                // どのマスが押されたのか
                controller.clickedFrame(placedPosition, point2.x, point2.y);
                if (logic.canClick) {
//                	while(!(logic.isFinish(logic, me, computer)) && logic.turns < 80) { //テスト用
                    // コマを置く
                    if (me.isMyTurn) {
//                    	int[] myDecidePosition = computer.decidePosition(me, computer); //テスト用
                        if (
                        		(boolean) player.canPlacing(placedPosition, me, computer).get("result")	
//                        		(boolean) player.canPlacing(myDecidePosition, computer, me).get("result") //テスト用	
                        ) {
                            me.placing(placedPosition, me, computer, gamingView.canntPlacingError, gamingView.llLliB, gamingView.llLliW);
//                        	me.placing(myDecidePosition, me, computer, canntPlacingError, llLliB, llLliW); //テスト用
                            
                            //meの置く処理
                            gamingView.myScoreLabel.setText(me.name + "のスコア : " + String.valueOf(me.getScore()));
                            gamingView.myTurnLabel.setVisible(false);
                            gamingView.computerTurnLabel.setVisible(true);
                        } else {
//                            System.out.println("エラー@View@Me");
                        	gamingView.canntPlacingError();
                        }
                        
                    }
                    if (computer.isMyTurn) {
                        logic.canClick = false;
                        TimerTask task = new TimerTask() {
                            public void run() {
                            	int[] decidePosition = computer.decidePosition(computer, me);
                            	if(decidePosition[0] == 8 && decidePosition[1] == 8) {
                            		computer.Pass(computer, me);
                            		gamingView.computerPassesLabel.setText(computer.name + "のパス回数 : " + String.valueOf(computer.getPasses()));
                            	} else if(
//                            			true //テスト用
                            			(boolean) player.canPlacing(decidePosition, computer, me).get("result")
                            			){
                            		computer.placing(decidePosition, computer, me, gamingView.canntPlacingError, gamingView.llLliB,
                            				gamingView.llLliW);
                            	} else {
                            		computer.Pass(computer, me);
                            		System.out.println("Cpmputer : エラー : 探索した座標にはルール上、置けません");
                            		gamingView.computerPassesLabel.setText(computer.name + "のパス回数 : " + String.valueOf(computer.getPasses()));
                            	}
                                
                                logic.canClick = true;
                                
                                //computerの置く処理
                                gamingView.computerScoreLabel.setText(computer.name + "のスコア : " + String.valueOf(computer.getScore()));
                                gamingView.computerTurnLabel.setVisible(false);
                                gamingView.myTurnLabel.setVisible(true);
                            }
                        };
                        Timer timer = new Timer();
//                        timer.schedule(task, 100); //テスト用
                        timer.schedule(task, 800);
                    }

                    logic.turns++;
                    //テスト用
//                	}

                    // ゲームの進行状況
                    if (
//                		true //テスト用
                    	logic.isFinish(logic, me, computer)
                    	) {
                        layout.show(getContentPane(), "panel4");
                        resultView.finished(logic, me, computer);
                    }
                }
            }
        });
        // テスト用
        JLabel num = new JLabel(
                "0              1              2               3              4              5              6             7");
        num.setBounds(100, 80, 500, 50);
//        panel3.add(num);

        layout = new CardLayout();

        Container contentPane = getContentPane();
        contentPane.setLayout(layout);
        contentPane.add(panel1, "panel1");
        contentPane.add(panel2, "panel2");
        contentPane.add(panel3, "panel3");
        contentPane.add(panel4, "panel4");
    }

}