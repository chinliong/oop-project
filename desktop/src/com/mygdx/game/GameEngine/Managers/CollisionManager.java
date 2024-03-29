package com.mygdx.game.GameEngine.Managers;

import java.util.List;
import java.util.ArrayList;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.Player;
import com.mygdx.game.GameLayer.Screens.WinLoseScreen;
import com.mygdx.game.GameLayer.Entities.Bin;
import com.mygdx.game.GameLayer.Entities.Monster;
import com.mygdx.game.GameLayer.Entities.PlayerGame;
import com.mygdx.game.GameLayer.Entities.Recyclables;

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
		Monster monsterEntity = null;

		// Find player entity
		for (Entity entity : game.getEntityManager().getEntities()) {
			if (entity instanceof Player) {
				playerEntity = (PlayerGame) entity;
				break;
			}
		}

		if (playerEntity == null)
			return; // No player found, exit the method

		for (Entity entity : game.getEntityManager().getEntities()) {
			if (entity instanceof Monster) {
				monsterEntity = (Monster) entity;
				break;
			}
		}

		if (monsterEntity == null)
			return; // No player found, exit the method

		for (Entity entity : game.getEntityManager().getEntities()) {
			// If entity is AI
			if (entity instanceof AI) {
				AI aiEntity = (AI) entity;
				int distance = game.getEntityManager().getCollisionManager().getCollisionRange();

				// Check for thrown item with monster
				if (monsterEntity.hasCollidedRect(aiEntity)) {
					if (aiEntity instanceof Recyclables) {
						Recyclables rEntity = (Recyclables) aiEntity;
						if (collidableList.contains(rEntity) && rEntity.isThrown()) {
							game.getAudioManager().getSound("Hit").play();
							monsterEntity.resetMonster();
							break;
						}
					}
				}
				if (playerEntity.hasCollidedRect(aiEntity)) // Use iCollision
				{
					// If player collides with recyclable, attach entity
					if (aiEntity instanceof Recyclables) {
						Recyclables rEntity = (Recyclables) aiEntity;
						if (collidableList.contains(rEntity)) {
							if (game.getInputOutputManager().getInputKeyboard().ifSpacePressed()) {
								playerEntity.attachEntity(rEntity);
								break;
							}
						}
					}

					if (aiEntity instanceof Bin) {
						Bin bEntity = (Bin) aiEntity;
						if (playerEntity.getPickedUpEntities().size() != 0) {
							Recyclables rEntity = (Recyclables) playerEntity.getPickedUpEntities().get(0);
							// same type = score increase
							if (game.getInputOutputManager().getInputKeyboard().ifSpacePressed()) {

								if (bEntity.getType() == rEntity.getType()) {
									entitiesToRemove.add(rEntity);
									playerEntity.setScoreCounter(playerEntity.getScoreCounter() + 1);
									game.getAudioManager().getSound("correct").play();
									break;
								}

								// different type = score same
								if (bEntity.getType() != rEntity.getType()) {
									entitiesToRemove.add(rEntity);
									game.getAudioManager().getSound("wrong").play();;
									break;
								}
							}
						}
					}
				}
				// if player collide with monster
				if (playerEntity.hasCollided(monsterEntity, distance)) {
					if (aiEntity instanceof Monster) {
//						game.getAudioManager().playSound();
						game.getAudioManager().getSound("Hit").play();
						playerEntity.setPlayerHealth(playerEntity.getPlayerHealth() - 1);
						playerEntity.setPosX(50);
						playerEntity.setPosY(20);
						if (playerEntity.getPlayerHealth() == 0) {
							handlePlayerAICollision(game);
						}
						return;
					}
				}

			}
		}

		if (entitiesToRemove.size() > 0) {
			System.out.println("out");
			game.getEntityManager().getEntities().remove(entitiesToRemove.remove(0));
			playerEntity.getPickedUpEntities().remove(0);
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
