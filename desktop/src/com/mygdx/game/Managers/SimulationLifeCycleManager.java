package com.mygdx.game.Managers;

import com.mygdx.game.Entities.*;
import com.mygdx.game.GameMaster;
import com.mygdx.game.Screens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import java.util.List;


public class SimulationLifeCycleManager {
    private GameMaster game; // Reference to the main game controller

    // Flags to manage visibility of different game screens
    private boolean showMenuScreen;
    private boolean showPlayScreen;
    private boolean showEndScreen;
    

    // Constructor to initialize the lifecycle manager with a reference to the GameMaster
    public SimulationLifeCycleManager(GameMaster game){
        this.game = game;
        // Initialize all screen visibility flags to false
        this.showMenuScreen = false;
        this.showPlayScreen = false;
        this.showEndScreen = false;
    }



    // Getter and Setter for menu screen visibility
    public boolean isShowMenuScreen() {
        return showMenuScreen;
    }

    public void setShowMenuScreen(boolean showMenuScreen) {
        this.showMenuScreen = showMenuScreen;
    }

    // Getter and Setter for play screen visibility
     public boolean isShowPlayScreen() {
        return showPlayScreen;
    }

    public void setShowPlayScreen(boolean showPlayScreen) {
        this.showPlayScreen = showPlayScreen;
    }

    // Getter and Setter for end screen visibility
    public boolean isShowEndScreen() {
        return showEndScreen;
    }

    public void setShowEndScreen(boolean showEndScreen) {
        this.showEndScreen = showEndScreen;
    }
    
    // Dispose all entities from a list
    public void disposeEntities(List<Entity> entities){
        for(Entity entity: entities){
            entity.dispose();
            System.out.println("Entities are disposed");
        }

        entities.clear(); //clear the entities list
        //check if the entities list is empty
        if(entities.isEmpty()){
            System.out.println("Empty");
        } else {
            System.out.println("Not Empty");
        }
    }
    
    // Dispose a single specified entity from the list
    public void disposeEntities(List<Entity> entities, Entity entityToDispose) {
        if (entities.contains(entityToDispose)) {
            entityToDispose.dispose(); // Dispose the specified entity
            entities.remove(entityToDispose); // Remove it from the list
            System.out.println("Entity is disposed");
        }
    }

    // Reset entities by clearing the list, possibly for reuse or reinitialization
    public void resetEntities(List<Entity> entities){
        entities.clear(); //clear the entities list
        }



 // Screen transition logic with the ability to pass additional arguments for specific screens
    public void transitionToScreen(Class<? extends BaseScreen> screenClass, Object... args) {
        // Special handling for transitioning to WinLoseScreen with arguments to indicate win/loss
        if (screenClass.equals(WinLoseScreen.class)) {
            if (args.length > 0) {
                boolean win = (boolean) args[0]; // Determine win or loss based on argument
                // Transition to the screen with the win/loss state
                this.game.getSceneManager().setScreen(this.game.getSceneManager().createScreen(screenClass, win));
                resetEntities(this.game.getEntityManager().getEntities()); // Reset entities for new game state
                game.getAudioManager().getMusic("Gameplay").stop(); // Stop current game music
            }
            else {
                // Default to loss if no arguments provided, indicating an error or oversight
                this.game.getSceneManager().setScreen(this.game.getSceneManager().createScreen(screenClass, false));
                resetEntities(this.game.getEntityManager().getEntities());
                game.getAudioManager().getMusic("Gameplay").stop();
            }
        }
    }

    

}
