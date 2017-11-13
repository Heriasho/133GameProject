package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MapCommand extends Command{
	private GameWorld gw;
	public MapCommand() {
		super("Map");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.map();
	}
}
