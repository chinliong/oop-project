package com.mygdx.game.GameEngine.Entities;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameLayer.InputOutput.InputObserver;

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

    @Override
    public Rectangle getBounds() {
        return new Rectangle(super.getPosX(), super.getPosY(), super.getWidth(), super.getHeight());
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    
    @Override
    public boolean hasCollided(CollidableEntity cEntity, int range)
    {
  		return Math.abs(this.getPosX() - cEntity.getPosX()) < range && Math.abs(this.getPosY()- cEntity.getPosY()) < range;
    }
    
 // getter setters
 	public int getPlayerHealth() {
 		return playerHealth;
 	}
 	public void setPlayerHealth(int playerHealth) {
 		this.playerHealth = playerHealth;
 	}

}
