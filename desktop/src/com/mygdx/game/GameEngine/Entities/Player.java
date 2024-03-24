package com.mygdx.game.GameEngine.Entities;


import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Player extends CollidableEntity {

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

}
