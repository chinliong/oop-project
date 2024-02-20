package com.mygdx.game.AIControl;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;

public class MoveHorizontal extends Move{
    private int posX;
    
    public MoveHorizontal(){
        super();
    }
    public int getPosX(){
        return this.posX;
    }
    public void setPosX(int new_posX){
        this.posX = new_posX;
    }
    @Override
    public void moveLeft(AI entityAI, int speed, int posX){
        entityAI.setPosX((int) (entityAI.getPosX() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosX()<= 0) entityAI.setPosX(posX);
    }
    @Override
    public void moveRight(AI entityAI, int speed, int posX){
        entityAI.setPosX((int) (entityAI.getPosX() + speed)); 
        if(entityAI.getPosX()>=800) entityAI.setPosX(posX);
    }
    @Override
    public void moveDown(AI entityAI, int speed, int posY){
        //Do nothing since these are vertical movements
    }
    @Override
    public void moveUp(AI entityAI, int speed, int posY){
        //Do nothing since these are vertical movements
    }
}
