package com.mygdx.game.GameEngine.Managers;

import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameEngine.AIControl.iBehaviour;
import com.mygdx.game.GameEngine.*;
import com.mygdx.game.GameLayer.Entities.*;
import java.util.ArrayList;
import java.util.List;

public class AIControlManager {
    
    private List<AI> AIList;
    private List<iBehaviour> behaviours;

    // Constructor initializing the AI list
    public AIControlManager() {
        this.AIList = new ArrayList<>();
        this.behaviours = new ArrayList<>();
    }

    // Method to add an AI entity to the list
    public void addAI(AI ai) {
        AIList.add(ai);
    }

    // Method to remove an AI entity from the list
    public void removeAI(AI ai) {
        AIList.remove(ai);
    }
    
 // Method to add behaviour 
    public void addBehavior(iBehaviour behaviour) {
        behaviours.add(behaviour);
    }

    public void executeBehaviors(PlayerGame player, SimulationLifeCycleManager game) {
        for (iBehaviour behaviour : behaviours) {
            for (AI ai : AIList) {
                behaviour.execute(player, game, ai); // Execute each behavior for each AI
            }
        }
    }
    
    public List<AI> getAIList() {
        return new ArrayList<>(AIList); // Return a copy to prevent external modification
    }

}
