package com.mycompany.a1;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class MapView extends Container implements Observer{
	private GameWorld gw;
	
	public MapView(GameWorld gw) {
		gw.addObserver(this);
	}

	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

}
