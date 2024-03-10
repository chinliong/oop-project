package com.mygdx.game.Entities;

public class CollidableEntity extends Entity implements iCollision {

	public CollidableEntity(String entityImagePath, int posX, int posY) {
		super(entityImagePath,posX,posY);
		// TODO Auto-generated constructor stub
	}
  
  @Override
  public boolean hasCollided(CollidableEntity cEntity, int range)
  {
		return Math.abs(this.getPosX() - cEntity.getPosX()) < range && Math.abs(this.getPosY()- cEntity.getPosY()) < range;
  }
}
