package com.mygdx.game.AIControl;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;

public abstract class Move implements MovementInterface{
    private int speed;
    private AI entityAI;
    
    //default constructor
    public Move(){
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

    public void setEntityAI(AI newEntityAI){
        this.entityAI = newEntityAI;
    }
}
