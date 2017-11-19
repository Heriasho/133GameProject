package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command {
    GameWorld gw;
    Game game;
    
    public SoundCommand(String command, GameWorld gw, Game game) {
        super(command);
        this.gw = gw;
    }
    
    public void actionPerformed(ActionEvent e) {
        // Allow the sound to be changed even when paused
        //if(gw.isPlaying) {
        	gw.setIsSoundOn();
    		gw.muteSound(); // toggle bgmusic
            System.out.println("Toggle Sound");
        //}
    }
}
