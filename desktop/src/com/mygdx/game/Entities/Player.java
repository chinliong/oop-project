package com.mygdx.game.Entities;


import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Player extends CollidableEntity {
	 private List<CollidableEntity> pickedupEntities = new ArrayList<>();

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
        this.pickedupEntities.add(entity);
    }

    // Method to update the positions of attached entities
    public void updateAttachedEntities() {
        for (CollidableEntity entity : pickedupEntities) {
            entity.setPosX(this.getPosX());
            entity.setPosY(this.getPosY());
        }
    }

    

}
