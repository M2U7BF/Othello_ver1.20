package player;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class Computer extends PlayerBase{
	int positionX =0;
	int positionY =0;
	
	public void placing(int[] placedPosition,Me me,Computer computer,JLabel error,JLabel computerScoreLabel,JLabel myScoreLabel,JLabel[][] llLliB,JLabel[][] llLliW) {
		//石を置く場所を決める
		
		//正誤判定
		if (true
//				!(me.position[placedPosition[0]][placedPosition[1]])
//				&& !(computer.position[placedPosition[0]][placedPosition[1]])
//                && (boolean) computer.canPlacing(placedPosition, computer, me).get("result")
                ) {

            // 置く石の種類を指定し、配置
            if (computer.getFirst()) {
                JLabel lliitem = llLliB[placedPosition[0]][placedPosition[1]];
                lliitem.setVisible(true);
            } else {
                JLabel lliitem = llLliW[placedPosition[0]][placedPosition[1]];
                lliitem.setVisible(true);
            }

            // 置いたことをログする
            computer.position[placedPosition[0]][placedPosition[1]] = true;
            computerScoreLabel.setText("相手のスコア : " + String.valueOf(computer.getScore()));

            //// コマを置いたときの処理
            int comScore = computer.getScore() + 1;
            computer.setScore(comScore);
            
            //ターン交代
            me.isMyTurn = true;
            computer.isMyTurn = false;
        }

	}
	
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
        System.out.println("arraysのサイズ:" +String.valueOf(arrays.size()));

        //それぞれのコマにseekPosition()(置ける場所を探す)実行。コマに紐づく配置用座標らを取得。arraylistに代入取得
        for(int i=0;i < arrays.size();i++){
        	ArrayList<int[]> arrays3 = new ArrayList<>();
        	System.out.println("arraysの要素の姿:"+String.valueOf(arrays.get(i)[0])+","+String.valueOf(arrays.get(i)[1]));
        	
    		arrays3 = seekPosition(arrays.get(i), com, me);
    		System.out.println("array3のサイズ : "+String.valueOf(arrays3));
    		
        	if(arrays3.size() > 0){
        		arrays2.add(arrays3);
        	}
        }
        System.out.println("arrays2のサイズ:" +String.valueOf(arrays2.size()));

        //取得したarraylistからランダムもしくは最適化する形で1つの値に決定
		Random rnd = new Random();
		
		//3重リストのarray2の1つ目を展開
		ArrayList<int[]> arrays4 = new ArrayList<>();
		int a = rnd.nextInt(arrays2.size());
		arrays4 = arrays2.get(a);
		
		//3重配列の2つ目を展開
		int b = rnd.nextInt(arrays4.size());
		decidePosition = arrays4.get(b);

		return decidePosition;
	}
	
	public ArrayList<int[]> seekPosition(int[] placedPosition, Computer com, Me me) {
		//// ルール的におけるのか
		// 全方位を調べる
		// 上下左右、右上左上、右下左下
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
		
		ArrayList<ArrayList<int[]>> turnOverlists = new ArrayList<>();
		ArrayList<int[]> resultList = new ArrayList<>();
		
		for (int j = 0; j < 7; j++) {
			////当コマの周囲に相手のコマがあるか。なければcontinue
			if (directions2[i] && !(directions[i])) {
				//相手のコマがある場合、その方位の探索リストを作る
				ArrayList<int[]> searchList = SearchList2(i,positionX,positionY);
				turnOverlists.add(searchList);
			} else {
				continue;
			}
		}
		
		//探索リストのなかで条件に見合うものを絞り込む
		for(int j=0; j < turnOverlists.size(); j++) {
			for(int k=0; k < turnOverlists.get(j).size(); k++) {
				////次の項目を連続けて確認していったとき、相手or空き。相手>次の項目をみる 空き>return
				if(!(com.position[turnOverlists.get(j).get(k)[0]][turnOverlists.get(j).get(k)[1]]) && (com.position[turnOverlists.get(j).get(k)[0]][turnOverlists.get(j).get(k)[1]])) {
					//空きマスが見つかった
					int[] empC = new int[2];
					result = true;
					resultList.add(empC);
				}else if(me.position[turnOverlists.get(j).get(k)[0]][turnOverlists.get(j).get(k)[1]]) {
					continue;
				} else {
					break;
				}
			}
		}
		
		System.out.println("seek探索結果は" + result + "でした。@computer");

		return resultList;
	}

//		for (i = 0; i < 7; i++) {
//			System.out.println("seekposition,for回数:" + String.valueOf(i));
//			//当コマの周囲に相手のコマがあるか。なければcontinue
//			if (directions2[i] && !(directions[i])) {
//				//相手のコマがあればその位置に移動
//				positionFix(i, com, me); 
//				//更にその先に仲間のコマがあるか
//				if (!(directions[i]) && !(directions2[i])) {
//					positionFix(i, com, me); 
//					list3[0] = positionX;
//					list3[1] = positionY;
//					turnOverlists.add(list3);
//					
//					result = true; continue; 
//				} else if (directions2[i] && !(directions[i])) {
//					positionFix(i, com, me); 
					// 3
//					if (!(directions[i]) && !(directions2[i])) {
//						positionFix(i, com, me); 
//						list4[0] = positionX;
//						list4[1] = positionY;
//						turnOverlists.add(list4);
//						
//						result = true; continue;
//					} else if (directions2[i] && !(directions[i])) {
//						positionFix(i, com, me); 
//						// 4
//						if (!(directions[i]) && !(directions2[i])) {
//							positionFix(i, com, me); 
//							list5[0] = positionX;
//							list5[1] = positionY;
//							turnOverlists.add(list5);
//							
//							result = true; continue;
//						} else if (directions2[i] && !(directions[i])) {
//							positionFix(i, com, me); 
//							// 5
//							if (!(directions[i]) && !(directions2[i])) {
//								positionFix(i, com, me); 
//								list6[0] = positionX;
//								list6[1] = positionY;
//								turnOverlists.add(list6);
//								
//								result = true; continue;
//							} else if (directions2[i] && !(directions[i])) {
//								positionFix(i, com, me); 
//								//6
//								if (!(directions[i]) && !(directions2[i])) {
//									positionFix(i, com, me); 
//									list7[0] = positionX;
//									list7[1] = positionY;
//									turnOverlists.add(list7);
//									
//									result = true; continue;
//								} else if (directions2[i] && !(directions[i])) {
//									positionFix(i, com, me); 
//									//7
//									if (!(directions[i]) && !(directions2[i])) {
//										positionFix(i, com, me); 
//										list8[0] = positionX;
//										list8[1] = positionY;
//										turnOverlists.add(list8);
//										
//										result = true; continue;
//									} else if (directions2[i] && !(directions[i])) {
//										continue;
//									} else {
//										continue;
//									}
//								} else {
//									continue;
//								}
//							} else {
//								continue;
//							}
//						} else {
//							continue;
//						}
//					} else {
//						continue;
//					}
//				} else {
//					continue;
//				}
//			} else {
//				continue;
//			}
//
//		}
//		System.out.println("seekPositionの結果は" + result + "でした。");
//		
//		//置ける場所 のリストを返す
//		return turnOverlists;
//	}
	
//	public Map<String, Object> ifs(int i,Computer com,Me me, boolean result, ArrayList<int[]> turnOverlists) {
//		// 元きた場所に対する直線上を探す
//		Map<String, Object> values = new HashMap<String, Object>();
//		// 2
//		if (!(directions[i]) && !(directions2[i])) {
//			try {positionFix(i, com, me); } catch ( ArrayIndexOutOfBoundsException aiobe) { 
//				result = false;
//				values.put("result",result);
//				return values;
//						}
//			int[] list3 = new int[2];
//			list3[0] = positionX;
//			list3[1] = positionY;
//			turnOverlists.add(list3);
//			
//			result = true; 
//		} else if (directions2[i] && !(directions[i])) {
//			try {positionFix(i, com, me); } catch ( ArrayIndexOutOfBoundsException aiobe) {
//				result = false;
//				values.put("result",result);
//				return values;
//			}
//			// 3
//			if(i < 8 && i > 0) {
//				ifs(i,com,me,result,turnOverlists);
//			}
//		} else {
//		}
//		
//		values.put("result",result);
//		values.put("turnPosition",turnOverlists);
//		
//		return values;
//		
	
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
}

