package com.mygdx.game.GameLayer.Entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameEngine.Camera;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameEngine.Entities.Player;
import com.mygdx.game.GameEngine.Managers.PlayerControlManager;

public class PlayerGame extends Player{
	private int playerHealth = 3;
	private int scoreCounter = 0;
	private PlayerControlManager pcm;
	private Camera mCamera;
	
	 private List<CollidableEntity> pickedupEntities = new ArrayList<>();

	public PlayerGame(Camera camera)
	{
		super();
		mCamera = camera;
		
	}
	
	public PlayerGame()
	{
		super();
	}
	
	// getter setters
	public int getPlayerHealth() {
		return playerHealth;
	}
	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
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
        return new ArrayList<>(pickedupEntities); // Returns a copy to protect the original list
    }
    public List<CollidableEntity> getPickedupEntityList() {
        return this.pickedupEntities; // Returns a copy to protect the original list
    }

    // Method to update the positions of attached entities
    public void updateAttachedEntities() {
        for (CollidableEntity entity : pickedupEntities) {
            entity.setPosX(this.getPosX());
            entity.setPosY(this.getPosY());
        }
    }
    
}
