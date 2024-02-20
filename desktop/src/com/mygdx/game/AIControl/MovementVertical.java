package com.mygdx.game.AIControl;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;

public class MovementVertical extends Movement{
    private int posY;
    public MovementVertical(){
        super();
    }
    public int getPosY(){
        return this.posY;
    }
    public void setPosY(int new_posY){
        this.posY = new_posY;
    }
    @Override
    public void movementFall(AI entityAI, int speed, int posY){
        entityAI.setPosY((int) (entityAI.getPosY() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosY()== 0) entityAI.setPosY(posY);
    }
    @Override
    public void movementFly(AI entityAI, int speed, int posY){
        entityAI.setPosY((entityAI.getPosY()+speed));
        //System.out.println(entityAI.getPosY()); 
        if(entityAI.getPosY()>= 800){
            entityAI.setPosY(posY);
        }
    }
    @Override
    public void movementLeft(AI entityAI, int speed, int posY){
        //Do nothing since these are horizontal movement
    }
    @Override
    public void movementRight(AI entityAI, int speed, int posY){
        //Do nothing since these are horizontal movement
    }
}
