package com.mygdx.game.GameEngine.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


//entity abstract class
public abstract class Entity{
    protected Texture entityImage;
    
    protected float posX; //Y position of entity
    protected float posY; //Y position of entity
    protected float width = 90; // width of entity
    protected float height = 100; // height of entity

    private static int idCounter = 0 ;
    protected int id;

    // Constructor 1: set position X and Y based on argument
    public Entity(String entityImagePath, float posX, float posY) {
        this.entityImage = new Texture(Gdx.files.internal(entityImagePath));
        this.posX = posX;
        this.posY = posY;

        this.id = idCounter++; // Assign an id to this entity, idCounter++ = track IDs count
    }    
    // Constructor 2: pre-set position X and Y
    public Entity(String entityImagePath) {
        this.entityImage = new Texture(Gdx.files.internal(entityImagePath));
        this.posX = 50;
        this.posY = 5;

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

    public int getID(){
        return this.id;
    }
    

    
    
//    collide checks
//    @Override
//   public boolean hasCollided(Entity cEntity, int range)
//    {
//		return Math.abs(this.getPosX() - cEntity.getPosX()) < range && Math.abs(this.getPosY()- cEntity.getPosY()) < range;
//    	
//    }
//    
//    @Override
//   public boolean hasCollidedRect(Entity cEntity) {
//	    // Get the bounding rectangles for this entity and the other entity
//	    Rectangle thisBounds = this.getBounds();
//	    Rectangle otherBounds = cEntity.getBounds();
//
//	    // Check if the two rectangles overlap, indicating a collision
//	    return thisBounds.overlaps(otherBounds);
//	}

}


