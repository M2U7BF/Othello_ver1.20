package ui.controller;

import java.util.logging.Logger;

public class GameController {
    private final Logger logger = Logger.getLogger(this.getClass().toString());

    /***
     * クリックされたマスの座標を取得する。
     * @param x
     * @param y
     * @return
     */
    public int[] clickedFrame(int x,int y){
		for (int row = 0, X1 = 0, Y1 = 63; row < 8; row++, X1 += 63, Y1 += 63) {
            for (int col = 0, X2 = 0, Y2 = 63; col < 8; col++, X2 += 63, Y2 += 63) {
                if (X2 <= x && Y2 >= x && X1 <= y && Y1 >= y) {
                    logger.info(col + row + "が押されました");
                    return new int[] {col, row};
                }
            }
        }
        return null;
	}

}
