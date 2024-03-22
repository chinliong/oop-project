package com.mygdx.game.GameEngine.Entities;

public interface iCollision {
	public boolean hasCollided(CollidableEntity cEntity, int range);
	public boolean hasCollidedRect(CollidableEntity cEntity);
	
}
