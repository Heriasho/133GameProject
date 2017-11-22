package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HealCommand extends Command {
	private static HealCommand healCommand;
	private static GameWorld gw;
	private Astronaut a;

	public HealCommand(GameWorld gw, Astronaut a) {
		super("Heal Astro");
		this.gw = gw;
	}

	public void setAstronaut(Astronaut a) {
		this.a = a;
	}

	public void planB(GameWorld gw) {
		Astronaut b = gw.getRandomAstronaut();
		healCommand.setAstronaut(b);
		gw.healAstro(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (a != null) {
			gw.healAstro(a);
		}
		/*Had plans to add in a planB, just encase you didn't select something.
		 * else { planB(gw); System.out .println(
		 * "You have to select an astronaut to heal them. \n Luckily for you, we got heat seeking heal pods availible. \b An astrobuddy of your was healed."
		 * ); }
		 */
	}

}