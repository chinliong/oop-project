package com.mygdx.game.GameEngine.Entities;

import com.badlogic.gdx.math.Rectangle;

public class CollidableEntity extends Entity implements iCollision {

	public CollidableEntity(String entityImagePath, int posX, int posY) {
		// Entity constructor 1
		super(entityImagePath,posX,posY);
	}
	
	public CollidableEntity(String entityImagePath)
	{
		// Entity constructor 2
		super(entityImagePath);
	}
  
  @Override
  public boolean hasCollided(CollidableEntity cEntity, int range)
  {
		return Math.abs(this.getPosX() - cEntity.getPosX()) < range && Math.abs(this.getPosY()- cEntity.getPosY()) < range;
  }
  
  public Rectangle getBounds() {
      // Rectangle to represent the collision space
      return new Rectangle(posX, posY, width, height);
  }
  
  	@Override
	public boolean hasCollidedRect(CollidableEntity cEntity) {
		Rectangle thisBound = this.getBounds();
		Rectangle otherBound = cEntity.getBounds();
		return thisBound.overlaps(otherBound);
	}
}
