package com.mygdx.game.Entities;


import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Player extends CollidableEntity {
	private CollidableEntity pickedupEntity = null; 

    public Player() {
    	// Entity constructor 1
        super("fish.png", 0, 10);
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
    
   // Method to attach a CollidableEntity to the player
    public void attachEntity(CollidableEntity entity) {
    	if (this.pickedupEntity == null) {
    	 this.pickedupEntity = entity; // Set the current picked-up entity
    	}
    }

    // Method to update the positions of attached entities
    public void updateAttachedEntities() {
    	 if (this.pickedupEntity != null) { // Check if there is an attached entity
    	        this.pickedupEntity.setPosX(this.getPosX());
    	        this.pickedupEntity.setPosY(this.getPosY());
    	    }
    }

    

}
