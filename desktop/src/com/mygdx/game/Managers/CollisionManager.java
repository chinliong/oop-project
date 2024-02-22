package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.AI;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameMaster;
import com.mygdx.game.Screens.WinLoseScreen;

import java.util.List;

public class CollisionManager {
    private int collisionRange; // The range within which entities are considered to be in collision range

    // Constructor initializes the collision manager with default collision range
    public CollisionManager() {
        this.collisionRange = 0; // Default value, implying no range. Should be set explicitly.
    }
    
    public void checkForCollision(GameMaster game) {
        Player playerEntity = null;
        for (Entity entity : game.getEntityManager().getEntities()) {
            if (entity instanceof Player) {
                playerEntity = (Player) entity; // if is type player = typecast it
                break; 
            }
        }

        if (playerEntity == null) return; // No player found, so exit the method

        for (Entity entity : game.getEntityManager().getEntities()) {
            if (entity instanceof AI) {
                AI aiEntity = (AI) entity;
                	//collisionRange value set at PlayScreen
                int distance = game.getCollisionManager().getCollisionRange(); 
                // if player and AI  < distance = collide
                if (Math.abs(playerEntity.getPosX() - aiEntity.getPosX()) < distance &&
                    Math.abs(playerEntity.getPosY() - aiEntity.getPosY()) < distance) {
                    game.getAudioManager().playSound();
                    handlePlayerAICollision(game);
                    return;
                }       
            }
        }
    }

//    // Checks for and handles collisions between entities and with the screen bounds
//    public boolean checkandHandleCollisions(GameMaster game, List<Entity> entities) {
//        // Iterate through all entity pairs to check collision range
//        for (Entity entity1 : entities) {
//            for (Entity entity2 : entities) {
//                // Ensure we're not comparing an entity to itself and check range
//                if (!entity1.equals(entity2) && checkRange(entity1, entity2, collisionRange)) {
//                    // Range detected, handle specific collision scenarios
//                    if (entity1 instanceof Player && entity2 instanceof AI ||
//                            entity1 instanceof AI && entity2 instanceof Player) {
//                        handlePlayerAICollision(game); // Handle collision between player and AI
//                        return true; // Indicate that a collision was handled
//                    }
//                }
//            }
//        }
//
//        // Check if the player has moved outside the screen bounds
//        Player player = (Player) game.getEntityManager().checkClass(Player.class);
//        if (player.getPosX() > Gdx.graphics.getWidth() || player.getPosY() > Gdx.graphics.getHeight()) {
//            handlePlayerFled(game); // Handle player fleeing the screen
//            return true; // Indicate that a collision was handled
//        }
//        return false; // No collisions were handled
//    }
//
//    // Checks if two entities are within a specified range of each other
//    private boolean checkRange(Entity entity1, Entity entity2, int range) {
//        return Math.abs(entity1.getPosX() - entity2.getPosX()) < range &&
//               Math.abs(entity1.getPosY() - entity2.getPosY()) < range;
//    }

    // Handles collision between the player and an AI
    private void handlePlayerAICollision(GameMaster game) {
        System.out.println("Player and AI are within the range of " + collisionRange);
        game.getSceneManager().transitionToScreen(WinLoseScreen.class, false); // Transition to the lose screen
    }

//    // Handles scenario when the player moves out of the screen bounds
//    private void handlePlayerFled(GameMaster game) {
//        System.out.println("Player has fled");
//        game.getSimulationLifeCycleManager().transitionToScreen(WinLoseScreen.class, true); // Transition to the win screen
//    }

    // Sets the collision range for collision detection
    public void setCollisionRange(int collisionRange) {
        this.collisionRange = collisionRange;
    }

    // Returns the current collision range
    public int getCollisionRange() {
        return collisionRange;
    }
}
