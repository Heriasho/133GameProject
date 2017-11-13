package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class QuitCommand extends Command{
	private GameWorld gw;
	private boolean confirm = false;
	
	public QuitCommand() {
		super("Quit");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(confirm)
			System.exit(0);
		else{
			System.out.println("You attempted to exit the game without confirming. \nIf you wish to quit, please press 'y' then press 'x'.");
		}
	}
}