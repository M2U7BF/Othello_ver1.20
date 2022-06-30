package logic;

import java.util.Random;

import javax.swing.JLabel;

import player.Computer;
import player.Me;

public class Logic {
	int maxNumPass;
//	boolean[][] isEmpty;
	
	public void decideFirst(Computer com, Me me, JLabel orderLabel) {
		int order;
		Random rand = new Random();
		
//		 order = rand.nextInt(2);
		//テスト用にorder = 1	にしている
		 order = 1;
         if (order == 1) {
             me.setFirst(true);
             com.setFirst(false);
             orderLabel.setText("あなたは : 先手");
         } else if (order == 0) {
             me.setFirst(false);
             com.setFirst(true);
             orderLabel.setText("あなたは : 後手");
         }
         
         //初期配置を設定する
         if (me.getFirst()) {
             com.position[3][3] = true;
             com.position[4][4] = true;
             me.position[3][4] = true;
             me.position[4][3] = true;
         } else {
             com.position[4][3] = true;
             com.position[3][4] = true;
             me.position[3][3] = true;
             me.position[4][4] = true;
         }
         
         me.isMyTurn = me.getFirst();
         com.isMyTurn = com.getFirst();
	}
	
	public void start(Computer c, Me m) {
	}
	
	public void isFinish(Logic logic) {
		logic.isEmpty();
	}
	
	public boolean isEmpty() {
		return true;
	}
	
}
