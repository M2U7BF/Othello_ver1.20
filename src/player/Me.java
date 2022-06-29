package player;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Me extends PlayerBase{
	public Me(){}
	
	public void placing(int[] placedPosition,Me me,Computer computer,JLabel error,JLabel computerScoreLabel,JLabel myScoreLabel,JLabel[][] llLliB,JLabel[][] llLliW) {
//		boolean result = (boolean) me.canPlacing(placedPosition, computer, me).get("result");
		if (!(me.position[placedPosition[0]][placedPosition[1]]) && !(computer.position[placedPosition[0]][placedPosition[1]]) && (boolean) me.canPlacing(placedPosition, computer, me).get("result")) {

            // 置く石の種類を指定し、配置
            if (me.getFirst()) {
                JLabel lliitem = llLliB[placedPosition[0]][placedPosition[1]];
                lliitem.setVisible(true);
            } else if (!(me.getFirst())){
                JLabel lliitem = llLliW[placedPosition[0]][placedPosition[1]];
                lliitem.setVisible(true);
            }

            // 置いたことをログする
            me.position[placedPosition[0]][placedPosition[1]] = true;
            myScoreLabel.setText("自分のスコア : " + String.valueOf(me.getScore()));

            //// コマを置いたときの処理
            int meScore = me.getScore() + 1;
            me.setScore(meScore);
            
            //ターン交代
            me.isMyTurn = false;
            computer.isMyTurn = true;
        } else {
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

}
