package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//Teleports the spaceship to a random alien
	public class TeleportToAlienCommand extends Command{
		private GameWorld gw;
		public TeleportToAlienCommand() {
			super("TeleToAlien");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.teleportToAlien();
		}
	}