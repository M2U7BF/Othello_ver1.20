package com.example.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

@Component
public class Sound {
	Clip clip;
	URL sounUrl[] = new URL[30];
	private final ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	public Sound(){
		try {
			sounUrl[0] = resourceLoader.getResource("sounds/choicing.wav").getURL();
			sounUrl[1] = resourceLoader.getResource("sounds/draw.wav").getURL();
			sounUrl[2] = resourceLoader.getResource("sounds/lose.wav").getURL();
			sounUrl[3] = resourceLoader.getResource("sounds/pass.wav").getURL();
			sounUrl[4] = resourceLoader.getResource("sounds/placing.wav").getURL();
			sounUrl[5] = resourceLoader.getResource("sounds/win.wav").getURL();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setFile(int i) {
		try {

			AudioInputStream ais = AudioSystem.getAudioInputStream(sounUrl[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void play() {
		if (clip == null) return;
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
