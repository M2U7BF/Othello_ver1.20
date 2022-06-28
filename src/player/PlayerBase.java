package player;

public class PlayerBase {
	private int Score = 2;
	private boolean isFirst;
	public boolean position[][] = new boolean[8][8];
	private int positionX;
	private int positionY;
	private boolean directions[] = new boolean[8];
	private boolean directions2[] = new boolean[8];
	Computer com;
	Me me;
	
	public PlayerBase() {
			
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++) {
				this.position[i][j]=false;
			}
		}
	}
	
	public void Pass() {}
	
	 public void turnOver() {
		
	}
	 
	public void placing() {
		
	}
	
	private boolean isMyTurn() {
		return false;
	}
	
	public boolean canTurnOver() {
		return false;
	}
	
	public boolean isDifferent() {
		return false;
	}
	
	public boolean canPlacing(int[] placedPosition,Computer com,Me me) {
		////ルール的におけるのか
		//全方位を調べる
		//上下左右、右上左上、右下左下
		positionX = placedPosition[0];
		positionY = placedPosition[1];
		
		boolean result = false;

		positionFix(8,com,me);
		
		for(int j=0; j < 7; j++) {
			for(int k=0; k<7; k++) {
					System.out.println(me.position[j][k]);
			}
		}
		
		int i =0;
		
		for(i = 0; i<7; i++) {
			System.out.println("for入ります");
			//相手のコマがおいてあれば、追跡
			//最大7回探索
			if(directions[i] && !(directions2[i])) {
				System.out.println(String.valueOf(i) + "の位置に相手コマがあります");
				positionFix(i,com,me);
				System.out.println("psitonxyはそれぞれ"+String.valueOf(positionX)+","+String.valueOf(positionY));
				//1
				break;
				
			} else {
				System.out.println("1段階目何もなかったので次の方位へ");
				continue;
			}
		}
		
		System.out.println("3-3:" + me.position[3][3]);
		System.out.println("me.position変化みようぜ:" + me.position[positionX-1][positionY+1]);
		
		//元きた場所に対する直線上を探す
		//2
		if(directions2[i] && !(directions[i])) {
			System.out.println("2:"+String.valueOf(i) + "の方向に探索し味方のものがありました");
			result = true;
		} else if (directions[i] && !(directions2[i])){
			System.out.println(directions[i]);
			System.out.println("2:"+String.valueOf(i) + "の位置に再び相手コマがあります");
			positionFix(i,com,me);
			System.out.println("2:psitonxyはそれぞれ"+String.valueOf(positionX)+","+String.valueOf(positionY));
			//
		}
		//3
		if(directions2[i] && !(directions[i])) {
			System.out.println("3:"+String.valueOf(i) + "の方向に探索し味方のものがありました");
			result = true;
		} else if (directions[i] && !(directions2[i])){
			System.out.println("3:"+directions[i]);
			System.out.println("3:"+String.valueOf(i) + "の位置に再び相手コマがあります");
			positionFix(i,com,me);
			System.out.println("3:"+"psitonxyはそれぞれ"+String.valueOf(positionX)+","+String.valueOf(positionY));
			//
		}
		//4
		if(directions2[i] && !(directions[i])) {
			System.out.println(String.valueOf(i) + "の方向に探索し味方のものがありました");
			result = true;
		} else if (directions[i] && !(directions2[i])){
			System.out.println(directions[i]);
			System.out.println(String.valueOf(i) + "の位置に再び相手コマがあります");
			positionFix(i,com,me);
			System.out.println("psitonxyはそれぞれ"+String.valueOf(positionX)+","+String.valueOf(positionY));
			//
		}
		//5
		if(directions2[i] && !(directions[i])) {
			System.out.println(String.valueOf(i) + "の方向に探索し味方のものがありました");
			result = true;
		} else if (directions[i] && !(directions2[i])){
			System.out.println(directions[i]);
			System.out.println(String.valueOf(i) + "の位置に再び相手コマがあります");
			positionFix(i,com,me);
			System.out.println("psitonxyはそれぞれ"+String.valueOf(positionX)+","+String.valueOf(positionY));
			//
		}
		//6
		if(directions2[i] && !(directions[i])) {
			System.out.println(String.valueOf(i) + "の方向に探索し味方のものがありました");
			result = true;
		} else if (directions[i] && !(directions2[i])){
			System.out.println(directions[i]);
			System.out.println(String.valueOf(i) + "の位置に再び相手コマがあります");
			positionFix(i,com,me);
			System.out.println("psitonxyはそれぞれ"+String.valueOf(positionX)+","+String.valueOf(positionY));
			//
		}
		//7
		if(directions2[i] && !(directions[i])) {
			System.out.println(String.valueOf(i) + "の方向に探索し味方のものがありました");
			result = true;
		} else if (directions[i] && !(directions2[i])){
			System.out.println(directions[i]);
			System.out.println(String.valueOf(i) + "の位置に再び相手コマがあります");
			positionFix(i,com,me);
			System.out.println("psitonxyはそれぞれ"+String.valueOf(positionX)+","+String.valueOf(positionY));
			//
		}
		
		
		

//		
//		if(directions2[i] && !(directions[i])) {
//			result = true;
//			break;
//		} else if (directions[i] && !(directions2[i])){
//			System.out.println(String.valueOf(i) + "の位置に再び相手コマがあります");
//			positionFix(i,com,me);
//			System.out.println(directions[i]);
//			System.out.println("psitonxyはそれぞれ"+String.valueOf(positionX)+","+String.valueOf(positionY));
//			//4
//			
//		} else {
//			continue;
//		}
//		
//		if(directions2[i] && !(directions[i])) {
//			result = true;
//			break;
//		} else if (directions[i] && !(directions2[i])){
//			System.out.println(String.valueOf(i) + "の位置に再び相手コマがあります");
//			positionFix(i,com,me);
//			System.out.println(directions[i]);
//			//5
//			
//		} else {
//			continue;
//		}
//		
//		if(directions2[i] && !(directions[i])) {
//			result = true;
//			break;
//		} else if (directions[i] && !(directions2[i])){
//			System.out.println(String.valueOf(i) + "の位置に再び相手コマがあります");
//			positionFix(i,com,me);
//			System.out.println(directions[i]);
//			//6
//			
//		} else {
//			continue;
//		}
//		
//		if(directions2[i] && !(directions[i])) {
//			result = true;
//			break;
//		} else if (directions[i] && !(directions2[i])){
//			System.out.println(String.valueOf(i) + "の位置に再び相手コマがあります");
//			positionFix(i,com,me);
//			//7
//			
//		} else {
//			continue;
//		}
//		
//		if(directions2[i] && !(directions[i])) {
//			result = true;
//			break;
//		} else {
//			//終了する
//			result = false;
//			break;
//		}
		
		System.out.println("探索結果は" + result + "でした。");
		System.out.println("psitonxyはそれぞれ"+String.valueOf(positionX)+","+String.valueOf(positionY));
		return result;
	}
	
	public void positionFix(int i, Computer com, Me me) {
		
		switch(i) {
		case 0:
			positionY--;
			break;
		case 1:
			positionX++;
			positionY--;
			break;
		case 2:
			positionX++;
			break;
		case 3:
			positionX++;
			positionY++;
			break;
		case 4:
			positionY++;
			break;
		case 5:
			positionX--;
			positionY++;
			break;
		case 6:
			positionX--	;
			break;
		case 7:
			positionX--;
			positionY--;
			break;
		case 8:
			break;
		}
		
		directions[0] = com.position[positionX][positionY-1];//上
		directions[1] = com.position[positionX+1][positionY-1];//右上
		directions[2] = com.position[positionX+1][positionY];//右
		directions[3] = com.position[positionX+1][positionY+1];//右下
		directions[4] = com.position[positionX][positionY+1];//下
		directions[5] = com.position[positionX-1][positionY+1];//左下
		directions[6] = com.position[positionX-1][positionY];//左
		directions[7] = com.position[positionX-1][positionY-1];//左上
		
		directions2[0] = me.position[positionX][positionY-1];//上
		directions2[1] = me.position[positionX+1][positionY-1];//右上
		directions2[2] = me.position[positionX+1][positionY];//右
		directions2[3] = me.position[positionX+1][positionY+1];//右下
		directions2[4] = me.position[positionX][positionY+1];//下
		directions2[5] = me.position[positionX-1][positionY+1];//左下
		directions2[6] = me.position[positionX-1][positionY];//左
		directions2[7] = me.position[positionX-1][positionY-1];//左上
	}

	
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	public boolean getFirst() {
		return this.isFirst;
	}
	public void setScore(int score) {
		this.Score = score;
	}
	public int getScore() {
		return this.Score;
	}
}
