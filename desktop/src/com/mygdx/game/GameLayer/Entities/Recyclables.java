package com.mygdx.game.GameLayer.Entities;

import java.util.ArrayList;

import com.mygdx.game.GameEngine.Entities.AI;

public class Recyclables extends AI implements iType{
	private boolean isThrown = false;
	protected RecyclableType type;
	
	public Recyclables(String entityImagePath, int posX, int posY, RecyclableType type)
	{
		super(entityImagePath,posX,posY);
		this.type = type;		
	}	
	
	@Override
	public RecyclableType getType()
	{
		return this.type;
	}
	
	//Check if recyclable is thrown 
	public void setThrown(boolean thrown) {
	        isThrown = thrown;
	    }

	public boolean isThrown() {
	        return isThrown;
	    }
	
	// Static method to generate coordinates
    public static ArrayList<int[]> generateCoordinates() {
        ArrayList<int[]> coordinates = new ArrayList<>();
        while (coordinates.size() < 8) {
            int[] newCoordinate = {
                (int)(Math.random() * 700), // Screen width
                200 + (int)(Math.random() * (500 - 200)) // Min height + random(height range)
            };

            boolean isValid = true;
            for (int[] coord : coordinates) {
                if (Math.sqrt(Math.pow(newCoordinate[0] - coord[0], 2) + Math.pow(newCoordinate[1] - coord[1], 2)) < 50) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                coordinates.add(newCoordinate);
            }
        }
        return coordinates;
    }
	
}

