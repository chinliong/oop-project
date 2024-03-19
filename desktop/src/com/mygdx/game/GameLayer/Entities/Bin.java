package com.mygdx.game.GameLayer.Entities;

import com.mygdx.game.GameEngine.Entities.AI;

public class Bin extends AI implements iType{
	
	protected RecyclableType type;
	
	public Bin(String entityImagePath, int posX, int posY, RecyclableType type)
	{
		super(entityImagePath,posX,posY);
		this.type = type;		
	}
	
	@Override
	public RecyclableType getType()
	{
		return this.type;
	}
}
