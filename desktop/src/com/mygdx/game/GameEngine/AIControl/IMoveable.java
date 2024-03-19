package com.mygdx.game.GameEngine.AIControl;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameEngine.Entities.AI;

public interface IMoveable {
    default void moveLeft(AI entityAI, float speed) {
        entityAI.setPosX((int) (entityAI.getPosX() - speed * Gdx.graphics.getDeltaTime()));
        if (entityAI.getPosX() <= 0) entityAI.setPosX(0); // Reset to 0 if it goes beyond the screen boundary
    }

    default void moveRight(AI entityAI, float speed) {
        entityAI.setPosX((int) (entityAI.getPosX() + speed * Gdx.graphics.getDeltaTime()));
        if (entityAI.getPosX() >= Gdx.graphics.getWidth()) entityAI.setPosX(Gdx.graphics.getWidth());
    }

    default void moveDown(AI entityAI, float speed) {
        entityAI.setPosY((int) (entityAI.getPosY() - speed * Gdx.graphics.getDeltaTime()));
        if (entityAI.getPosY() <= 0) entityAI.setPosY(0); // Reset to 0 if it goes below the screen
    }

    default void moveUp(AI entityAI, float speed) {
        entityAI.setPosY((int) (entityAI.getPosY() + speed * Gdx.graphics.getDeltaTime()));
        if (entityAI.getPosY() >= Gdx.graphics.getHeight()) entityAI.setPosY(Gdx.graphics.getHeight());
    }

    default void setPosition(AI entityAI, float posX, float posY) {
        entityAI.setPosX(posX);
        entityAI.setPosY(posY);
    }
}
