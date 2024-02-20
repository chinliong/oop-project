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

    private int currentLevel;

    //screen with boolean to check true or false
    private boolean showMenuScreen;
    private boolean showPlayScreen;
    private boolean showEndScreen;
    


    public SimulationLifeCycleManager(GameMaster game){
        this.game = game;
        this.currentLevel = 1;
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

    // the dispose method i can thinkas of now,will recode 
    // public void dispose() {
    //     // Dispose of the SceneManager resources
    //     if (sceneManager != null) {
    //         sceneManager.dispose();
    //     }

    //     // Dispose of the EntityManager resources
    //     if (entityManager != null) {
    //         entityManager.dispose();
    //     }

    //     // Dispose of the PlayerControlManager resources
    //     if (playerControlManager != null) {
    //         playerControlManager.dispose();
    //     }

    //     // Dispose of the CollisionManager resources
    //     if (collisionManager != null) {
    //         collisionManager.dispose();
    //     }

    // }
    
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


    public void setupLevel(){
        //instantiating objects based on the level
        if (this.currentLevel > 3){
            this.currentLevel = 3;
        }
        System.out.println("Setting up level " + this.currentLevel);
        resetEntities(this.game.getEntityManager().getEntities()); //a mechanism to reset the entities
        switch (this.currentLevel) {

            case 1:
                //create the entities
                this.game.getEntityManager().createEntity(Player.class);
                this.game.getEntityManager().createEntity(AI.class, "fish", 100);
                this.game.getEntityManager().createEntity(AI.class, "whale", 100);
                this.game.getEntityManager().createEntity(Item.class);
                break;
            case 2:
                //create the entities
                this.game.getEntityManager().createEntity(Player.class);
                this.game.getEntityManager().createEntity(AI.class, "guard", 100);
                this.game.getEntityManager().createEntity(AI.class, "wizard2", 100);
                this.game.getEntityManager().createEntity(AI.class, "fireguard", 100);
                this.game.getEntityManager().createEntity(Item.class);
                break;
            case 3:
                //create the entities
                this.game.getEntityManager().createEntity(Player.class);
                this.game.getEntityManager().createEntity(AI.class, "guard", 100);
                this.game.getEntityManager().createEntity(AI.class, "wizard2", 100);
                this.game.getEntityManager().createEntity(AI.class, "wizard2", 100);
                this.game.getEntityManager().createEntity(AI.class, "fireguard", 100);
                this.game.getEntityManager().createEntity(AI.class, "droplet", 100);
                this.game.getEntityManager().createEntity(Item.class); //default item
                this.game.getEntityManager().createEntity(Item.class,"bow");  //bow item
                break;
        }
    }

    public void incrementLevel(){
        this.currentLevel++;
//        if (this.currentLevel > 3){
//            this.currentLevel = 3;
//        }
    }

    public int getLevel(){
        return this.currentLevel;
    }



    //Screen transition logic

    public void transitionToScreen(Class<? extends BaseScreen> screenClass, Object... args) {
        //if its the winlosescreen, i will definitely have additional arguments to see if the player won or lost
        if (screenClass.equals(WinLoseScreen.class)) {
            if (args.length > 0) {
                boolean win = (boolean) args[0];
                this.game.getSceneManager().setScreen(this.game.getSceneManager().createScreen(screenClass, win));
                resetEntities(this.game.getEntityManager().getEntities());
                game.getSoundManager().getMusic().stop();
            }
            else {
                //something is wrong so i will auto pass lost
                this.game.getSceneManager().setScreen(this.game.getSceneManager().createScreen(screenClass, false));
                resetEntities(this.game.getEntityManager().getEntities());
                game.getSoundManager().getMusic().stop();
            }
        }
    }

    

}
