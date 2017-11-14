package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/*Open the door & update the score according to the types
 * & conditions of opponents that are let in to the spaceship
 * as described above in rules of play.
 * This causes all of the opponents whose centers are within 
 * the boundaries of the bounding square of the door to be removed
 * from the game world.*/
public class OpenDoorCommand extends Command{
	private GameWorld gw;
	public OpenDoorCommand(GameWorld gw) {
		super("Open Door");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.openDoor();
	}
}