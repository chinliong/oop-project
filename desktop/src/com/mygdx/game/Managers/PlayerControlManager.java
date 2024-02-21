package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Entities.Player;

public class PlayerControlManager {
	
	// Constructor for the player control manager
	public PlayerControlManager() {
		
	}
	
	// Processes walking movement based on the input key
	public void walk(Player player, int key) {
		// Checks if the right arrow key is pressed for moving right
		if (key == Input.Keys.RIGHT) {
			// Moves the player to the right by increasing the X position
            // The movement speed is adjusted by the frame rate to ensure consistent speed across different devices
			player.setPosX(player.getPosX() + (int) (200 * Gdx.graphics.getDeltaTime()));
		} else {
			// Moves the player to the left by decreasing the X position if any key other than right is pressed
            // This could potentially be refined to check for a specific key (like LEFT) for leftward movement
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
            // This part could be used for handling falling or crouching if modified to check for specific conditions
			player.setPosY(player.getPosY() - (int) (200 * Gdx.graphics.getDeltaTime()));

		}
	}


}
