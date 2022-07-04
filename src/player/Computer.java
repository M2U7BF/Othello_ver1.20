package player;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import ui.view.GamingView;

public class Computer extends PlayerBase {
    int positionX = 0;
    int positionY = 0;
    public int strength;
    
    public Computer() {
    	this.name = "相手";
    	this.id = 1;
	}
    //行動
    public void turnAction(Computer computer,Me me,GamingView gamingView) {
    	int[] decidePosition = decidePosition(computer, me);
    	if(decidePosition[0] == 8 && decidePosition[1] == 8) {
    		computer.Pass(computer, me);
    		gamingView.computerPassesLabel.setText(computer.name + "のパス回数 : " + String.valueOf(computer.getPasses()));
    	} else if(
//            			true //テスト用
    			(boolean) canPlacing(decidePosition, computer, me).get("result")
    			){
    		Map<String,Object> values = canPlacing(decidePosition, computer, me);
        	ArrayList<ArrayList<int[]>> turnOverList = (ArrayList<ArrayList<int[]>>) values.get("turnPosition");
        	
            gamingView.placeAnimation(decidePosition,computer);
            gamingView.turnOverAnimation(computer, me, turnOverList, gamingView);
            
        	computer.placing(decidePosition, computer, me,
    				turnOverList);
    	} else {
    		computer.Pass(computer, me);
    		System.out.println("Cpmputer : エラー : 探索した座標にはルール上、置けません("+String.valueOf(decidePosition[0])+","+String.valueOf(decidePosition[1])+")");
    		gamingView.computerPassesLabel.setText(computer.name + "のパス回数 : " + String.valueOf(computer.getPasses()));
    	}
    }

    // 決める
    public int[] decidePosition(PlayerBase me, PlayerBase enemy) {
        int[] decidePosition = new int[2];
        ArrayList<int[]> arrays = new ArrayList<>();
        ArrayList<ArrayList<int[]>> arrays2 = new ArrayList<>();

        // 置かれているマスの座標をarraylistに格納
        for (int i = 0; i < me.position.length; i++) {
            for (int j = 0; j < me.position.length; j++) {
                if (me.position[i][j]) {
                    int[] list = new int[2];
                    list[0] = i;
                    list[1] = j;
                    arrays.add(list);
                }
            }
        }

        // それぞれのコマにseekPosition()(置ける場所を探す)実行。コマに紐づく配置用座標らを取得。arraylistに代入取得
        for (int i = 0; i < arrays.size(); i++) {
            ArrayList<int[]> arrays3 = new ArrayList<>();

            arrays3 = seekPosition(arrays.get(i), me, enemy);

            if (arrays3.size() > 0) {
                arrays2.add(arrays3);
            }
        }

        if (arrays2.size() > 0) {
        	if(strength == 0) {
        		decidePosition = randomChoice(arrays2);
        	}
        	if(strength == 1) {
        		decidePosition = ChoicePosition(arrays2);
        	}
        } else if (arrays2.size() == 0) {
            // 置けるマスが無い場合
            decidePosition[0] = 8;
            decidePosition[1] = 8;
        }

        return decidePosition;
    }
    
    public int[] ChoicePosition(ArrayList<ArrayList<int[]>> arrays2) {
    	boolean doseExit = false;
    	int[] decidePosition = new int[2];
    	
    	Random rnd = new Random();
    	
    	ArrayList<int[]> arrays5 = new ArrayList<>();
    	
    	// 3重リストのarray2の1つ目を展開
    	//置ける場所をすべてarraylistにまとめる
        ArrayList<int[]> arrays4 = new ArrayList<>();
        for(int i=0; i < arrays2.size(); i++ ) {
        	for(int j=0; j < arrays2.get(i).size(); j++) {
        		arrays4.add(arrays2.get(i).get(j));
        	}
        }
        
        //置ける場所から優先順位をつける
        
        //角をとる
        for(int i=0; i < arrays4.size(); i++) {
        	int x = arrays4.get(i)[0];
        	int y = arrays4.get(i)[1];
        	if((x==0 && y==0)||(x==0 && y==7)||(x==7 && y==0)||(x==7 && y==7)) {
        		arrays5.add(arrays4.get(i));
        		doseExit = true;
        		continue;
        	}
        }
        
        //最も外側のマスをとる
//        if(!(doseExit)) {
//	        for(int i=0; i < arrays4.size(); i++) {
//	        	int x = arrays4.get(i)[0];
//	        	int y = arrays4.get(i)[1];
//	        	if((x==3 || x==5)&&(y==0 || y==7)||(y==2 && y==4)&&(x==0 || x==7)) {
//	        		arrays5.add(arrays4.get(i));
//	        		doseExit = true;
//	        		continue;
//	        	}
//	        }
//        }
        
        //(2,0)付近をとる
        if(!(doseExit)) {
	        for(int i=0; i < arrays4.size(); i++) {
	        	int x = arrays4.get(i)[0];
	        	int y = arrays4.get(i)[1];
	        	if((x==2||x==5)&&(y==0||y==7)||(y==2||y==5)&&(x==0||x==7)) {
	        		arrays5.add(arrays4.get(i));
	        		doseExit = true;
	        		continue;
	        	}
	        }
        }
        
        //(2,1)付近をとる
        if(!(doseExit)) {
	        for(int i=0; i < arrays4.size(); i++) {
	        	int x = arrays4.get(i)[0];
	        	int y = arrays4.get(i)[1];
	        	if((x==2||x==5)&&(y==1||y==6)||(y==2||y==5)&&(x==1||x==6)) {
	        		arrays5.add(arrays4.get(i));
	        		doseExit = true;
	        		continue;
	        	}
	        }
        }
    	
        if(!(doseExit)) {
	        for(int i=0; i < arrays4.size(); i++) {
	        	int x = arrays4.get(i)[0];
	        	int y = arrays4.get(i)[1];
	        	if((x>=2 && x<=5)&&(y>=2 && y<=5)) {
	        		arrays5.add(arrays4.get(i));
	        		doseExit = true;
	        		continue;
	        	}
	        }
        }
        
        if(doseExit){
        	decidePosition = arrays5.get(rnd.nextInt(arrays5.size()));
        }else{
        	decidePosition = randomChoice(arrays2);
        }
        
    	return decidePosition;
	}
    
    public int[] randomChoice(ArrayList<ArrayList<int[]>> arrays2) {
    	int[] decidePosition = new int[2];
    	// 取得したarraylistからランダムもしくは最適化する形で1つの値に決定
        Random rnd = new Random();

        // 3重リストのarray2の1つ目を展開
        ArrayList<int[]> arrays4 = new ArrayList<>();
        int a = rnd.nextInt(arrays2.size());
        arrays4 = arrays2.get(a);

        // 3重配列の2つ目を展開
        int b = rnd.nextInt(arrays4.size());
        
        decidePosition = arrays4.get(b);
    
        return decidePosition;
    }

    // 探す
    public ArrayList<int[]> seekPosition(int[] placedPosition, PlayerBase me, PlayerBase enemy) {
        //// ルール的におけるのか
        // 全方位を調べる
        // 上下左右、右上左上、右下左下
        positionX = placedPosition[0];
        positionY = placedPosition[1];
        
        int[][] change = {{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1}};
		int[][] directionsB = new int[8][2];
		
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

        ArrayList<ArrayList<int[]>> turnOverlists = new ArrayList<>();
        ArrayList<int[]> resultList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
        	if(directionsB[i] == null) {
				continue;
			} else {
				boolean mePlaced = me.position[directionsB[i][0]][directionsB[i][1]];
				boolean enemyPlaced = enemy.position[directionsB[i][0]][directionsB[i][1]];
				
	            //// 当コマの周囲に相手のコマがあるか。なければcontinue
	            if (enemyPlaced && !(mePlaced)) {
	                // 相手のコマがある場合、その方位の探索リストを作る
	                ArrayList<int[]> searchList = new ArrayList<>();
	                searchList = SearchList2(i, positionX, positionY);
	                turnOverlists.add(searchList);
	            } else {
	                continue;
	            }
			}
        }

        // 探索リストのなかで条件に見合うものを絞り込む
        for (int j = 0; j < turnOverlists.size(); j++) {
            for (int k = 0; k < turnOverlists.get(j).size(); k++) {
                //// 次の項目を連続けて確認していったとき、相手or空き。相手>次の項目をみる 空き>return
                if (!(me.position[turnOverlists.get(j).get(k)[0]][turnOverlists.get(j).get(k)[1]])
                        && !(enemy.position[turnOverlists.get(j).get(k)[0]][turnOverlists.get(j).get(k)[1]])) {
                    // 空きマスが見つかった
                    int[] empC = new int[2];
                    empC[0] = turnOverlists.get(j).get(k)[0];
                    empC[1] = turnOverlists.get(j).get(k)[1];
                    resultList.add(empC);
                    break;
                } else if (enemy.position[turnOverlists.get(j).get(k)[0]][turnOverlists.get(j).get(k)[1]]) {
                    // 敵のコマが置いてあった場合
                    continue;
                } else {
                    break;
                }
            }
        }

        return resultList;
    }
}
