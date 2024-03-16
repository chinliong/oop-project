package com.mygdx.game.AIControl;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;

public class Directions implements IMoveable{
    private float posX;
    private float posY;
    
    public Directions(){
        super();
    }
    public float getPosX(){
        return this.posX;
    }
    public void setPosX(float new_posX){
        this.posX = new_posX;
    }   

    public float getPosY(){
        return this.posY;
    }
    public void setPosY(float new_posY){
        this.posY = new_posY;
    }
    
    public void moveLeft(AI entityAI, float speed, float posX){
        entityAI.setPosX((int) (entityAI.getPosX() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosX()<= 0) entityAI.setPosX(posX);
    }
    
    public void moveRight(AI entityAI, float speed, float posX){
        entityAI.setPosX((int) (entityAI.getPosX() + speed)); 
        if(entityAI.getPosX()>=800) entityAI.setPosX(posX);
    }
   
    public void moveDown(AI entityAI, float speed, float posY){
        entityAI.setPosY((int) (entityAI.getPosY() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosY()== 0) entityAI.setPosY(posY);
    }
    
    public void moveUp(AI entityAI, float speed, float posY){
        entityAI.setPosY((entityAI.getPosY()+speed));
        if(entityAI.getPosY()>= 800){
            entityAI.setPosY(posY);
        }
    }
    
    public void setPosition(AI entity, float x, float y) {
        entity.setPosX(x); 
        entity.setPosY(y); 
    } 
}
