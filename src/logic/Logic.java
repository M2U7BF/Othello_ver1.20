package logic;

import java.util.Random;

import player.Computer;
import player.Me;

public class Logic {
	int maxNumPass;
	boolean[][] isEmpty;
	
	public int decideFirst() {
		Random rand = new Random();
	    int num = rand.nextInt(1);
	    System.out.println(num);
	    
		return num;
	}
	
	public void start(Computer c, Me m) {
		int num = decideFirst();
		
		switch(num) {
			case 0:
				m.setFirst(true);
				break;
			case 1:
				c.setFirst(true);
				break;
		}
	}
	
	public void finish() {
		
	}
	
}
