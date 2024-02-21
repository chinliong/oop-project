package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Entities.Player;

public class PlayerControlManager {

	public PlayerControlManager() {
		
	}

	public void walk(Player player, int key) {
		if (key == Input.Keys.RIGHT) {
			player.setPosX(player.getPosX() + (int) (200 * Gdx.graphics.getDeltaTime()));
		} else {
			player.setPosX(player.getPosX() - (int) (200 * Gdx.graphics.getDeltaTime()));
		}

	}

	public void jump(Player player, boolean jump) {
		if (jump)
		{
			player.setPosY(player.getPosY() + (int) (200 * Gdx.graphics.getDeltaTime()));
		}
		else {
			player.setPosY(player.getPosY() - (int) (200 * Gdx.graphics.getDeltaTime()));

		}
	}


}
