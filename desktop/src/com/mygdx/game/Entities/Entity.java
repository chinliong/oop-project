package com.mygdx.game.Entities;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//entity abstract class
public abstract class Entity{
    private Texture entityImage;
    private int posX;
    private int posY;

    private int healthBar;

    private Sprite entitySprite;
    private static int entityID = 0 ;
    private int id;

    public Entity(Texture entityImage, int posX, int posY) {
        this.entityImage = entityImage;
        this.posX = posX;
        this.posY = posY;

        this.entitySprite = new Sprite(entityImage);
        this.entitySprite.setPosition(posX, posY);
        this.entitySprite.setSize(100, 100);

        this.id = entityID++;

    }
    public Rectangle getBounds() {
        return entitySprite.getBoundingRectangle();
    }

    //might be overriden by the subclasses
    public void draw(SpriteBatch batch) {
        this.entitySprite.setPosition(posX, posY); // this needs to happen to update the position of the sprite as when i am drawing using rhe sprite , the default set position is used
        this.entitySprite.draw(batch);
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

    public int getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(int healthBar) {
        this.healthBar = healthBar;
    }


    public void dispose() {
        entityImage.dispose();
    }

    public int getID(){
        return this.id;
    }

}


