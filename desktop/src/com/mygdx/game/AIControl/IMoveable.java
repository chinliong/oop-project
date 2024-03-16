package com.mygdx.game.AIControl;

import com.mygdx.game.Entities.AI;

public interface IMoveable {
    public void moveDown(AI entityAI, float speed, float pos);
    public void moveUp(AI entityAI, float speed, float pos);
    public void moveLeft(AI entityAI, float speed, float pos);
    public void moveRight(AI entityAI, float speed, float pos);
    
}
