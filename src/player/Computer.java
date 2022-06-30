package player;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends PlayerBase{
	int positionX =0;
	int positionY =0;
	
	//決める
	public int[] decidePosition(Computer com, Me me) {
		int[] decidePosition = new int[2];
        ArrayList<int[]> arrays = new ArrayList<>();
        ArrayList<ArrayList<int[]>> arrays2 = new ArrayList<>();

        //置かれているマスの座標をarraylistに格納
        for(int i=0; i < com.position.length ; i++){
            for(int j=0; j < com.position.length; j++){
                if(com.position[i][j]){
                	int[] list = new int[2];
                	list[0] = i;
                	list[1] = j;
                	arrays.add(list);
                }
            }
        }
        
        //それぞれのコマにseekPosition()(置ける場所を探す)実行。コマに紐づく配置用座標らを取得。arraylistに代入取得
        for(int i=0;i < arrays.size();i++){
        	ArrayList<int[]> arrays3 = new ArrayList<>();
        	
    		arrays3 = seekPosition(arrays.get(i), com, me);
    		
        	if(arrays3.size() > 0){
        		arrays2.add(arrays3);
        	}
        }
        
        if(arrays2.size() > 0) {
	        //取得したarraylistからランダムもしくは最適化する形で1つの値に決定
			Random rnd = new Random();
			
			//3重リストのarray2の1つ目を展開
			ArrayList<int[]> arrays4 = new ArrayList<>();
			int a = rnd.nextInt(arrays2.size());
			arrays4 = arrays2.get(a);
			
			//3重配列の2つ目を展開
			int b = rnd.nextInt(arrays4.size());
			decidePosition = arrays4.get(b);
        } else if(arrays2.size() == 0) {
        	//置けるマスが無い場合
        	Pass(com,me);
        }

		return decidePosition;
	}
	
	//探す
	public ArrayList<int[]> seekPosition(int[] placedPosition, Computer com, Me me) {
		//// ルール的におけるのか
		// 全方位を調べる
		// 上下左右、右上左上、右下左下
		positionX = placedPosition[0];
		positionY = placedPosition[1];
		
		if(positionY > 0) {
			directions[0] = com.position[positionX][positionY - 1];// 上
			directions2[0] = me.position[positionX][positionY - 1];// 上
		}
		if(positionX < 7 && positionY > 0) {
			directions[1] = com.position[positionX + 1][positionY - 1];// 右上
			directions2[1] = me.position[positionX + 1][positionY - 1];// 右上
		}
		if(positionX < 7) {
			directions[2] = com.position[positionX + 1][positionY];// 右
			directions2[2] = me.position[positionX + 1][positionY];// 右
		}
		if (positionX < 7 && positionY < 7) {
			directions[3] = com.position[positionX + 1][positionY + 1];// 右下
			directions2[3] = me.position[positionX + 1][positionY + 1];// 右下
		}
		if(positionY < 7) {
			directions[4] = com.position[positionX][positionY + 1];// 下
			directions2[4] = me.position[positionX][positionY + 1];// 下
		}
		if(positionX > 0 && positionY < 7) {
			directions[5] = com.position[positionX - 1][positionY + 1];// 左下
			directions2[5] = me.position[positionX - 1][positionY + 1];// 左下
		}
		if(positionX > 0) {
			directions[6] = com.position[positionX - 1][positionY];// 左
			directions2[6] = me.position[positionX - 1][positionY];// 左
		}
		if(positionX > 0 && positionY > 0) {
			directions[7] = com.position[positionX - 1][positionY - 1];// 左上
			directions2[7] = me.position[positionX - 1][positionY - 1];// 左上
		}
		
		ArrayList<ArrayList<int[]>> turnOverlists = new ArrayList<>();
		ArrayList<int[]> resultList = new ArrayList<>();
		
		for (int j = 0; j < 7; j++) {
			////当コマの周囲に相手のコマがあるか。なければcontinue
			if (directions2[j] && !(directions[j])) {
				//相手のコマがある場合、その方位の探索リストを作る
				ArrayList<int[]> searchList = new ArrayList<>();
				searchList = SearchList2(j,positionX,positionY);
				turnOverlists.add(searchList);
			} else {
				continue;
			}
		}
		
		//探索リストのなかで条件に見合うものを絞り込む
		for(int j=0; j < turnOverlists.size(); j++) {
			for(int k=0 ; k < turnOverlists.get(j).size() ; k++) {
				////次の項目を連続けて確認していったとき、相手or空き。相手>次の項目をみる 空き>return
				if(!(com.position[turnOverlists.get(j).get(k)[0]][turnOverlists.get(j).get(k)[1]]) && !(me.position[turnOverlists.get(j).get(k)[0]][turnOverlists.get(j).get(k)[1]])) {
					//空きマスが見つかった
					int[] empC = new int[2];
					empC[0] = turnOverlists.get(j).get(k)[0];
					empC[1] = turnOverlists.get(j).get(k)[1];
					resultList.add(empC);
					break;
				}else if(me.position[turnOverlists.get(j).get(k)[0]][turnOverlists.get(j).get(k)[1]]) {
					//敵のコマが置いてあった場合
					continue;
				} else {
					break;
				}
			}
		}
		
		return resultList;
	}
}

