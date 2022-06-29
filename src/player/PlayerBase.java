package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerBase {
	private int Score = 2;
	private boolean isFirst;
	public boolean position[][] = new boolean[8][8];
	protected int positionX;
	protected int positionY;
	protected boolean directions[] = new boolean[8];
	protected boolean directions2[] = new boolean[8];
	public boolean isMyTurn = false;
	Computer com;
	Me me;

	public PlayerBase() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.position[i][j] = false;
			}
		}
	}

	public void Pass() {
	}

	public void turnOver() {

	}


//	public boolean canTurnOver() {
//		return false;
//	}

	public boolean isDifferent() {
		return false;
	}

	//ルール的におけるのかを調べる。
	public Map<String, Object> canPlacing(int[] placedPosition, Computer com, Me me) {
		positionX = placedPosition[0];
		positionY = placedPosition[1];
		
		directions[0] = com.position[positionX][positionY - 1];// 上
		directions[1] = com.position[positionX + 1][positionY - 1];// 右上
		directions[2] = com.position[positionX + 1][positionY];// 右
		directions[3] = com.position[positionX + 1][positionY + 1];// 右下
		directions[4] = com.position[positionX][positionY + 1];// 下
		directions[5] = com.position[positionX - 1][positionY + 1];// 左下
		directions[6] = com.position[positionX - 1][positionY];// 左
		directions[7] = com.position[positionX - 1][positionY - 1];// 左上

		directions2[0] = me.position[positionX][positionY - 1];// 上
		directions2[1] = me.position[positionX + 1][positionY - 1];// 右上
		directions2[2] = me.position[positionX + 1][positionY];// 右
		directions2[3] = me.position[positionX + 1][positionY + 1];// 右下
		directions2[4] = me.position[positionX][positionY + 1];// 下
		directions2[5] = me.position[positionX - 1][positionY + 1];// 左下
		directions2[6] = me.position[positionX - 1][positionY];// 左
		directions2[7] = me.position[positionX - 1][positionY - 1];// 左上

		boolean result = false;

		int i = 0;
		ArrayList<int[]> turnOverlists = new ArrayList<>();
		int[] list1 = new int[2];
		int[] list2 = new int[2];

		for (i = 0; i < 7; i++) {
			////当コマの周囲に相手のコマがあるか。なければcontinue
			if (directions[i] && !(directions2[i])) {
				//相手のコマがる場合、その位置に移動
				positionFix(i, com, me);
				list1[0] = positionX;
				list1[1] = positionY;
				turnOverlists.add(list1);
				//2
				//更にその先に仲間のコマがあるか
				if (directions2[i] && !(directions[i])) {
					positionFix(i, com, me);
					list2[0] = positionX;
					list2[1] = positionY;
					turnOverlists.add(list2);
					
					result = true;
					break;
				
				} else if (directions[i] && !(directions2[i])) {
					//再び相手のコマだった場合探索リスト作成
					ArrayList<int[]> searchList = SearchList(list1,list2);
					
					for(int j=0; j < searchList.size(); j++) {
						if(com.position[searchList.get(j)[0]][searchList.get(j)[1]]) {
							result = true;
							break;
						} else if(me.position[searchList.get(j)[0]][searchList.get(j)[1]]) {
							turnOverlists.add(searchList.get(j));
						} else {
							turnOverlists.clear();
							result = false;
						}
					}
					break;
					
				} else {
					turnOverlists.clear();
					result = false;
				}
			} else {
				turnOverlists.clear();
				continue;
			}

		}
		
		System.out.println("探索結果は" + result + "でした。@playerbase");
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("result",result);
		values.put("turnPosition",turnOverlists);

		return values;
	}
	
	public ArrayList<int[]> SearchList(int[] list1, int[] list2){
		ArrayList<int[]> searchList = new ArrayList<>();
		searchList.add(list2);
		
		int xc = 0;
		int yc = 0;
		
		//xの変化量
		xc = list2[0]-list1[0];
		//yの変化量
		yc = list2[1]-list1[1];
		
		for(int i=1; i<8;i++) {
			int[] list3 = new int[2];
			list3[0]=list2[0] + xc*i;
			list3[1]=list2[1] + yc*i;
			if(list3[0] < 8 && list3[1] < 8 && list3[0] >= 0 && list3[1] >= 0) {
				searchList.add(list3);
			}
		}
		
		return searchList;
	}
	
	public ArrayList<int[]> SearchList2(int i,int x, int y){
		ArrayList<int[]> searchList = new ArrayList<>();
		
		int xc = 0;
		int yc = 0;
		
		switch(i) {
		case 0:
			yc = -1;
			break;
		case 1:
			xc = 1;
			yc = -1;
			break;
		case 2:
			xc = 1;
			break;
		case 3:
			xc = 1;
			yc = 1;
			break;
		case 4:
			xc = -1;
			break;
		case 5:
			xc = -1;
			yc = 1;
			break;
		case 6:
			xc = -1;
			break;
		case 7:
			xc = -1;
			yc = -1;
			break;
		}
		
		for(int j=0; j < 8; j++) {
			int[] list3 = new int[2];
			list3[0]=x + xc*i;
			list3[1]=y + yc*i;
			if(list3[0] < 8 && list3[1] < 8 && list3[0] >= 0 && list3[1] >= 0) {
				searchList.add(list3);
			}
		}
		
		return searchList;
	}
	
//	public Map<String, Object> ifs(Computer com,Me me,int i, ArrayList<int[]> turnOverlists,boolean result) {
//		int[] list10 = new int[2]; 
//		System.out.println("探索回数:"+String.valueOf(i));
//		if (directions2[i] && !(directions[i])) {
//			System.out.println(String.valueOf(positionX));
//			positionFix(i, com, me);
//			list10[0] = positionX;
//			list10[1] = positionY;
//			turnOverlists.add(list10);
//			
//			result = true;
//		} else if (directions[i] && !(directions2[i])) {
//			positionFix(i, com, me);
//			list10[0] = positionX;
//			list10[1] = positionY;
//			turnOverlists.add(list10);
//			
//			// 3
//			ifs(com,me,i,turnOverlists,result);
//			
//		} else {
//			turnOverlists.clear();
//			result = false;
//		}
//		Map<String, Object> values = new HashMap<String, Object>();
//		values.put("result",result);
//		values.put("turnPosition",turnOverlists);
//		
//		return values;
//	}
	

	public void positionFix(int i, Computer com, Me me) {

		switch (i) {
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
				positionX--;
				break;
			case 7:
				positionX--;
				positionY--;
				break;
			case 8:
				break;
		}

		if (positionX >= 0 && positionX <= 8 && positionY >= 0 && positionY <= 8) {
			directions[0] = com.position[positionX][positionY - 1];// 上
			directions[1] = com.position[positionX + 1][positionY - 1];// 右上
			directions[2] = com.position[positionX + 1][positionY];// 右
			directions[3] = com.position[positionX + 1][positionY + 1];// 右下
			directions[4] = com.position[positionX][positionY + 1];// 下
			directions[5] = com.position[positionX - 1][positionY + 1];// 左下
			directions[6] = com.position[positionX - 1][positionY];// 左
			directions[7] = com.position[positionX - 1][positionY - 1];// 左上

			directions2[0] = me.position[positionX][positionY - 1];// 上
			directions2[1] = me.position[positionX + 1][positionY - 1];// 右上
			directions2[2] = me.position[positionX + 1][positionY];// 右
			directions2[3] = me.position[positionX + 1][positionY + 1];// 右下
			directions2[4] = me.position[positionX][positionY + 1];// 下
			directions2[5] = me.position[positionX - 1][positionY + 1];// 左下
			directions2[6] = me.position[positionX - 1][positionY];// 左
			directions2[7] = me.position[positionX - 1][positionY - 1];// 左上
		}
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
