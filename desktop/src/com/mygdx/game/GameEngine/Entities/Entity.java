package com.mygdx.game.GameEngine.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


// Entity abstract class
public abstract class Entity{
    protected Texture entityImage;
    
    protected float posX; // Y position of entity
    protected float posY; // Y position of entity
    protected float width = 90; // Width of entity
    protected float height = 100; // Height of entity

    // Constructor 1: set position X and Y based on argument
    public Entity(String entityImagePath, float posX, float posY) {
        this.entityImage = new Texture(Gdx.files.internal(entityImagePath));
        this.posX = posX;
        this.posY = posY;

    }    
    // Constructor 2: pre-set position X and Y
    public Entity(String entityImagePath) {
        this.entityImage = new Texture(Gdx.files.internal(entityImagePath));
        this.posX = 50;
        this.posY = 5;
    }    
    
    public void draw(SpriteBatch batch) {
        // Draw the texture at its position with width and height
        batch.draw(entityImage, posX, posY, width, height);
    }

    public Texture getEntityImage() {
        return entityImage;
    }

    public void setEntityImage(Texture entityImage) {
        this.entityImage = entityImage;
        this.width = entityImage.getWidth();
        this.height = entityImage.getHeight();
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }
    
    public float getWidth()
    {
    	return width;
    }
    public void setWidth(float width)
    {
    	this.width = width;
    }
    
    public float getHeight()
    {
    	return height;
    }
    
    public void setHeight(float height)
    {
    	this.height = height;
    }

    public void dispose() {
        entityImage.dispose();
    }

}


