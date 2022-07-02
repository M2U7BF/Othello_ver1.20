package ui.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import player.PlayerBase;
import util.Img;

public class GamingView {
	Img imgs;
	
    public JLabel bStone;
    public JLabel wStone;
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
    public ImageIcon placingError;
    public ImageIcon passError;

    public GamingView(JPanel panel3,PlayerBase me, PlayerBase computer) {
        llLliB = new JLabel[8][8];
        llLliW = new JLabel[8][8];
        
        myTurnLabel = new JLabel("あなたのターン");
        computerTurnLabel = new JLabel("相手のターン");
        
        imgs = new Img();
        bStoneIcon = new ImageIcon(new ImageIcon(imgs.img[0]).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        wStoneIcon = new ImageIcon(new ImageIcon(imgs.img[7]).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
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

        myScoreLabel = new JLabel("自分のスコア : " + String.valueOf(me.getScore()));
        myScoreLabel.setBounds(100, 650, 150, 30);
        panel3.add(myScoreLabel);
        computerScoreLabel = new JLabel("相手のスコア : " + String.valueOf(computer.getScore()));
        computerScoreLabel.setBounds(400, 650, 150, 30);
        panel3.add(computerScoreLabel);
        myPassesLabel = new JLabel("自分のパス回数 : " + String.valueOf(me.getPasses()));
        myPassesLabel.setBounds(100, 680, 150, 30);
        panel3.add(myPassesLabel);
        computerPassesLabel = new JLabel("相手のパス回数 : " + String.valueOf(computer.getPasses()));
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
        canntPlacingError = new JLabel(placingError);
        canntPlacingError.setBounds(100, 10, 200, 80);
        panel3.add(canntPlacingError);
        canntPlacingError.setVisible(false);
        canntPassError = new JLabel(passError);
        canntPassError.setBounds(100, 10, 200, 80);
        panel3.add(canntPassError);
        canntPassError.setVisible(false);

        // パスのボタン
        passButton = new JButton("パスをする");
        passButton.setBounds(100, 720, 100, 30);
        panel3.add(passButton);

        // 手番の表示
        myTurnLabel.setBounds(400, 50, 100, 30);
        computerTurnLabel.setBounds(400, 50, 100, 30);
        panel3.add(myTurnLabel);
        panel3.add(computerTurnLabel);

        panel3.add(labelb);
        panel3.add(emptyFrame);
    }
}
