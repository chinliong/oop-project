package com.mygdx.game.AIControl;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;

public abstract class Movement implements MovementInterface{
    private int speed;
    private AI entityAI;
    //default constructor
    public Movement(){
        this.speed = 0;
        this.entityAI = null;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setSpeed(int new_speed){
        this.speed = new_speed;
    }

    public AI getEntityAI(){
        return this.entityAI;
    }

    public void setEntityAI(AI new_entityAI){
        this.entityAI = new_entityAI;
    }

    
    /*public void movementFall(AI entityAI, int speed, int posY){
        entityAI.setPosY((int) (entityAI.getPosY() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosY()== 0) entityAI.setPosY(posY);
    }
   
    public void movementLeft(AI entityAI, int speed, int posX){
        entityAI.setPosX((int) (entityAI.getPosX() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosX()== 0) entityAI.setPosY(posX);
    }
    
    public void movementRight(AI entityAI, int speed, int posX){
        entityAI.setPosX((int) (entityAI.getPosX() + speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosX()==1200) entityAI.setPosY(posX);
    }*/
}
