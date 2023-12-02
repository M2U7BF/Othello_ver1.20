package com.example.ui.controller;

import org.apache.logging.log4j.Logger;

public class GameController {

    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(GameController.class);

    /***
     * クリックされたマスの座標を取得する。
     * @param x
     * @param y
     * @return
     */
    public int[] clickedFrame(int x, int y) {
        int position[] = new int[2];

        int col = x == 0 ? 0 : x / 63;
        int row = y == 0 ? 0 : y / 63;
        log.info("[" + col + "," + row + "]が押されました");

        position[0] = col;
        position[1] = row;

        return position;
    }

}
