package com.mygdx.game.AIControl;

import com.mygdx.game.Entities.AI;

public interface MovementInterface {
    public void movementFall(AI entityAI, int speed, int pos);
    public void movementFly(AI entityAI, int speed, int pos);
    public void movementLeft(AI entityAI, int speed, int pos);
    public void movementRight(AI entityAI, int speed, int pos);
    
}
