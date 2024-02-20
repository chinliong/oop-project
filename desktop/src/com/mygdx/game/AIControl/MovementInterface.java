package com.mygdx.game.AIControl;

import com.mygdx.game.Entities.AI;

public interface MovementInterface {
    public void moveDown(AI entityAI, int speed, int pos);
    public void moveUp(AI entityAI, int speed, int pos);
    public void moveLeft(AI entityAI, int speed, int pos);
    public void moveRight(AI entityAI, int speed, int pos);
    
}
