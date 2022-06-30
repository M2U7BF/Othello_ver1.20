package player;

public class Me extends PlayerBase {
    public Me() {
    }

    public boolean meCanPlacing(Me me, Computer com) {
        // 空きコマに対しcanPlacing実行
        boolean available = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 空きコマを探す
                if (!(me.position[i][j]) && !(com.position[i][j])) {
                    // もし置åけるのであればtrue
                    int[] positon = { i, j };
                    if ((boolean) me.canPlacing(positon, me, com).get("result")) {
                        available = true;
                        break;
                    } else {
                        continue;
                    }
                }
            }
            if (available) {
                break;
            }
        }

        return available;
    }
}
