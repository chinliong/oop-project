package com.mygdx.game.GameEngine.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Player extends CollidableEntity {
	private int playerHealth = 3;

    public Player() {
    	// Entity constructor 1
        super("fish.png", 300, 200);
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
