package com.mycompany.a1;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class ScoreView extends Container implements Observer {
	private Label roamingAliens;
	private Label roamingAstronauts;
	private Label rescuedAstronauts;
	private Label rescuedAliens;
	private Label score;
	
	public ScoreView(GameWorld gw) {
		score = new Label(""+gw.getScore());
		rescuedAliens = new Label("Rescued Aliens : "+gw.getRescuedAliens());
		rescuedAstronauts = new Label(""+gw.getRescuedAstronauts());
		roamingAstronauts = new Label(""+gw.getRoamingAstronauts());
		roamingAliens = new Label(""+gw.getRescuedAliens());
	}

	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		/*This will create labels that store the values of int values shown above*/
		score.setText(""+((GameWorld) observable).getScore());
		rescuedAliens.setText("Rescued Aliens : "+((GameWorld) observable).getRescuedAliens());
		rescuedAstronauts.setText(""+((GameWorld) observable).getRescuedAstronauts());
		roamingAstronauts.setText(""+((GameWorld) observable).getRoamingAstronauts());
		roamingAliens.setText(""+((GameWorld) observable).getRoamingAliens());
		
		
	}
}
