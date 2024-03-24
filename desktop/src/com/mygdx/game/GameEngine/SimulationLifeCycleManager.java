package com.mygdx.game.GameEngine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameEngine.Managers.*;

public class SimulationLifeCycleManager extends Game {

	// Attributes 
    protected SpriteBatch batch;
    private BitmapFont font;
    private SceneManager sceneManager; 
    private EntityManager entityManager; 
    private IOManager ioManager;
//    private PlayerControlManager playerControlManager;
    private AudioManager audioManager;
    private SimulationLifeCycleManager simulationLifeCycleManager;
    private PlayerManager playerManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); 
        playerManager = new PlayerManager(this);

        // Initialize all managers
        entityManager = new EntityManager(this); 
        sceneManager = new SceneManager(this); 
        ioManager = new IOManager();
//        playerControlManager = new PlayerControlManager(this); 
        audioManager = new AudioManager();
        sceneManager.setScreen();
        System.out.println("Managers intialized");
    }
    @Override
    public void render() {
    	
        ScreenUtils.clear(0, 0, 0.2f, 1);
        super.render();
    }
    @Override
    public void dispose() {
        System.out.println("GameMaster disposing resources");
        font.dispose();
        batch.dispose();
        if(entityManager != null)
        {
        	entityManager.disposeEntities();
        }
        if(audioManager != null)
        {
        	audioManager.dispose();
        }
        if(sceneManager != null)
        {
        	sceneManager.dispose();
        }
        
    }

    public SpriteBatch getBatch()
    {
    	return batch;
    }
    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public BitmapFont getFont() {
        return font;
    }

    public IOManager getInputOutputManager(){
        return ioManager;
    }

//    public PlayerControlManager getPlayerControlManager(){
//        return playerControlManager;
//    }

    public AudioManager getAudioManager(){
        return audioManager;
    }

    public SimulationLifeCycleManager getSimulationLifeCycleManager(){
        return simulationLifeCycleManager;
    }    
    
    public PlayerManager getPlayerManager(){
        return playerManager;
    }

}
