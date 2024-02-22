package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Managers.*;
import com.mygdx.game.Screens.MainScreen;

//so what i need to here is extends game instead of application adapter
//so i am allowed to use the screen class
//i will also define my managers here so i can use them in the screen class

public class SimulationLifeCycleManager extends Game {

	
    protected SpriteBatch batch; //* should not be public
    private BitmapFont font;
    //creates the instances of my managers
    private SceneManager sceneManager; //declaring a object that is a SceneManager type
    private EntityManager entityManager; //declaring a object that is a EntityManager type
    private IOManager ioManager;
    private AIControlManager AIControlManager;
    private PlayerControlManager playerControlManager;
    private AudioManager audioManager;
    private SimulationLifeCycleManager simulationLifeCycleManager;
    private CollisionManager collisionManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); //this is to create a new instance of the BitmapFont object
        entityManager = new EntityManager(); //actually creating an instance of the EntityManager object
        sceneManager = new SceneManager(this); //actually creating an instance of the SceneManager object
        ioManager = new IOManager();
        playerControlManager = new PlayerControlManager(); 
        audioManager = new AudioManager();
        AIControlManager = new AIControlManager();
//        simulationLifeCycleManager = new SimulationLifeCycleManager(this);
        sceneManager.setScreen();
        collisionManager = new CollisionManager();
    }
    @Override
    public void render() {
    	
        ScreenUtils.clear(0, 0, 0.2f, 1);
        super.render(); //this is to delegate the render method to the current screen by calling the render method of the current screen
    }
    @Override
    public void dispose() {
        System.out.println("GameMaster disposing resources");
        font.dispose();
        batch.dispose();
//        simulationLifeCycleManager.disposeEntities(entityManager.getEntities());
    }

    public SpriteBatch getBatch()
    {
    	return batch;
    }
    //getSceneManager method
    public SceneManager getSceneManager() {
        return sceneManager;
    }

    //getEntityManager method
    public EntityManager getEntityManager() {
        return entityManager;
    }

    //getFont method
    public BitmapFont getFont() {
        return font;
    }

    public IOManager getInputOutputManager(){
        return ioManager;
    }


    public PlayerControlManager getPlayerControlManager(){
        return playerControlManager;
    }

    public AIControlManager getAIControlManager(){
        return AIControlManager;
    }
    
    public AudioManager getAudioManager(){
        return audioManager;
    }

    public SimulationLifeCycleManager getSimulationLifeCycleManager(){
        return simulationLifeCycleManager;
    }

    public CollisionManager getCollisionManager(){
        return collisionManager;
    }
}
