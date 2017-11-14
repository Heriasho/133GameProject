package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/*Tell the Gw that the game clock has ticked.
 * All moving objects are told to update their positions
 * according to their current direction and speed.*/
public class TickCommand extends Command{
	private GameWorld gw;
	public TickCommand(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.tick();
	}
}
