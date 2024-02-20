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

    
}
