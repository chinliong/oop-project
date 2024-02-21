package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

public class AI extends Entity{

    private String AIObject;

    public AI() {
        //coordinates are randomly generated within the game screen
        super("whale.png", (int)(Math.random() * 800), (int)(Math.random() * 500));
        this.AIObject = "whale.png"; //default AIObject
    }

    public AI(String AIObject) {
    	super(AIObject + ".png", (int) (Math.random() * 800), (int) (Math.random() * 500));
        this.AIObject = AIObject;

        //System.out.println("Created AI object: " + AIObject);
        //System.out.print("Situational Radius: " + situationalRadius);
        //System.out.println(" X: " + this.getPosX() + " Y: " + this.getPosY());
    }

}
