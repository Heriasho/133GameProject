package com.mycompany.a3;

import java.io.IOException;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound implements Runnable {
	protected Media m;
	//protected Media mp3;
	private static GameWorld gw;
	
	public Sound(String file, GameWorld gw) {
		this.gw = gw;
		final InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + file);
		try {
			if(is != null) {
				m = MediaManager.createMedia(is, "audio/wav", this);
				is.close();
			} else {
				System.err.print("Resource cannot be found: ");
				System.err.println(file);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {}
	
	public boolean play()  {
		if(!gw.getIsPlaying()) return false;
		
		boolean canPlay = (m != null);
		if(canPlay) {
			m.setTime(0);
			m.play();
		}
		return canPlay;
	}
	
	public boolean pause() {
		boolean canPause = (m != null);
		if(canPause) m.pause();
		return canPause;
	}
	
	public boolean resume()  {
		if((gw.getIsPlaying() == false) || m.isPlaying() || m.getTime() == 0 || (gw.getIsSoundOn() == false)){
			return false;
		}
		boolean canPlay = (m != null);
		if(canPlay) {
			m.play();
		}
		return canPlay;
	}
}