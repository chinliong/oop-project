package com.mygdx.game.Managers;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.AIControl.Movement;
import com.mygdx.game.AIControl.MovementHorizontal;
import com.mygdx.game.AIControl.MovementVertical;
import com.mygdx.game.Entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AIControlManager {
    private List<AI> AIList;
    private AI entityAI;
    private MovementVertical movementVertical;
    private MovementHorizontal movementHorizontal;
    public AIControlManager(List<AI> AIList){
        this.AIList = AIList;
    }

    public AIControlManager(AI entityAI){
        this.movementVertical = new MovementVertical();
        this.movementHorizontal = new MovementHorizontal();
    }

    public AIControlManager(){
        this.AIList = null;
        this.movementVertical = new MovementVertical();
        this.movementHorizontal = new MovementHorizontal();
    }

    public MovementVertical getMovementVertical(){
        return this.movementVertical;
    }

    public MovementHorizontal getMovementHorizontal(){
        return this.movementHorizontal;
    }
}
