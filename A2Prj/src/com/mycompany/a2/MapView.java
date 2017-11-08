package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;

public class MapView extends Container implements Observer{
	
	public MapView(GameWorld gw) {
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.CYAN);
	}

	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		/*Will display game objects on the map*/
	}
}
