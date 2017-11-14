package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class DeleteCommand extends Command{
	private GameWorld gw;
	public DeleteCommand(GameWorld gw) {
		super("Delete");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Delete command is invoked...");
	}
}