package com.mygdx.game.GameEngine.Entities;

public class AI extends CollidableEntity{

    private String AIObject;

	public AI() { 
        // Coordinates are randomly generated within the game screen
        super("whale.png", (800), (int)(Math.random() * 500));
        this.AIObject = "whale.png"; //default AIObject
    }
    
    // Constructor for thrashbin with coordinates as parameters
    public AI(String image,int posX, int posY) {
        super(image, posX, posY);
        this.AIObject = image;
    }
    
    // To create AI with random coordinate
    public AI(String image) {
        // Coordinates are randomly generated within the game screen
    	super(image, (int)(Math.random() * 700), 200 + (int)(Math.random() * 300));
        this.AIObject = image; //default AIObject
    }    
    
    // Method to get the name of the AIObject (the texture name or image file name)
    public String getAIObjectName() {
        return this.AIObject;
    }
	

}
