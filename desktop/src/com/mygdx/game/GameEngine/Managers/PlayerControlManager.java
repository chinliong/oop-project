package com.mygdx.game.GameEngine.Managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.Player;

public class PlayerControlManager {

	private SimulationLifeCycleManager game;
	private IOManager ioManager;

	// Empty constructor
	public PlayerControlManager(SimulationLifeCycleManager game) {
		this.game = game;
		this.ioManager = game.getInputOutputManager();
	}

	public void walk(Player player) {

		if (ioManager.getInputKeyboard().ifDPressed()) { // Checks if the right arrow key is pressed
															// for moving right
			// Moves the player to the right by increasing the X position
			player.setPosX(player.getPosX() + (int) (200 * Gdx.graphics.getDeltaTime()));
		} else if (ioManager.getInputKeyboard().ifAPressed()) {
			// Moves the player to the left by decreasing the X position if any key other
			// than right is pressed
			player.setPosX(player.getPosX() - (int) (200 * Gdx.graphics.getDeltaTime()));
		}

	}

	// Processes jumping movement based on a boolean flag
	public void jump(Player player) {
		// If the jump flag is true, moves the player upwards
		if (ioManager.getInputKeyboard().ifWPressed()) {
			player.setPosY(player.getPosY() + (int) (200 * Gdx.graphics.getDeltaTime()));
		} else if (ioManager.getInputKeyboard().ifSPressed()) {

			// If the jump flag is false, moves the player downwards
			player.setPosY(player.getPosY() - (int) (200 * Gdx.graphics.getDeltaTime()));

		}
	}

}
