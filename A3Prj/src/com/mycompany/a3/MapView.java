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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.Random;

public class MapView extends Container implements Observer {
	private Graphics myGraphics;
	private Game g;
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
	private Astronaut selectedAstro;
	private HealCommand healCommand = new HealCommand(gw, selectedAstro);
	private String[] bgImages = { "chromaCosmos1.jpg", "cosmosBackground1.jpg",
			"chaosCircle1.jpg" };

	public MapView(GameWorld gw, Game g) {
		this.g = g;
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
		if(debug)System.out.println("POINTER PRESS HAS BEEN FOUND");
		if(debug)System.out.println("Mouse coords. x: " + x + " y: " + y);
		if(debug)System.out.println("Container coords. x: " + getX() + " y: " + getY());
		if(debug)System.out.println(getParent().getAbsoluteX());
		if(debug)System.out.println(getParent().getAbsoluteY());
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		if(debug)System.out.println("Modified Mouse coords. x: " + x + " y: " + y);
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		if(debug)System.out.println("POINTER PRESS HAS BEEN FOUND");
		Iiterator iter = gcp.getIterator();
		while (iter.hasNext()) {
			GameObject object = (GameObject) iter.getNext();
			if(debug)System.out.println("Object you clicked : " + object);
			if (object instanceof Astronaut) {
				if(debug)System.out.println("Object is a astronaut");
				Astronaut a = (Astronaut) object;
				if(debug)System.out.println("Astronaut a : " + a);
				if ((a.contains(pPtrRelPrnt, pCmpRelPrnt))) {
					if (g.getIsPaused()) {
						if(debug)System.err.println("Astronaut is selected");
						a.setSelected(true);
						healCommand.setAstronaut(a);
						gw.healAstro(a);
					}

				} else {
					if(debug)System.err.println("Astronaut is not selected");
					a.setSelected(false);
				}
			} else {
				if(debug)System.out.println("Object is not an astronaut");
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
		if(debug)System.out.println("BG IMAGE REDRAWN");
	}
}