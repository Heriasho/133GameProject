package com.mycompany.a3;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
//import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.Random;
public class MapView extends Container implements Observer {
	private Graphics myGraphics;
	private GameWorld gw;
	private GameCollection gcp;
	private GameObject obj;
	private static final Random R = Random.getInstance();
	private final boolean debug = false;
    private Image bgImage;
    private Container mvContainer;
    private String[] bgImages = {
		"cluster.jpg",
		"milkyway.jpg",
		"milkyway2.jpg",
		"nebulae.jpg",
		"pillars.jpg"
    };

	public MapView(GameWorld gw) {
		this.gw = gw;
		gcp = gw.getGameCollection();
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.GRAY);
	   	this.setLayout(new FlowLayout());
    	//this.getAllStyles().setBgTransparency(255);
        this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLUE));
    	
        bgImage = null;
		try {
			bgImage = Image.createImage("/" + bgImages[R.nextInt(0, bgImages.length-1)]);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        mvContainer = new Container();
        
        gw.setEndWidth(this.getWidth());
        gw.setEndHeight(this.getHeight());
	}

	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		if (debug) {
			System.out.println("paint running!");
		}
		if(bgImage != null) {
			g.drawImage(bgImage, getX(), getY(), getWidth(), getHeight());
		} else {
			System.err.println("Cannot display background");
		}
		Iiterator drawGameIterator = gcp.getIterator();
		while (drawGameIterator.hasNext()) {
			obj = (GameObject) drawGameIterator.getNext();
			obj.draw(g, pCmpRelPrnt);
		}
	}

	public void update(Observable o, Object gwp) {
		if(gw.getIsPlaying()) this.repaint();
		System.out.println("BG IMAGE REDRAWN");
	}
}