package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MapView extends Container implements Observer {
	private Graphics myGraphics;
	private GameWorld gw;
	private GameCollection gcp;
	private GameObject obj;

	public MapView(GameWorld gw) {
		this.gw = gw;
		gcp = gw.getGameCollection();
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.GRAY);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());

		System.out.println("paint running!");

		Iiterator drawGameIterator = gcp.getIterator();
		while (drawGameIterator.hasNext()) {
			obj = (GameObject) drawGameIterator.getNext();
			obj.draw(g, pCmpRelPrnt);
		}
	}

	public void update(Observable o, Object gwp) {
		this.repaint();
		System.out.println("update running!");
	}
}