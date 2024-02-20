package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends  Entity {

    private Sprite playerSprite;
    public Player() {
        super(new Texture("bucket.png"), 0, 10);
        
        this.playerSprite = new Sprite(getEntityImage());
        this.playerSprite.setPosition(getPosX(), getPosY());
        this.playerSprite.setSize(100, 100);

    }
    // Method to get the bounding rectangle of the player sprite for collision detection
    public Rectangle getBounds() {
        return playerSprite.getBoundingRectangle();
    }


}
