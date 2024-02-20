package com.mygdx.game.AIControl;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;

public class MovementHorizontal extends Movement{
    private int posX;
    
    public MovementHorizontal(){
        super();
    }
    public int getPosX(){
        return this.posX;
    }
    public void setPosX(int new_posX){
        this.posX = new_posX;
    }
    @Override
    public void movementLeft(AI entityAI, int speed, int posX){
        entityAI.setPosX((int) (entityAI.getPosX() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosX()<= 0) entityAI.setPosX(posX);
    }
    @Override
    public void movementRight(AI entityAI, int speed, int posX){
        entityAI.setPosX((int) (entityAI.getPosX() + speed)); 
        if(entityAI.getPosX()>=800) entityAI.setPosX(posX);
    }
    @Override
    public void movementFall(AI entityAI, int speed, int posY){
        //Do nothing since these are vertical movements
    }
    @Override
    public void movementFly(AI entityAI, int speed, int posY){
        //Do nothing since these are vertical movements
    }
}
