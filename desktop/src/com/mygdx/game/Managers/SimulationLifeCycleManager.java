package com.mygdx.game.Managers;

import com.mygdx.game.Entities.*;
import com.mygdx.game.GameMaster;
import com.mygdx.game.Screens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
//import com.sun.java.swing.plaf.windows.resources.windows;

import java.util.List;


public class SimulationLifeCycleManager {
    private GameMaster game;


    //screen with boolean to check true or false
    private boolean showMenuScreen;
    private boolean showPlayScreen;
    private boolean showEndScreen;
    


    public SimulationLifeCycleManager(GameMaster game){
        this.game = game;
        this.showMenuScreen = false;
        this.showPlayScreen = false;
        this.showEndScreen = false;
    }



    // Getter and Setter for showMenuScreen
    public boolean isShowMenuScreen() {
        return showMenuScreen;
    }

    public void setShowMenuScreen(boolean showMenuScreen) {
        this.showMenuScreen = showMenuScreen;
    }

     // Getter and Setter for showPlayScreen
     public boolean isShowPlayScreen() {
        return showPlayScreen;
    }

    public void setShowPlayScreen(boolean showPlayScreen) {
        this.showPlayScreen = showPlayScreen;
    }

    // Getter and Setter for showEndScreen
    public boolean isShowEndScreen() {
        return showEndScreen;
    }

    public void setShowEndScreen(boolean showEndScreen) {
        this.showEndScreen = showEndScreen;
    }
    
    //for my understanding it loops and remove all the entities, but what if only need remove one entity?
    public void disposeEntities(List<Entity> entities){
        for(Entity entity: entities){
            entity.dispose();
            System.out.println("Entity has been disposed");
        }

        entities.clear(); //clear the entities list
        //check if the entities list is empty
        if(entities.isEmpty()){
            System.out.println("Entities list is empty");
        } else {
            System.out.println("Entities list is not empty");
        }
    }

    public void disposeEntities(List<Entity> entities, Entity entityToDispose) {
        if (entities.contains(entityToDispose)) {
            entityToDispose.dispose();
            entities.remove(entityToDispose);
            System.out.println("Entity has been disposed");
        }
    }

    // resetting entities instead of disposing them
    public void resetEntities(List<Entity> entities){
        entities.clear(); //clear the entities list
        }



    //Screen transition logic

    public void transitionToScreen(Class<? extends BaseScreen> screenClass, Object... args) {
        //if its the winlosescreen, i will definitely have additional arguments to see if the player won or lost
        if (screenClass.equals(WinLoseScreen.class)) {
            if (args.length > 0) {
                boolean win = (boolean) args[0];
                this.game.getSceneManager().setScreen(this.game.getSceneManager().createScreen(screenClass, win));
                resetEntities(this.game.getEntityManager().getEntities());
                game.getAudioManager().getMusic().stop();
            }
            else {
                //something is wrong so i will auto pass lost
                this.game.getSceneManager().setScreen(this.game.getSceneManager().createScreen(screenClass, false));
                resetEntities(this.game.getEntityManager().getEntities());
                game.getAudioManager().getMusic().stop();
            }
        }
    }

    

}
