package com.mygdx.game.GameEngine.Entities;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.mygdx.game.GameEngine.InputOutput.InputObserver;

public abstract class Player extends CollidableEntity implements InputObserver {
	private int playerHealth = 3;

    public Player() {
    	// Entity constructor 2
    		super("fish.png");
    }
    
    @Override
    public void onKeyEvent(KeyEvent event) {
        // Respond to key events, e.g., move the player
    }

    @Override
    public void onMouseEvent(MouseEvent event) {
        // Optional if Player needs to respond to mouse events
    }
    
 // getter setters
 	public int getPlayerHealth() {
 		return playerHealth;
 	}
 	public void setPlayerHealth(int playerHealth) {
 		this.playerHealth = playerHealth;
 	}

}
