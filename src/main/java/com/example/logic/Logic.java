package com.example.logic;

import java.util.Random;

import javax.swing.JLabel;

import com.example.player.Computer;
import com.example.player.Me;
import com.example.player.PlayerBase;

public class Logic {
    int maxNumPass;
    public int turns = 0;
    public boolean canClick = true;
    PlayerBase player;
    // boolean[][] isEmpty;

    public void decideFirst(Computer com, Me me, JLabel orderLabel) {
        // 先手を決定する
        Random rand = new Random();
        int order = rand.nextInt(2);
        boolean isMeFirst = order == 1;

        // 先手の設定から、各値を設定する
        me.setFirst(isMeFirst);
        com.setFirst(!isMeFirst);

        orderLabel.setText("あなたは : " + (isMeFirst ? "先手" : "後手"));

        // 初期配置を設定する
        me.position[3][4] = isMeFirst;
        me.position[4][3] = isMeFirst;
        me.position[3][3] = !isMeFirst;
        me.position[4][4] = !isMeFirst;
        com.position[3][3] = isMeFirst;
        com.position[4][4] = isMeFirst;
        com.position[4][3] = !isMeFirst;
        com.position[3][4] = !isMeFirst;

        me.isMyTurn = isMeFirst;
        com.isMyTurn = !isMeFirst;
    }

    public void start(Computer c, Me m) {
    }

    public boolean isFinish(Logic logic, Me me, Computer com) {
        if (!(logic.isEmpty(me, com)) || !(playersCanPlacing(me, com))) {
            return true;
        }
        else if(!(me.somewhereCanPlacing(me, com)) && !(me.somewhereCanPlacing(com, me))) {
        	return true;
        }

        return false;
    }

    public boolean playersCanPlacing(Me me, Computer com) {
        PlayerBase player = new PlayerBase();

        // 空きコマに対しcanPlacing実行
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 空きコマを探す
                if (!(me.position[i][j]) && !(com.position[i][j])) {
                    // もし置けるのであればtrue
                    int[] positon = { i, j };
                    if ((boolean) player.canPlacing(positon, me, com).get("result") ||
                            (boolean) player.canPlacing(positon, com, me).get("result")) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isEmpty(Me me, Computer com) {
        // com,me それぞれの所持positionを参照する
        // 1つでも空きマスがあればtrue

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(me.position[i][j]) && !(com.position[i][j])) {
                    return true;
                }
            }
        }

        //// アルゴリズムの適用が望ましい
        return false;
    }

}
