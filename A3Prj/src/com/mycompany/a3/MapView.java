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
import com.codename1.ui.Label;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.Random;

public class MapView extends Container implements Observer {
	private Graphics myGraphics;
	private GameWorld gw;
	private GameCollection gcp;
	private GameObject obj;
	private int x = 0;
	private int y = 0;
	private static final Random R = Random.getInstance();
	private final boolean debug = false;
	private final boolean debugerr = false;
	private boolean gameOver = false;
	private Image bgImage;
	private Container mvContainer;
	private String[] bgImages = { "chromaCosmos1.jpg", "cosmosBackground1.jpg",
			"chaosCircle1.jpg" };

	public MapView(GameWorld gw) {
		this.gw = gw;
		gcp = gw.getGameCollection();
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.GRAY);
		this.setLayout(new FlowLayout());
		// this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBorder(
				Border.createLineBorder(2, ColorUtil.GREEN));

		bgImage = null;
		try {
			bgImage = Image.createImage("/"
					+ bgImages[R.nextInt(0, bgImages.length - 1)]);
		} catch (IOException e) {
			e.printStackTrace();
		}

		mvContainer = new Container();
		/*
		 * XXX DO literally nothing fam gw.setMapViewWidth(this.getWidth()); ===
		 * 0 gw.setMapViewHeight(this.getHeight()); === 0
		 */
	}

	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		if (debug) {
			System.out.println("paint running!");
		}
		if (bgImage != null) {
			g.drawImage(bgImage, getX(), getY(), getWidth(), getHeight());
			if (debugerr) {
				System.err.println("MapView's width: " + this.getWidth());
				System.err.println("MapView's height: " + this.getHeight());
				System.err.println("MapView's X: " + this.getX());
				System.err.println("MapView's Y: " + this.getY());
			}
		} else {
			System.err.println("Cannot display background");
		}
		Iiterator drawGameIterator = gcp.getIterator();
		while (drawGameIterator.hasNext()) {
			obj = (GameObject) drawGameIterator.getNext();
			// pCmpRelPrnt = new
			// Point((int)obj.getLocation().getX(),(int)obj.getLocation().getY());
			obj.draw(g, pCmpRelPrnt);
		}
	}

	/*
	 * XXX THIS WILL CHECK FOR CURSER CALLS AND SET SOMETHING TO 'SELECTED' IF
	 * SELECTED. ONLY ASTRONAUT CUZ THEY ARE SPECIAL SNOWFLAKES
	 */
	@Override
	public void pointerPressed(int x, int y) {
		System.out.println("POINTER PRESS HAS BEEN FOUND");
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		Iiterator iter = gcp.getIterator();
		while (iter.hasNext()) {
			GameObject object = (GameObject) iter.getNext();
			if(object instanceof Astronaut){
				if (((Astronaut) object).contains(pPtrRelPrnt, pCmpRelPrnt)) {
					((Astronaut) object).setSelected(true);
				} else {
					((Astronaut) object).setSelected(false);
				}	
			}
		}
		repaint();
	}

	public void update(Observable o, Object gwp) {
		if (gw.getIsPlaying()) {
			this.repaint();
		}
		if (gameOver == true) {
			Label gameOver = new Label("Game Over");
			this.add(gameOver);
		}
		System.out.println("BG IMAGE REDRAWN");
	}
}