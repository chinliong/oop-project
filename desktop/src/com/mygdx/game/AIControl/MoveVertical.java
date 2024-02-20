package com.mygdx.game.AIControl;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;

public class MoveVertical extends Move{
    private int posY;
    
    public MoveVertical(){
        super();
    }
    public int getPosY(){
        return this.posY;
    }
    public void setPosY(int new_posY){
        this.posY = new_posY;
    }
    @Override
    public void moveDown(AI entityAI, int speed, int posY){
        entityAI.setPosY((int) (entityAI.getPosY() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosY()== 0) entityAI.setPosY(posY);
    }
    @Override
    public void moveUp(AI entityAI, int speed, int posY){
        entityAI.setPosY((entityAI.getPosY()+speed));
        //System.out.println(entityAI.getPosY()); 
        if(entityAI.getPosY()>= 800){
            entityAI.setPosY(posY);
        }
    }
    @Override
    public void moveLeft(AI entityAI, int speed, int posY){
        //Do nothing since these are horizontal movement
    }
    @Override
    public void moveRight(AI entityAI, int speed, int posY){
        //Do nothing since these are horizontal movement
    }
}
