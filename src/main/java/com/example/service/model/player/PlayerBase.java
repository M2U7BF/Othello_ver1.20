package com.example.service.model.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import com.example.util.Sound;

public class PlayerBase {
	private int Score = 2;
	private boolean isFirst;
	private int passes = 0;
	public boolean position[][] = new boolean[8][8];
	protected int positionX;
	protected int positionY;
	protected boolean directions[] = new boolean[8];
	protected boolean directions2[] = new boolean[8];
	public boolean isMyTurn = false;
	public String name = "";
	public int id;
	Computer com;
	Me me;
	Sound sounds;

	public PlayerBase() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.position[i][j] = false;
			}
		}
	}

	public void Pass(PlayerBase me, PlayerBase enemy) {
		sounds = new Sound();
		sounds.setFile(3);
		sounds.play();
		
		int numPass = me.getPasses() + 1;
		me.setPasses(numPass);
		
		me.isMyTurn = false;
        enemy.isMyTurn = true;
	}
	
	public void turnOver(PlayerBase me, PlayerBase enemy, ArrayList<ArrayList<int[]>> turnOverList) {		
		for(int i=0; i<turnOverList.size(); i++) {
			for(int j=0; j<turnOverList.get(i).size(); j++) {
				int x = turnOverList.get(i).get(j)[0];
				int y = turnOverList.get(i).get(j)[1];
				if(enemy.position[x][y] && !(me.position[x][y])) {
			
			        // 置いたことをログする
			        me.position[x][y] = true;
			        enemy.position[x][y] = false;
			        
			        //// コマを置いたときの処理
			        int myScore = me.getScore() +1;
			        me.setScore(myScore);
			        int enemyScore = enemy.getScore() -1;
			        enemy.setScore(enemyScore);
				}else if(me.position[x][y] && !(enemy.position[x][y])) {
					break;
				}
			}
		}
	}

	public void turnOver2(PlayerBase me, PlayerBase enemy, ArrayList<ArrayList<int[]>> turnOverList, JLabel[][] llLliB, JLabel[][] llLliW) {
		JLabel[][] lliitem = new JLabel[8][8];
		JLabel[][] enemyLliitem = new JLabel[8][8];
		
		// 置く石の種類を指定
		if (me.getFirst()) {
            lliitem = llLliB;
            enemyLliitem = llLliW;
        } else if (!(me.getFirst())){
            lliitem = llLliW;
            enemyLliitem = llLliB;
        }
		
		for(int i=0; i<turnOverList.size(); i++) {
			for(int j=0; j<turnOverList.get(i).size(); j++) {
				int x = turnOverList.get(i).get(j)[0];
				int y = turnOverList.get(i).get(j)[1];
				if(enemy.position[x][y] && !(me.position[x][y])) {
					//表示
			        lliitem[x][y].setVisible(true);
			        enemyLliitem[x][y].setVisible(false);
			
			        // 置いたことをログする
			        me.position[x][y] = true;
			        enemy.position[x][y] = false;
			        
			        //// コマを置いたときの処理
			        int myScore = me.getScore() +1;
			        me.setScore(myScore);
			        int enemyScore = enemy.getScore() -1;
			        enemy.setScore(enemyScore);
				}else if(me.position[x][y] && !(enemy.position[x][y])) {
					break;
				}
			}
		}
	}

	public boolean isDifferent() {
		return false;
	}
	
	public Map<String, Object> canPlacing(int[] placedPosition, PlayerBase me,PlayerBase enemy) {
		positionX = placedPosition[0];
		positionY = placedPosition[1];
		
		int[][] change = {{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1}};
		int[][] directionsB = new int[8][2];
		boolean result = false;
		
		//方向リストを作成
		for(int i=0; i<8; i++) {
			int[] direction = new int[2];
			
			direction[0] = placedPosition[0] + change[i][0];
			direction[1] = placedPosition[1] + change[i][1];
			
			if(direction[0] >= 8 || direction[0] < 0 || direction[1] >= 8 || direction[1] < 0 ) {
				//条件に合わない場合
				directionsB[i] = null;
			}else {
				directionsB[i] = direction;
			}
		}
		
		ArrayList<ArrayList<int[]>> arrayA = new ArrayList<>();
		ArrayList<ArrayList<int[]>> resultList = new ArrayList<>();
		
		//8方位探索
		for (int i = 0; i < 8; i++) {
			if(directionsB[i] == null) {
				//Do nothing
			} else {
				boolean mePlaced = me.position[directionsB[i][0]][directionsB[i][1]];
				boolean enemyPlaced = enemy.position[directionsB[i][0]][directionsB[i][1]];
				
				////当マスの周囲に相手のコマがあるか。なければcontinue
				if (enemyPlaced && !(mePlaced)) {
					//相手のコマがる場合、その方向に間する探索リストを作成
					ArrayList<int[]> searchList = SearchList2(i,positionX,positionY);
					arrayA.add(searchList);
				}
			}
		}
		
		//探索リストのなかで条件に見合うものを絞り込む
		for(int i=0; i < arrayA.size(); i++) {
			for(int j=0 ; j < arrayA.get(i).size() ; j++) {
				////次の項目を連続けて確認していったとき、相手or空き。相手>次の項目をみる 空き>return
				int x = arrayA.get(i).get(j)[0];
				int y = arrayA.get(i).get(j)[1];
				if(me.position[x][y] && !(enemy.position[x][y])) {
					//味方のコマが見つかった場合、その探索リストを結果に保存
					result = true;
					resultList.add(arrayA.get(i));
					break;
				}else if(enemy.position[x][y]) {
					//敵のコマが置いてあった場合
					//Do nothing
				} else {
					break;
				}
			}
		}
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("result",result);
		values.put("turnPosition",resultList);

		return values;
	}

	public void placing(int[] placedPosition,PlayerBase me,PlayerBase enemy,ArrayList<ArrayList<int[]>> turnOverList) {
		int x = placedPosition[0];
		int y = placedPosition[1];
		
		//音を再生
		//音を再生する
		sounds = new Sound();
		sounds.setFile(4);
		sounds.play();

        // 置いたことをログする
        me.position[x][y] = true;
        
        //覆せるコマを全て覆す
        turnOver(me, enemy, turnOverList);
        
        //// コマを置いたときの処理
        int meScore = me.getScore() + 1;
        me.setScore(meScore);
        
        //ターン交代
        me.isMyTurn = false;
        enemy.isMyTurn = true;
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
				yc = 1;
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
			
			for(int j=1; j < 8; j++) {
				int[] list3 = new int[2];
				list3[0]=x + xc*j;
				list3[1]=y + yc*j;
				if(list3[0] < 8 && list3[1] < 8 && list3[0] >= 0 && list3[1] >= 0) {
					searchList.add(list3);
				}
			}
			return searchList;
	}
	
    public boolean somewhereCanPlacing(PlayerBase me, PlayerBase enemy) {
        PlayerBase player = new PlayerBase();
		// 空きコマに対しcanPlacing実行

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 空きコマを探す
                if (!(me.position[i][j]) && !(enemy.position[i][j])) {
                    // もし置けるのであればtrue
                    int[] position = { i, j };
                    if ((boolean) player.canPlacing(position, me, enemy).get("result")) {
                        return true;
                    }
                }
            }
        }

        return false;
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

	public int getPasses() {
		return passes;
	}
	
	public void setPasses(int passes) {
		this.passes = passes;
	}
}
