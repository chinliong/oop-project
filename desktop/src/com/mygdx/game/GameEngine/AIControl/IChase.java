package com.mygdx.game.GameEngine.AIControl;


import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameEngine.*;
import com.mygdx.game.GameLayer.Entities.*;

public interface IChase {
	void chase(PlayerGame player, SimulationLifeCycleManager game, AI ai);
}
