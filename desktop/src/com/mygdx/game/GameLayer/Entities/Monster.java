package com.mygdx.game.GameLayer.Entities;

import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameEngine.AIControl.IChase;
import com.mygdx.game.GameEngine.AIControl.ChasingPlayer;

public class Monster extends AI {
    private IChase chasePlayer;

    public Monster() {
        super("1.png", -100, 200);
        this.chasePlayer = new ChasingPlayer(); 
    }
    // for IntroScreen
    public Monster(int posX, int posY)
    {
    	super("1.png", posX, posY);
    	this.chasePlayer = new ChasingPlayer();
    }
    
    public void setchasePlayer(IChase chasePlayer) {
        this.chasePlayer = chasePlayer;
    }

    public void chasePlayer(PlayerGame player, SimulationLifeCycleManager game) {
        if (chasePlayer != null) {
            chasePlayer.chase(player, game, this);
        }
    }
    
    public void resetMonster() {
  	  this.setPosX(-100);
  	  this.setPosY(200);
    }
	
}
