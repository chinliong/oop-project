package com.mygdx.game.GameEngine.Entities;


public class AI extends CollidableEntity{

    private String AIObject;
    
    // Constructor for thrashbin with coordinates as parameters
    public AI(String image,int posX, int posY) {
        super(image, posX, posY);
        this.AIObject = image;
    }
    
    // Method to get the name of the AIObject (the texture name or image file name)
    public String getAIObjectName() {
        return this.AIObject;
    }
    

}
