package com.mygdx.game.Entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends  Entity {

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

}
