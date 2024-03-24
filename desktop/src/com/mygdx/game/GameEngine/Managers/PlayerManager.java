package com.mygdx.game.GameEngine.Managers;

import java.util.ArrayList;

import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.Player;

public class PlayerManager {
	private SimulationLifeCycleManager game;
	private PlayerControlManager playerControlManager;
	
	private ArrayList<Player> playerList;
	
	public PlayerManager(SimulationLifeCycleManager game)
	{
		this.game = game;
		playerList = new ArrayList<>();
		playerControlManager = new PlayerControlManager(game);
	}
	
	public void addPlayer(Player player)
	{
		playerList.add(player);
	}
	
	public void removePlayer(Player player)
	{
		for (Player entity: playerList)
		{
			if (entity instanceof Player)
			{
				player = (Player) entity;
			}
		}
		playerList.remove(player);
	}
	
	public ArrayList<Player> getPlayerList()
	{
		return playerList;
	}
	public PlayerControlManager getPlayerControlManager()
	{
		return playerControlManager;
	}
}
