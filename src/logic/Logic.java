package logic;

import java.util.Random;

import javax.swing.JLabel;

import player.Computer;
import player.Me;

public class Logic {
    int maxNumPass;
    // boolean[][] isEmpty;

    public void decideFirst(Computer com, Me me, JLabel orderLabel) {
        int order;
        Random rand = new Random();

        // order = rand.nextInt(2);
        // テスト用にorder = 1 にしている
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

        // 初期配置を設定する
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

    public boolean isFinish(Logic logic, Me me, Computer com) {
    	boolean finish = false;
        if (!(logic.isEmpty(me, com)) || !(playersCanPlacing(me, com))) {
        	finish = true;
        }
        return finish;
    }

    public boolean playersCanPlacing(Me me, Computer com) {
        // 空きコマに対しcanPlacing実行
        boolean available = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 空きコマを探す
                if (!(me.position[i][j]) && !(com.position[i][j])) {
                    // もし置けるのであればtrue
                    int[] positon = { i, j };
                    if ((boolean) me.canPlacing(positon, me, com).get("result") ||
                            (boolean) me.canPlacing(positon, com, me).get("result")) {
                        available = true;
                        break;
                    } else {
                        continue;
                    }
                }
            }
            if (available) {
                break;
            }
        }

        return available;
    }

    public boolean isEmpty(Me me, Computer com) {
        boolean empty = false;
        // com,me それぞれの所持positionを参照する
        // 1つでも空きマスがあればtrue

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(me.position[i][j]) && !(com.position[i][j])) {
                    empty = true;
                    break;
                } else {
                    continue;
                }
            }
            if (empty) {
                break;
            }
        }

        //// アルゴリズムの適用が望ましい
        return empty;
    }

}
