package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

public class ScoreView extends Container implements Observer {
	private Label roamingAliens;
	private Label roamingAstronauts;
	private Label rescuedAstronauts;
	private Label rescuedAliens;
	private Label score;
	
	public ScoreView(GameWorld gw) {
		this.getAllStyles().setPadding(Component.TOP, 50);
		score = new Label("Score : "+gw.getScore());
		rescuedAliens = new Label("\tRescued Aliens : "+gw.getRescuedAliens());
		roamingAliens = new Label("\tRoaming Aliens : "+gw.getRoamingAliens());
		rescuedAstronauts = new Label("\tRescued Astronauts : "+gw.getRescuedAstronauts());
		roamingAstronauts = new Label("\tRoaming Astronauts :"+gw.getRoamingAstronauts());
		this.add(score);
		this.add(rescuedAliens);
		this.add(roamingAliens);
		this.add(rescuedAstronauts);
		this.add(roamingAstronauts);
	}

	public void update(Observable observable, Object data) {
		System.out.println("Hurray I was called");
		score.setText(""+((GameWorld) observable).getScore());
		rescuedAliens.setText("Rescued Aliens : "+((GameWorld) observable).getRescuedAliens());
		roamingAliens.setText("Roaming Aliens : "+((GameWorld) observable).getRoamingAliens());
		rescuedAstronauts.setText("Rescued Astronauts : "+((GameWorld) observable).getRescuedAstronauts());
		roamingAstronauts.setText("Roaming Astronauts : "+((GameWorld) observable).getRoamingAstronauts());
		revalidate();
	}
}
