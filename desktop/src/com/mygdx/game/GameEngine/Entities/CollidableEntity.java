package com.mygdx.game.GameEngine.Entities;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

@Override
	public boolean hasCollidedRect(CollidableEntity cEntity) {
		Rectangle thisBound = this.getBounds();
		Rectangle otherBound = cEntity.getBounds();
		return thisBound.overlaps(otherBound);
	}
}
