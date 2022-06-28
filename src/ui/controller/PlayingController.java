package ui.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import player.Me;
import ui.view.View;

public class PlayingController extends MouseAdapter{
	Me me = new Me();
	View view = new View("go");
	
	public int PlacingPoint(int x, int y) {
		//// クリックされた場所を受け取り、何列目の何か判定しある石を置く座標を返す
		
		//おけるのか判定
		
		
		return 1;
	}
	
	
	public void mouseClicked(MouseEvent e){
	    Point point2 = e.getPoint();
	    view.labelb.setText("全体: x:" + point2.x + ",y:" + point2.y);
	    
	    if(me.canPlacing()) {
	    	//その場所には置けません
	    }
	  }
	
}
