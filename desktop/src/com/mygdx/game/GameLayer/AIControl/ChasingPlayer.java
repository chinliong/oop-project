package com.mygdx.game.GameLayer.AIControl;

import com.mygdx.game.GameLayer.Entities.PlayerGame;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameEngine.Managers.AIControlManager;
import com.mygdx.game.GameEngine.AIControl.iBehaviour;

public class ChasingPlayer implements iBehaviour {
		
	private AIControlManager aiControlManager;
	 
	public ChasingPlayer(AIControlManager aiControlManager) {
	        this.aiControlManager = aiControlManager;
	    
	}
	 
	@Override
	public void execute(PlayerGame player, SimulationLifeCycleManager game, AI ai) {
	        chase(player, game, ai);
	}
	 
    public void chase(PlayerGame player, SimulationLifeCycleManager game, AI ai) {
        float dx = player.getPosX() - ai.getPosX();
        float dy = player.getPosY() - ai.getPosY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance > 0) {
            float nx = dx / distance;
            float ny = dy / distance;
            float speed = 1.4f; 
            float nextPosX = ai.getPosX() + nx * speed;
            float nextPosY = ai.getPosY() + ny * speed;

            ai.setPosX(nextPosX);
            ai.setPosY(nextPosY);
        }
    }
}
