package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.AI;

public class Monster extends AI {
	
	
	public Monster()
	{
		super("1.png", 200, 10);
	}
	
	
	// insert movement code to chase player
	
	public void chasePlayer(PlayerGame player, SimulationLifeCycleManager game)
	{
		float dx = player.getPosX() - this.getPosX();
		float dy = player.getPosY() - this.getPosY();
		 float distance = (float) Math.sqrt(dx * dx + dy * dy);
       if (distance > 0) { // To avoid division by zero
           // Normalize direction vector
           float nx = dx / distance;
           float ny = dy / distance;
           // Move monsterEntity towards the player
           float speed = 1; // Adjust speed as needed
           float monsterposX = this.getPosX() + nx * speed;
           float monsterposY = this.getPosY() + ny * speed;

          this.setPosX(monsterposX);
          this.setPosY(monsterposY);
          Gdx.app.log("After chasePlayer()", "pEntity X: " + player.getPosX() + " pEntityY: " + player.getPosY());}
//      //Make monster entity follow player
//      if (pEntity != null) {
//          AI monsterEntity = null;
//          // Find the monsterEntity in the list of entities
//          for (Entity entity : game.getEntityManager().getEntities()) {
//              if (entity instanceof AI) {
//                  AI ai = (AI) entity;
//                  
//                  // Check if this AI entity is the monsterEntity
//                  if (ai.getAIObjectName().equals("1.png")) {
//                  	monsterEntity = ai;
//                      break;
//                  }
//              }
//          }
	}
	
	 public void reset() {
	        // Reset monster's position and other relevant attributes
	        setPosX(200);
	        setPosY(10);
	        // Reset other attributes as needed
	    }
	
//	 // If monsterEntity is found, move it towards pEntity
//    if (monsterEntity != null) {
//        // Calculate direction vector from monsterEntity to pEntity
//        float dx = pEntity.getPosX() - monsterEntity.getPosX();
//        float dy = pEntity.getPosY() - monsterEntity.getPosY();
//        float distance = (float) Math.sqrt(dx * dx + dy * dy);
//        if (distance > 0) { // To avoid division by zero
//            // Normalize direction vector
//            float nx = dx / distance;
//            float ny = dy / distance;
//            // Move monsterEntity towards the player
//            float speed = 1; // Adjust speed as needed
//            float canbinposX = monsterEntity.getPosX() + nx * speed;
//            float canbinposY = monsterEntity.getPosY() + ny * speed;
//
//            game.getEntityManager().getAIControlManager().getDirections().setPosition(monsterEntity,canbinposX ,canbinposY );
//        }
//    }
}
