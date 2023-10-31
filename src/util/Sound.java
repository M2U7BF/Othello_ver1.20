package util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL sounUrl[] = new URL[30];
	
	public Sound(){
		sounUrl[0] = getClass().getResource("sounds/choicing.wav");
		sounUrl[1] = getClass().getResource("sounds/draw.wav");
		sounUrl[2] = getClass().getResource("sounds/lose.wav");
		sounUrl[3] = getClass().getResource("sounds/pass.wav");
		sounUrl[4] = getClass().getResource("sounds/placing.wav");
		sounUrl[5] = getClass().getResource("sounds/win.wav");
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
