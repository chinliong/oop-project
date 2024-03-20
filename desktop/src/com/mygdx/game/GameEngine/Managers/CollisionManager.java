package com.mygdx.game.GameEngine.Managers;

import java.util.List;

import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.Player;
import com.mygdx.game.GameEngine.Screens.WinLoseScreen;
import com.mygdx.game.GameLayer.Entities.Bin;
import com.mygdx.game.GameLayer.Entities.Monster;
import com.mygdx.game.GameLayer.Entities.PlayerGame;
import com.mygdx.game.GameLayer.Entities.Recyclables;

import java.util.ArrayList;

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


	public void checkForCollision(SimulationLifeCycleManager game) {
	    PlayerGame playerEntity = null;
	    for (Entity entity : game.getEntityManager().getEntities()) {
	        if (entity instanceof Player) {
	            playerEntity = (PlayerGame) entity;
	            break;
	        }
	    }
	    if (playerEntity == null) return; // No player found, exit the method

	    
		for (Entity entity : game.getEntityManager().getEntities()) {
			if (entity instanceof PlayerGame) {
				playerEntity = (PlayerGame) entity; // if is type player = typecast it
				break;
			}
		}
		if (playerEntity == null)
			return; // No player found, so exit the method
		for (Entity entity : game.getEntityManager().getEntities()) {
			if (entity instanceof AI) {
				AI aiEntity = (AI) entity;
				int distance = game.getEntityManager().getCollisionManager().getCollisionRange();
				

				if (playerEntity.hasCollided(aiEntity, distance)) // use iCollision
				{

					if(aiEntity instanceof Recyclables)
					{
						Recyclables rEntity = (Recyclables) aiEntity;
						if (collidableList.contains(rEntity))
						{
							playerEntity.attachEntity(rEntity);
							break;
						}
					}
					
					if (aiEntity instanceof Bin)
					{
						Bin bEntity = (Bin) aiEntity;
						if(playerEntity.getPickedUpEntities().size() != 0)
						{
							Recyclables rEntity = (Recyclables) playerEntity.getPickedUpEntities().get(0);
							// same type = score increase
							if (bEntity.getType() == rEntity.getType())
							{
								entitiesToRemove.add(rEntity);
								playerEntity.setScoreCounter(playerEntity.getScoreCounter() +1);
								System.out.println("my score is " + playerEntity.getScoreCounter());
								System.out.println("ur entitiescarry " + playerEntity.getPickedUpEntities().size());
								break;
							}
							
							// different type = score same
							if (bEntity.getType() != rEntity.getType())
							{
								entitiesToRemove.add(rEntity);
								System.out.println("my score is unchanged " + playerEntity.getScoreCounter());
								System.out.println("ur entitiescarry " + playerEntity.getPickedUpEntities().size());
								break;
							}
							
						}
					}
					
					if (aiEntity instanceof Monster)
					{
						game.getAudioManager().playSound();
						checkForCollisionTest(game);
						//handlePlayerAICollision(game);
						return;
					}
//					// If monster catches player, player die
//					if (aiEntity.getAIObjectName().equals("1.png")) {
//						game.getAudioManager().playSound();
//						handlePlayerAICollision(game);
////						checkForCollisionTest(game);
//						return;
//					}

					// Attach recyclable to entity
//	                else if (aiEntity.getAIObjectName().matches("plastic.png|glass.png|paper.png|can.png")) {
//	                    playerEntity.attachEntity(aiEntity);
//	                    break;
//	                } 
	                // Handling disposal into bins with correct type matching
//	                else if (aiEntity.getAIObjectName().endsWith("bin.png")) {
//	                    if (!playerEntity.getPickedupEntityList().isEmpty()) {
//	                        AI pickedUpEntity = (AI) playerEntity.getPickedupEntityList().get(0);
//	                        String entityType = pickedUpEntity.getAIObjectName().replace(".png", "");
//	                        String binType = aiEntity.getAIObjectName().replace("bin.png", "");
//
//	                        boolean disposalCorrect = entityType.equals(binType);
//	                        if (disposalCorrect) {
//	                            playerEntity.setScoreCounter(playerEntity.getScoreCounter() + 1);
//	                            System.out.println(entityType + " disposed correctly in " + binType + " bin. Score increased.");
//	                        } else {
//	                            playerEntity.setScoreCounter(playerEntity.getScoreCounter() - 0);
//	                            System.out.println(entityType + " disposed incorrectly in " + binType + " bin. Score remained the same.");
//	                        }
//
//	                        entitiesToRemove.add(pickedUpEntity);
//	                        break; // Stop checking after handling disposal for one item
//	                    }
//	                }
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


	public void checkForCollisionTest(SimulationLifeCycleManager game) {
		PlayerGame playerShip = null;
		// First, find the Playership
		for (Entity entity : game.getEntityManager().getEntities()) {
			if (entity instanceof PlayerGame) {
				playerShip = (PlayerGame) entity;
				break; // Assuming there's only one Playership, we can break after finding it
			}
		}

		// If a Player was found, check for collisions with AI entities
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

	// Sets the collision range for collision detection
	public void setCollisionRange(int collisionRange) {
		this.collisionRange = collisionRange;
	}

	// Returns the current collision range
	public int getCollisionRange() {
		return collisionRange;
	}
}
