package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command{
	public HelpCommand() {
		super("Help");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Provides helpful tips");
	}
}
