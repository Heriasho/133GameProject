package com.mycompany.a1;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer {
	private GameWorld gw;
	private Label roamingAliens;
	private Label roamingAstronauts;
	private Label rescuedAstronauts;
	private Label rescuedAliens;
	private Label score;
	
	public ScoreView(GameWorld gw) {
		this.gw = gw;
	}

	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		/*This will create labels that store the values of int values shown above*/
	}

	public GameWorld getGw() {
		return gw;
	}

	public void setGw(GameWorld gw) {
		this.gw = gw;
	}
}
