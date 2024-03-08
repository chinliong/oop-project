package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

public class AI extends Entity{

    private String AIObject;

    
    public AI() {
        //coordinates are randomly generated within the game screen
        super("whale.png", (800), (int)(Math.random() * 500));
        this.AIObject = "whale.png"; //default AIObject
    }
    
    //constructor for thrashbin with coordinates as parameters
    public AI(String image,int posX, int posY) {
        super(image, posX, posY);
        this.AIObject = image;
    }
    
    public AI(String image) {
        //coordinates are randomly generated within the game screen
    	 super(image, (int)(Math.random() * 700), 200 + (int)(Math.random() * 300));
        this.AIObject = image; //default AIObject
    }

	

}
