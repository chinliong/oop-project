package com.mygdx.game.Managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameMaster;
import com.mygdx.game.Screens.WinLoseScreen;

import java.util.List;

public class CollisionManager {
    private int proximityRange;



    public CollisionManager() {
        this.proximityRange = 0;
    }


    public boolean checkandHandleCollisions(GameMaster game, List<Entity> entities){
        // Check proximity for all entities
        for (Entity entity1 : entities) {
            for (Entity entity2 : entities) {
                if (!entity1.equals(entity2) && checkProximity(entity1, entity2, proximityRange)) {
                    // Proximity detected, handle accordingly
                    if (entity1 instanceof Player && entity2 instanceof AI ||
                            entity1 instanceof AI && entity2 instanceof Player) {
                        handlePlayerAICollision(game);
                        return true;
                    }
                }
            }
        }

        // Check if the player is out of the window bounds
        Player player = (Player) game.getEntityManager().getEntity(Player.class);
        if (player.getPosX() > Gdx.graphics.getWidth() || player.getPosY() > Gdx.graphics.getHeight()) {
            handlePlayerEscape(game);
            return true;
        }
        return false;
    }

    private boolean checkProximity(Entity entity1, Entity entity2, int range) {
        return Math.abs(entity1.getPosX() - entity2.getPosX()) < range &&
                Math.abs(entity1.getPosY() - entity2.getPosY()) < range;
    }

    private void handlePlayerAICollision(GameMaster game){
        System.out.println("Player and AI are in range of " + proximityRange);
        game.getSimulationLifeCycleManager().transitionToScreen(WinLoseScreen.class, false);
    }

    private void handlePlayerEscape(GameMaster game){
        System.out.println("Player has escaped");
        game.getSimulationLifeCycleManager().transitionToScreen(WinLoseScreen.class, true);
    }


    public void setProximityRange(int proximityRange) {
        this.proximityRange = proximityRange;
    }

    public int getProximityRange() {
        return proximityRange;
    }
}
