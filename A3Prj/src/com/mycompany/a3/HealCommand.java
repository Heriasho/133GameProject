package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HealCommand extends Command {
    private static HealCommand healCommand;
    private static GameWorld gw;
    private Astronaut a;

    public HealCommand(GameWorld gw, Astronaut a){
    	super("heal");
		this.gw = gw;
    }
    public void setAstronaut(Astronaut a){
    	this.a = a;
    }
    @Override
    public void actionPerformed(ActionEvent e){
    	if(a != null){
    		gw.healAstro(a);
    	}
    	else{
    		System.out.println("You have to select an astronaut to heal them");
    	}
    }

}