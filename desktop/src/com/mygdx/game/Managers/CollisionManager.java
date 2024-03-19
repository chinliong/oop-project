package com.mygdx.game.Managers;

import com.mygdx.game.Entities.AI;
import com.mygdx.game.Entities.CollidableEntity;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Player;
import java.util.List;
import java.util.ArrayList;

import com.mygdx.game.SimulationLifeCycleManager;
import com.mygdx.game.Screens.WinLoseScreen;
import com.mygdx.game.Entities.PlayerGame;

public class CollisionManager {
	private int collisionRange; // The range within which entities are considered to be in collision range
	private ArrayList<CollidableEntity> collidableList;
	List<Entity> entitiesToRemove = new ArrayList<>();


	// Constructor initializes the collision manager with default collision range
	public CollisionManager() {
		collidableList = new ArrayList<>();
		this.collisionRange = 0; // Default value, implying no range. Should be set explicitly.
	}

	public void addCollidableList(CollidableEntity cEntity) {
		collidableList.add(cEntity);
	}

	public ArrayList<CollidableEntity> getCollidableList() {
		return collidableList;
	}
	
	public void removeCollidable(CollidableEntity cEntity) {
		collidableList.remove(cEntity);
	}

//    public void checkForCollision(SimulationLifeCycleManager game) {
//        Player playerEntity = null;
//        for (Entity entity : game.getEntityManager().getEntities()) {
//            if (entity instanceof Player) {
//                playerEntity = (PlayerShip) entity; // if is type player = typecast it
//                break; 
//            }
//        }
//
//        if (playerEntity == null) return; // No player found, so exit the method
//
////        for (Entity entity : game.getEntityManager().getEntities()) {
////            if (entity instanceof AI) {
////                AI aiEntity = (AI) entity;
////                	//collisionRange value set at PlayScreen
////                int distance = game.getEntityManager().getCollisionManager().getCollisionRange(); 
////                // if player and AI  < distance = collide
//////                if (Math.abs(playerEntity.getPosX() - aiEntity.getPosX()) < distance &&
//////                    Math.abs(playerEntity.getPosY() - aiEntity.getPosY()) < distance) 
////                if(playerEntity.hasCollided(aiEntity, distance)) // use icollision
////                {
////                    game.getAudioManager().playSound();
////                    playerEntity = (PlayerShip) playerEntity;
////                    playerEntity.set
////                    handlePlayerAICollision(game);
////                    return;
////                }       
////            }
////        }
//        for (Entity entity : game.getEntityManager().getEntities()) {
//            if (entity instanceof PlayerShip) { // Directly check for PlayerShip
//                PlayerShip playerShip = (PlayerShip) entity; // Correctly cast and store in a PlayerShip variable
//                if (entity instanceof AI)
//                {
//                    AI aiEntity = (AI) entity;
//                }
//                // Loop through entities again to check for collisions with AI...
//                // Assuming you're inside another loop checking for AI entities:
//            ;
//                int distance = getCollisionRange(); // Directly access the collision range from this class
//                
//                if(playerShip.hasCollided(aiEntity, distance)) {
//                    game.getAudioManager().playSound();
//                    playerShip.setPlayerHealth(playerShip.getPlayerHealth() - 1); // Correctly call getPlayerHealth method
//                    
//                    handlePlayerAICollision(game);
//                    return;
//                }
//            }
//        }
//    }
	public void checkForCollision(SimulationLifeCycleManager game) {
	    PlayerGame playerEntity = null;
	    for (Entity entity : game.getEntityManager().getEntities()) {
	        if (entity instanceof Player) {
	            playerEntity = (PlayerGame) entity;
	            break;
	        }
	    }
	    if (playerEntity == null) return; // No player found, exit the method

	    // JACOB CODES
	    // Check for collisions with bins 
//	    for (CollidableEntity entity : collidableList) {
//	        if (entity instanceof AI && ((AI) entity).getType().contains("bin")) {
//	            AI bin = (AI) entity;
//	            if (playerEntity.hasCollided(bin, collisionRange)) {
//	                List<CollidableEntity> collectedItems = new ArrayList<>(playerEntity.getPickedUpEntities());
//	                for (CollidableEntity itemEntity : collectedItems) {
//	                    if (itemEntity instanceof AI) {
//	                        AI item = (AI) itemEntity;
//	                        if (isCorrectBin(item, bin)) {
//	                            // Logic for correctly placed item
//	                            System.out.println(item.getAIObjectName() + " successfully recycled in " + bin.getAIObjectName());
//	                            playerEntity.getPickedUpEntities().remove(item);
//	                            // Optionally: Increment score or play sound here
//	                        } else {
//	                            // Logic for incorrectly placed item
//	                            System.out.println("Not in correct bin: " + item.getAIObjectName() + " cannot go into " + bin.getAIObjectName());
//	                            // Optionally: Decrement score or play different sound here
//	                        }
//	                    }
//	                }
//	            }
//	        }
//	    }
		for (Entity entity : game.getEntityManager().getEntities()) {
			if (entity instanceof PlayerGame) {
				playerEntity = (PlayerGame) entity; // if is type player = typecast it
				break;
			}
		}
		if (playerEntity == null)
			return; // No player found, so exit the method
//		System.out.println("ur removelist BEFORE:" + entitiesToRemove.size() );
		for (Entity entity : game.getEntityManager().getEntities()) {
			if (entity instanceof AI) {
				AI aiEntity = (AI) entity;
				int distance = game.getEntityManager().getCollisionManager().getCollisionRange();
				
				// Check if this AI entity has the specific image you're interested in

				// if player and AI < distance = collide
//              if (Math.abs(playerEntity.getPosX() - aiEntity.getPosX()) < distance &&
//                  Math.abs(playerEntity.getPosY() - aiEntity.getPosY()) < distance) 
				// If there is a collision with player and ai entity
				if (playerEntity.hasCollided(aiEntity, distance)) // use icollision
				{
					// If monster catches player
					if (aiEntity.getAIObjectName().equals("thrashbin.png")) {
						game.getAudioManager().playSound();
						handlePlayerAICollision(game);
						return;
					}

					// If player touches thrash entity
					else if (aiEntity.getAIObjectName().equals("bottle.png")
							|| aiEntity.getAIObjectName().equals("glass.png")
							|| aiEntity.getAIObjectName().equals("paper.png")
							|| aiEntity.getAIObjectName().equals("can.png")) {
						// Attach entity to player
//						game.getEntityManager().getEntities().remove(aiEntity);
						
						System.out.println("size listing before " + playerEntity.getPickedupEntityList().size());
						playerEntity.attachEntity(aiEntity);
						System.out.println("size listing after " + playerEntity.getPickedupEntityList().size());

						break;
					} 
				

					else if (aiEntity.getAIObjectName().equals("canbin.png"))
					{
						if(playerEntity.getPickedupEntityList().size() != 0)
						{
							System.out.println("size before" + entitiesToRemove.size());
							entitiesToRemove.add(playerEntity.getPickedupEntityList().get(0));
							System.out.println("the size is now" + entitiesToRemove.size());
							playerEntity.setScoreCounter(playerEntity.getScoreCounter() +1);
							break;
						}
					}
					
				}

			}
		}
		if (entitiesToRemove.size() > 0)
		{
			System.out.println("out");
			game.getEntityManager().getEntities().remove(entitiesToRemove.remove(0));
			playerEntity.getPickedupEntityList().remove(0);
			System.out.println("the list now is" + playerEntity.getPickedupEntityList().size());
		}
	}

	private boolean isCorrectBin(AI item, AI bin) {
	    String itemType = item.getAIObjectName().replace(".png", ""); // Removing .png for comparison
	    String binType = bin.getAIObjectName().replace("bin.png", ""); // Removing bin.png for comparison

	    return itemType.equals(binType);
	}

	public void checkForCollisionTest(SimulationLifeCycleManager game) {
		PlayerGame playerShip = null;
		// First, find the PlayerShip
		for (Entity entity : game.getEntityManager().getEntities()) {
			if (entity instanceof PlayerGame) {
				playerShip = (PlayerGame) entity;
				break; // Assuming there's only one PlayerShip, we can break after finding it
			}
		}

		// If a PlayerShip was found, check for collisions with AI entities
		if (playerShip != null) {
			for (Entity entity : game.getEntityManager().getEntities()) {
				if (entity instanceof AI) {
					AI aiEntity = (AI) entity;
					int distance = getCollisionRange(); // Directly access the collision range from this class

					// Check for collision between the playerShip and the AI entity
					if (playerShip.hasCollidedRect(aiEntity)) {
						game.getAudioManager().playSound();
						playerShip.setPlayerHealth(playerShip.getPlayerHealth() - 1);
						playerShip.setPosX(50);
						playerShip.setPosY(20);
						System.out.println("player health is now " + playerShip.getPlayerHealth());
						// Correct method call
						if (playerShip.getPlayerHealth() == 0) {
							handlePlayerAICollision(game);
						}
						return; // Exit if a collision is detected and handled
					}
				}
			}
		}

	}

	private void handlePlayerAICollision(SimulationLifeCycleManager game) {
		System.out.println("Player and AI are within the range of " + collisionRange);
		game.getSceneManager().transitionToScreen(WinLoseScreen.class, false); // Transition to the lose screen
	}

	// private void handlePickupCollision(SimulationLifeCycleManager game, AI
	// thrashEntity) {
	// }

	// Sets the collision range for collision detection
	public void setCollisionRange(int collisionRange) {
		this.collisionRange = collisionRange;
	}

	// Returns the current collision range
	public int getCollisionRange() {
		return collisionRange;
	}
}
