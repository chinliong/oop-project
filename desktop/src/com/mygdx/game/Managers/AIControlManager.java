package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.AIControl.Directions;
import com.mygdx.game.Entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIControlManager {
	
    private List<AI> AIList;
    private Directions directions;
    
   // public AIControlManager(List<AI> AIList){
     //   this.AIList = AIList;
    //}
    
    //constructor initializing vertical and horizontal movement for AI list
    public AIControlManager(){
        this.AIList = null;
        this.directions = new Directions();
    }
    
    // Returns the movements
    public Directions getDirections(){
        return this.directions;
    }
}
