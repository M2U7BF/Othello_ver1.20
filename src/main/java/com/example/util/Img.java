package com.example.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Component
public class Img {
	public URL[] img = new URL[20];
	private final ResourceLoader resourceLoader = new DefaultResourceLoader();

	public Img() {
		try {
			img[0] = resourceLoader.getResource("img/blackStone.png").getURL();
			img[1] = resourceLoader.getResource("img/blackStone2.png").getURL();
			img[2] = resourceLoader.getResource("img/board.png").getURL();
			img[3] = resourceLoader.getResource("img/emptyFrame.png").getURL();
			img[4] = resourceLoader.getResource("img/emptyFrame2.png").getURL();
			img[5] = resourceLoader.getResource("img/errorImage.png").getURL();
			img[6] = resourceLoader.getResource("img/passerrorImage.png").getURL();
			img[7] = resourceLoader.getResource("img/whiteStone.png").getURL();
			img[8] = resourceLoader.getResource("img/whiteStone2.png").getURL();
			img[9] = resourceLoader.getResource("img/errorImage.png").getURL();
			img[10] = resourceLoader.getResource("img/passerrorImage.png").getURL();
			img[11] = resourceLoader.getResource("img/enemyPassed.png").getURL();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	};
}
