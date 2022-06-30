package ui.controller;

import java.awt.event.MouseAdapter;

public class PlayingController extends MouseAdapter{
	
//	public int PlacingPoint(int x, int y) {
//	}
	
	public void clickedFrame(int[] placedPosition,int x,int y){
		for (int row = 0, X1 = 0, Y1 = 65; row < 8; row++, X1 += 65, Y1 += 65) {
            for (int col = 0, X2 = 0, Y2 = 65; col < 8; col++, X2 += 65, Y2 += 65) {
                if (X2 <= x && Y2 >= x && X1 <= y && Y1 >= y) {
                    System.out.println(String.valueOf(col) + String.valueOf(row) + "が押されました");
                    placedPosition[0] = col;
                    placedPosition[1] = row;
                }
            }
        }
	}
}
