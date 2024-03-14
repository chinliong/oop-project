package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Camera;
import com.mygdx.game.Managers.PlayerControlManager;

public class PlayerGame extends Player{
	private int playerHealth = 3;
	private int scoreCounter = 0;
	private PlayerControlManager pcm;
	private Camera mCamera;
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
}
