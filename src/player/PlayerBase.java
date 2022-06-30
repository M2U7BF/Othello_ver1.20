package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

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
	Computer com;
	Me me;

	public PlayerBase() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.position[i][j] = false;
			}
		}
	}

	public void Pass(PlayerBase me, PlayerBase enemy) {
		int numPass = me.getPasses() + 1;
		me.setPasses(numPass);
		
		me.isMyTurn = false;
        enemy.isMyTurn = true;
	}

	public void turnOver(PlayerBase me, PlayerBase enemy, ArrayList<ArrayList<int[]>> turnOverList, JLabel[][] llLliB, JLabel[][] llLliW) {
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
				System.out.println("覆そうか考えたコマ座標:"+String.valueOf(x)+","+String.valueOf(y));
				if(enemy.position[x][y] && !(me.position[x][y])) {
					System.out.println("覆したコマ座標:"+String.valueOf(x)+","+String.valueOf(y));
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
	
	//ルール的におけるのかを調べる。
	public Map<String, Object> canPlacing(int[] placedPosition, PlayerBase enemy, PlayerBase me) {
		positionX = placedPosition[0];
		positionY = placedPosition[1];
		
		if(positionY > 0) {
			directions[0] = enemy.position[positionX][positionY - 1];// 上
			directions2[0] = me.position[positionX][positionY - 1];// 上
		}
		if(positionX < 7 && positionY > 0) {
			directions[1] = enemy.position[positionX + 1][positionY - 1];// 右上
			directions2[1] = me.position[positionX + 1][positionY - 1];// 右上
		}
		if(positionX < 7) {
			directions[2] = enemy.position[positionX + 1][positionY];// 右
			directions2[2] = me.position[positionX + 1][positionY];// 右
		}
		if (positionX < 7 && positionY < 7) {
			directions[3] = enemy.position[positionX + 1][positionY + 1];// 右下
			directions2[3] = me.position[positionX + 1][positionY + 1];// 右下
		}
		if(positionY < 7) {
			directions[4] = enemy.position[positionX][positionY + 1];// 下
			directions2[4] = me.position[positionX][positionY + 1];// 下
		}
		if(positionX > 0 && positionY < 7) {
			directions[5] = enemy.position[positionX - 1][positionY + 1];// 左下
			directions2[5] = me.position[positionX - 1][positionY + 1];// 左下
		}
		if(positionX > 0) {
			directions[6] = enemy.position[positionX - 1][positionY];// 左
			directions2[6] = me.position[positionX - 1][positionY];// 左
		}
		if(positionX > 0 && positionY > 0) {
			directions[7] = enemy.position[positionX - 1][positionY - 1];// 左上
			directions2[7] = me.position[positionX - 1][positionY - 1];// 左上
		}
		
		boolean result = false;
		
		ArrayList<ArrayList<int[]>> arrayA = new ArrayList<>();
		ArrayList<ArrayList<int[]>> resultList = new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			////当コマの周囲に相手のコマがあるか。なければcontinue
			if (directions[i] && !(directions2[i])) {
				//相手のコマがる場合、その位置に間する探索リストを制作
				ArrayList<int[]> searchList = new ArrayList<>();
				searchList = SearchList2(i,positionX,positionY);
				arrayA.add(searchList);
			} else {
				continue;
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
					continue;
				} else {
					break;
				}
			}
		}
		
//		System.out.println("探索結果は" + result + "でした。@playerbase");
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("result",result);
		values.put("turnPosition",resultList);

		return values;
	}
	
	
	public void placing(int[] placedPosition,PlayerBase me,PlayerBase enemy,JLabel error,JLabel[][] llLliB,JLabel[][] llLliW) {
		Map<String,Object> values = me.canPlacing(placedPosition, enemy, me);
		int x = placedPosition[0];
		int y = placedPosition[1];
		if (!(me.position[x][y]) && !(enemy.position[x][y])) {
            // 置く石の種類を指定し、配置
            if (me.getFirst()) {
                JLabel lliitem = llLliB[x][y];
                lliitem.setVisible(true);
            } else if (!(me.getFirst())){
                JLabel lliitem = llLliW[x][y];
                lliitem.setVisible(true);
            }

            // 置いたことをログする
            me.position[x][y] = true;
            
            //覆せるコマを全て覆す
            ArrayList<ArrayList<int[]>> turnOverList = (ArrayList<ArrayList<int[]>>) values.get("turnPosition");
            turnOver(me, enemy, turnOverList, llLliB, llLliW);
            
            //// コマを置いたときの処理
            int meScore = me.getScore() + 1;
            me.setScore(meScore);
            
            //ターン交代
            me.isMyTurn = false;
            enemy.isMyTurn = true;
            
        } else {
        	System.out.println("error@PlayerBase");
            error.setVisible(true);

            TimerTask task = new TimerTask() {
                public void run() {
                    // タイマーで実行したい処理
                    error.setVisible(false);
                }
            };

            Timer timer = new Timer();
            timer.schedule(task, 2000);
        }
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
