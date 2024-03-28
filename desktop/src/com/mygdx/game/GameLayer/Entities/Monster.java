package com.mygdx.game.GameLayer.Entities;

import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameLayer.AIControl.ChasingPlayer;

public class Monster extends AI {
	private ChasingPlayer chasingPlayer;

	public Monster(ChasingPlayer chasingPlayer) {
        super("1.png", -100, 200);
        this.chasingPlayer = chasingPlayer;
    }
	
	// Method to for chasing behavior
    public void chase(PlayerGame player, SimulationLifeCycleManager game) {
        if (this.chasingPlayer != null) {
            this.chasingPlayer.chase(player, game, this);
        }
    }
    
	public void resetMonster() {
		this.setPosX(-100);
		this.setPosY(200);
	}

}
