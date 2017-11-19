package com.mycompany.a3;

import java.io.IOException;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.mycompany.a3.GameWorld;

public class BGsound extends Sound implements Runnable {
	private GameWorld gw;
	
	public BGsound(String file, GameWorld gw) {
		super(file, gw);
		this.gw = gw;
	}
	
	public void run() {
		if(gw.getIsPlaying() == false) return;
		boolean canReplay = (m != null);
		if(canReplay) {
			super.m.setTime(0);
			super.play();
		}
	}
}