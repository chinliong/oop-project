package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;

import com.mygdx.game.AIControl.Move;
import com.mygdx.game.AIControl.MoveHorizontal;
import com.mygdx.game.AIControl.MoveVertical;
import com.mygdx.game.Entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIControlManager {
	
    private List<AI> AIList;
    private AI entityAI;
    private MoveVertical moveVertical;
    private MoveHorizontal moveHorizontal;
    
    public AIControlManager(List<AI> AIList){
        this.AIList = AIList;
    }
    
    // Constructor for managing a single AI entity with vertical and horizontal movement capabilities
    public AIControlManager(AI entityAI){
        this.moveVertical = new MoveVertical();
        this.moveHorizontal = new MoveHorizontal();
    }
    
    // Default constructor initializing vertical and horizontal movement strategies without specific AI entities
    public AIControlManager(){
        this.AIList = null;
        this.moveVertical = new MoveVertical();
        this.moveHorizontal = new MoveHorizontal();
    }
    
    // Returns the vertical movement strategy
    public MoveVertical getMoveVertical(){
        return this.moveVertical;
    }
    
    // Returns the horizontal movement strategy
    public MoveHorizontal getMoveHorizontal(){
        return this.moveHorizontal;
    }
}
