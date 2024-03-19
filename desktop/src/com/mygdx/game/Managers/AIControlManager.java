package com.mygdx.game.Managers;

import com.mygdx.game.AIControl.IMoveable;
import com.mygdx.game.Entities.AI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
