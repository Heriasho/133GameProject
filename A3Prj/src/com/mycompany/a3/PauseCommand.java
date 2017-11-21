package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class PauseCommand extends Command {
    private GameWorld gw;
    private Game game;
    public PauseCommand(String command, GameWorld gw, Game game) {
        super(command);
        this.gw   = gw;
        this.game = game;
    }
    
    public void actionPerformed(ActionEvent e) {
    	gw.setIsPlaying();
        String playMessage = gw.getIsPlaying() ? "Playing" : "Paused";
        System.out.println(playMessage);
        game.pause();
    }
}

