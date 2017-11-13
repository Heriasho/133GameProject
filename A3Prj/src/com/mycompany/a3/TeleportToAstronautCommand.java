package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//Teleports the spaceship to a random Astronaut
	public class TeleportToAstronautCommand extends Command{
		private GameWorld gw;
		public TeleportToAstronautCommand() {
			super("TeleToAstro");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.teleportToAstronaut();
		}
	}