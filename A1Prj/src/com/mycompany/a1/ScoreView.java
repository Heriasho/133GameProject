package com.mycompany.a1;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class ScoreView extends Container implements Observer {
	private GameWorld gw;
	
	public ScoreView(GameWorld gw) {
		gw.addObserver(this);
	}

	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

	public GameWorld getGw() {
		return gw;
	}

	public void setGw(GameWorld gw) {
		this.gw = gw;
	}
}
