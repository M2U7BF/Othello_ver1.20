package util;

import java.net.URL;

import javax.imageio.ImageIO;

public class Img {
public URL[] img = new URL[10];

	public Img() {
		img[0] = getClass().getResource("img/blackStone.png");
		img[1] = getClass().getResource("img/blackStone2.png");
		img[2] = getClass().getResource("img/board.png");
		img[3] = getClass().getResource("img/emptyFrame.png");
		img[4] = getClass().getResource("img/emptyFrame2.png");
		img[5] = getClass().getResource("img/errorImage.png");
		img[6] = getClass().getResource("img/passerrorImage.png");
		img[7] = getClass().getResource("img/whiteStone.png");
		img[8] = getClass().getResource("img/whiteStone2.png");
	};
}
