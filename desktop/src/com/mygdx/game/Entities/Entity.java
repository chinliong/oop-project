package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//entity abstract class
public abstract class Entity{
    private Texture entityImage;
    
    private int posX; //Y position of entity
    private int posY; //Y position of entity
    private int width = 90; // width of entity
    private int height = 80; // height of entity

    private static int idCounter = 0 ;
    private int id;


    public Entity(String entityImagePath, int posX, int posY) {
        this.entityImage = new Texture(Gdx.files.internal(entityImagePath));
        this.posX = posX;
        this.posY = posY;

        this.id = idCounter++; // Assign an id to this entity, idCounter++ = track IDs count
    }
    
    
    public Rectangle getBounds() {
        // Rectangle to represent the collision space
        return new Rectangle(posX, posY, width, height);
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
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public int getWidth()
    {
    	return width;
    }
    public void setWidth(int width)
    {
    	this.width = width;
    }
    
    public int getHeight()
    {
    	return height;
    }
    
    public void setHeight(int height)
    {
    	this.height = height;
    }

    public void dispose() {
        entityImage.dispose();
    }

    public int getID(){
        return this.id;
    }

}


