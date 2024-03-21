package com.mygdx.game.GameLayer.Entities;

import com.mygdx.game.GameEngine.Entities.AI;

public class Recyclables extends AI implements iType{
	private boolean isThrown = false;
	private float thrownTimer = 0;
	protected RecyclableType type;
	
	public Recyclables(String entityImagePath, int posX, int posY, RecyclableType type)
	{
		super(entityImagePath,posX,posY);
		this.type = type;		
	}	
	
	@Override
	public RecyclableType getType()
	{
		return this.type;
	}
	
	//Check if recyclable is thrown 
	public void setThrown(boolean thrown) {
	        isThrown = thrown;
	    }

	public boolean isThrown() {
	        return isThrown;
	    }
	
}

