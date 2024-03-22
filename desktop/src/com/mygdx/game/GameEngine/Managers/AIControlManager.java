package com.mygdx.game.GameEngine.Managers;

import com.mygdx.game.GameEngine.Entities.AI;
import java.util.ArrayList;
import java.util.List;

public class AIControlManager {
    
    private List<AI> AIList;

    // Constructor initializing the AI list
    public AIControlManager() {
        this.AIList = new ArrayList<>();
    }

    // Method to add an AI entity to the list
    public void addAI(AI ai) {
        AIList.add(ai);
    }

    // Method to remove an AI entity from the list
    public void removeAI(AI ai) {
        AIList.remove(ai);
    }

}
