package com.mygdx.game.GameEngine.AIControl;

import com.mygdx.game.GameLayer.Entities.PlayerGame;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.AI;

public class ChasingPlayer implements IChase {
    @Override
    public void chase(PlayerGame player, SimulationLifeCycleManager game, AI ai) {
        float dx = player.getPosX() - ai.getPosX();
        float dy = player.getPosY() - ai.getPosY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance > 0) {
            float nx = dx / distance;
            float ny = dy / distance;
            float speed = 1; 
            float nextPosX = ai.getPosX() + nx * speed;
            float nextPosY = ai.getPosY() + ny * speed;

            ai.setPosX(nextPosX);
            ai.setPosY(nextPosY);
        }
    }
}
