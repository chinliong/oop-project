package com.mygdx.game.GameLayer.Entities;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameEngine.Entities.Player;

public class PlayerGame extends Player{
	private int scoreCounter = 0;
	private List<CollidableEntity> pickedupEntities = new ArrayList<>();
	
	public PlayerGame()
	{
		super();
	}
	
	public int getScoreCounter() {
		return scoreCounter;
	}
	public void setScoreCounter(int scoreCounter) {
		this.scoreCounter = scoreCounter;
	}
	   // Method to attach a CollidableEntity to the player
    public void attachEntity(CollidableEntity entity) {
    	final int maxCount = 1;
    	if(this.pickedupEntities.size() < maxCount)
    	{
            this.pickedupEntities.add(entity);
            System.out.println(" attach entity added "+ entity + "into list");
    	}
  
    }
    
    public List<CollidableEntity> getPickedUpEntities() {
        return pickedupEntities; // Returns a copy to protect the original list
    }


    // Method to update the positions of attached entities
    public void updateAttachedEntities() {
        for (CollidableEntity entity : pickedupEntities) {
            entity.setPosX(this.getPosX());
            entity.setPosY(this.getPosY());
        }
    }
    
   //Method to detach attached entity from the player
    public CollidableEntity detachLastAttachedEntity() {
        if (!pickedupEntities.isEmpty()) {
            return pickedupEntities.remove(pickedupEntities.size() - 1); // Remove and return the last entity
        }
        return null; // Return null if there are no attached entities
    }
    
   // Method to check if there are any attached entities
    public boolean hasAttachedEntities() {
        return !pickedupEntities.isEmpty();
    }
    
 // Method to detach and throw the last attached entity towards a given direction
    public void throwAttachedEntity(Vector2 direction) {
        if (!pickedupEntities.isEmpty()) {
            CollidableEntity entity = pickedupEntities.remove(pickedupEntities.size() - 1); // Detach the last entity
            if (entity instanceof Recyclables) { 
            float throwStrength = 100; // Adjust this value based on your game's scale
            
            // Calculate the new position based on the direction and throw strength
            float newX = this.getPosX() + direction.x * throwStrength;
            float newY = this.getPosY() + direction.y * throwStrength;
            
            entity.setPosX(newX);
            entity.setPosY(newY);
            ((Recyclables) entity).setThrown(true);
            Timer.schedule(new Task(){
                @Override
                public void run() {
                    ((Recyclables) entity).setThrown(false);
                }
            }, (float) 0.2); // Delay in seconds
            }
           
        
        }
      
    }
    
}
