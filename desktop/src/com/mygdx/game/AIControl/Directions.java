package com.mygdx.game.AIControl;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;

public class Directions extends Move{
    private int posX;
    private int posY;
    
    public Directions(){
        super();
    }
    public int getPosX(){
        return this.posX;
    }
    public void setPosX(int new_posX){
        this.posX = new_posX;
    }   

    public int getPosY(){
        return this.posY;
    }
    public void setPosY(int new_posY){
        this.posY = new_posY;
    }
    
    public void moveLeft(AI entityAI, int speed, int posX){
        entityAI.setPosX((int) (entityAI.getPosX() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosX()<= 0) entityAI.setPosX(posX);
    }
    
    public void moveRight(AI entityAI, int speed, int posX){
        entityAI.setPosX((int) (entityAI.getPosX() + speed)); 
        if(entityAI.getPosX()>=800) entityAI.setPosX(posX);
    }
   
    public void moveDown(AI entityAI, int speed, int posY){
        entityAI.setPosY((int) (entityAI.getPosY() - speed * Gdx.graphics.getDeltaTime())); 
        if(entityAI.getPosY()== 0) entityAI.setPosY(posY);
    }
    
    public void moveUp(AI entityAI, int speed, int posY){
        entityAI.setPosY((entityAI.getPosY()+speed));
        if(entityAI.getPosY()>= 800){
            entityAI.setPosY(posY);
        }
    }
}
