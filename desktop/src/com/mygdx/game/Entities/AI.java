package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

public class AI extends CollidableEntity{

    private String AIObject;
    private String type;
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
    
    //To create AI with random coordinate
    public AI(String image) {
        //coordinates are randomly generated within the game screen
    	 super(image, (int)(Math.random() * 700), 200 + (int)(Math.random() * 300));
        this.AIObject = image; //default AIObject
    }
    
    
    // Method to get the name of the AIObject (the texture name or image file name)
    public String getAIObjectName() {
        return this.AIObject;
    }
	

}
