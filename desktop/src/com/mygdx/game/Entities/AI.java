package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

public class AI extends Entity{

    private String AIObject;

    public AI() {
        //coordinates are randomly generated within the game screen
        super("whale.png", (800), (int)(Math.random() * 500));
        this.AIObject = "whale.png"; //default AIObject
    }

}
