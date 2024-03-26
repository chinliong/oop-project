package com.mygdx.game.GameEngine.Managers;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.*;

public class EntityManager {

    private ArrayList<Entity> entityList;
    private AIControlManager AIControlManager;
    private CollisionManager collisionManager;
    private PlayerManager playerManager;
    
    public EntityManager(SimulationLifeCycleManager game) {
        this.entityList = new ArrayList<>();
        AIControlManager = new AIControlManager();
        collisionManager = new CollisionManager();
        this.playerManager = game.getPlayerManager();
    }
    // creates new entity
    public void addEntity(Entity entity) {
        entityList.add(entity);
        if (entity instanceof Player)
        {
        	playerManager.addPlayer((Player) entity);
        }
        if (entity instanceof AI)
        {
        	AIControlManager.addAI((AI) entity);
        }
        if (entity instanceof CollidableEntity)
        {
        	collisionManager.addCollidableList((CollidableEntity)entity);
        }
    }
    public void removeEntity(Entity entity) {
        entityList.remove(entity);
        if (entity instanceof Player)
        {
        	playerManager.removePlayer((Player) entity);
        }
        if (entity instanceof AI)
        {
        	AIControlManager.removeAI((AI) entity);
        }
        if (entity instanceof CollidableEntity)
        {
        	collisionManager.removeCollidable((CollidableEntity)entity);
        }
    }
    
    
    public Entity checkClass(Class<? extends Entity> entityClass) 
    {			// Class<? extends Entity> allows Entity's subclass as an argument
    	for (Entity entity: entityList)
    	{
    		if (entity.getClass().equals(entityClass)) // if entity class in loop matches argument
    		{
    			return entity;
    		}
    	}
    	return null;
    }


// show all entities
    public List<Entity> getEntities() {
        return entityList;
    }
    
    //get entity by class
    public <T extends Entity> List<T> getEntitiesByClass(Class<T> entityClass) {
        List<T> filteredEntities = new ArrayList<>();
        for (Entity entity : entityList) {
            if (entityClass.isInstance(entity)) {
                filteredEntities.add(entityClass.cast(entity));
            }
        }
        return filteredEntities;
    }


    public void disposeEntities() {
        for (Entity entity : entityList) {
            
            entity.dispose();
        }
        entityList.clear(); // clear ArrayList
    }
    
    public CollisionManager getCollisionManager(){
        return collisionManager;
    }
    
    public AIControlManager getAIControlManager(){
    	return AIControlManager;
    }
    
    
}
