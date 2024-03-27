package com.mygdx.game.GameEngine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameEngine.Managers.*;

public class SimulationLifeCycleManager extends Game {

	// Attribute
    protected SpriteBatch batch;
    private BitmapFont font;
    private SceneManager sceneManager; 
    private EntityManager entityManager; 
    private IOManager ioManager;
    private AudioManager audioManager;
    private PlayerManager playerManager;
    private LevelManager levelManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); 
        // Initialize all managers
        ioManager = new IOManager();
        playerManager = new PlayerManager(this);
        entityManager = new EntityManager(this); 
        levelManager = new LevelManager(this);
        sceneManager = new SceneManager(this); 
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

    public AudioManager getAudioManager(){
        return audioManager;
    }
    
    public PlayerManager getPlayerManager(){
        return playerManager;
    }
    
    public LevelManager getLevelManager() {
        return levelManager;
    }

}
