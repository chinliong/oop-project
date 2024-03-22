package com.mygdx.game.GameEngine.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.GameEngine.Entities.Player;

public class PlayerControlManager {
	
	// Empty constructor 
	public PlayerControlManager() {
		
	}
	
	public void walk(Player player, int key) {
	
		if (key == Input.Keys.RIGHT) { // Checks if the right arrow key is pressed for moving right
			// Moves the player to the right by increasing the X position
			player.setPosX(player.getPosX() + (int) (200 * Gdx.graphics.getDeltaTime()));
		} else {
			// Moves the player to the left by decreasing the X position if any key other than right is pressed
			player.setPosX(player.getPosX() - (int) (200 * Gdx.graphics.getDeltaTime()));
		}

	}
	
	// Processes jumping movement based on a boolean flag
	public void jump(Player player, boolean jump) {
		// If the jump flag is true, moves the player upwards
		if (jump)
		{
			player.setPosY(player.getPosY() + (int) (200 * Gdx.graphics.getDeltaTime()));
		}
		else {
			// If the jump flag is false, moves the player downwards
			player.setPosY(player.getPosY() - (int) (200 * Gdx.graphics.getDeltaTime()));

		}
	}


}
