package ui.controller;

public class GameController {
	
	public void clickedFrame(int[] placedPosition,int x,int y){
		for (int row = 0, X1 = 0, Y1 = 63; row < 8; row++, X1 += 63, Y1 += 63) {
            for (int col = 0, X2 = 0, Y2 = 63; col < 8; col++, X2 += 63, Y2 += 63) {
                if (X2 <= x && Y2 >= x && X1 <= y && Y1 >= y) {
                    System.out.println(col + row + "が押されました");
                    placedPosition[0] = col;
                    placedPosition[1] = row;
                }
            }
        }
	}

}
