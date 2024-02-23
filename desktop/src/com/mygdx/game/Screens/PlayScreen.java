package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Entities.AI;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Player;
//import com.mygdx.game.GameMaster;
import com.mygdx.game.SimulationLifeCycleManager;


public class PlayScreen extends BaseScreen {

    public PlayScreen(SimulationLifeCycleManager game) {
        super(game);
        setBgColour(Color.SKY);
        initialiseUI();
    }

    @Override
    public void initialiseUI() {
    	createText("This is the PlayScreen screen");
    }

    @Override
    public void show() {
        super.show();
        startAudio("Gameplay", 1.0f);
        
        Player pEntity = new Player();
        AI aEntity = new AI();
        game.getCollisionManager().setCollisionRange(24);
        
        //Check for existing entity before adding
        if (game.getEntityManager().checkClass(Player.class) == null) {
        game.getEntityManager().addEntity(pEntity);
        }
        if (game.getEntityManager().checkClass(AI.class) == null) {
        game.getEntityManager().addEntity(aEntity);
        }
    }

    @Override
    public void render(float delta) {
        super.stage.act(delta);
        super.stage.draw();
        
        game.getBatch().begin();
        handleInput();
        drawEntities();
        moveEntities();
        game.getBatch().end();
        
        checkGameConditions();
    }


    private void handleInput() {
    	if(game.getInputOutputManager().getInputMouse().mousePressed()){  // check if mouse pressed
    		
            if(game.getInputOutputManager().getInputMouse().ifLMBPressed()) {
                game.getInputOutputManager().getInputMouse().setInputReceived(true);
                game.getInputOutputManager().getOutput().onPressLMB(Buttons.LEFT, game.getInputOutputManager().getInputMouse().getInputReceived());
            }

            if(game.getInputOutputManager().getInputMouse().ifRMBPressed()) {
                game.getInputOutputManager().getInputMouse().setInputReceived(true);
                game.getInputOutputManager().getOutput().onPressRMB(Buttons.RIGHT, game.getInputOutputManager().getInputMouse().getInputReceived());
            }
            
        }
    }

    private void drawEntities()
    {
    	for(int i = 0 ; i < game.getEntityManager().getEntities().size(); i++)
    	{
    		for(Entity entity: game.getEntityManager().getEntities())
    		{
    			entity.draw(game.getBatch());
    		}
    	}
    }
    
    private void moveEntities() {
    	//loop through all entities
        for (int i = 0; i < game.getEntityManager().getEntities().size(); i++) {
        	
        	//Move AI entities to the left up to distance of 800 and speed of 1
            game.getAIControlManager().getDirections().moveLeft((AI)game.getEntityManager().checkClass(AI.class), 1, 800);

            if (game.getEntityManager().getEntities().get(i) instanceof Player && game.getInputOutputManager().getInputKeyboard().keyPressed()==true) { 
                if (game.getInputOutputManager().getInputKeyboard().ifRightPressed()==true) { 
                    game.getPlayerControlManager().walk((Player) game.getEntityManager().getEntities().get(i), Keys.RIGHT);
                } else if (game.getInputOutputManager().getInputKeyboard().ifLeftPressed()==true) { 
                    game.getPlayerControlManager().walk((Player) game.getEntityManager().getEntities().get(i), Keys.LEFT);                    
                } 
                else if (game.getInputOutputManager().getInputKeyboard().ifUpPressed()==true) { 
                    game.getPlayerControlManager().jump((Player) game.getEntityManager().getEntities().get(i), true);
                }
                else if (game.getInputOutputManager().getInputKeyboard().ifDownPressed()==true) { 
                    game.getPlayerControlManager().jump((Player) game.getEntityManager().getEntities().get(i), false);
                }
            }
        }
    }

    private void checkGameConditions() {
        pauseScreenIfRequested();
        game.getCollisionManager().checkForCollision(game);
        checkWinCondition();
    }
    

    private void pauseScreenIfRequested() {
        if (game.getInputOutputManager().getInputKeyboard().ifEscPressed()) {
            game.getSceneManager().setScreen(game.getSceneManager().getScreen(PauseScreen.class));
            game.getAudioManager().getMusic("Gameplay").stop();
        }
    }
    
    private void checkWinCondition() {
        Player playerEntity = null;
        for (Entity entity : game.getEntityManager().getEntities()) {
            if (entity instanceof Player) {
                playerEntity = (Player) entity;
                break; 
            }
        }

        if (playerEntity == null) return; // No player found, so exit the method
        // if playerEntity exits the screen = win screen
    	if (playerEntity.getPosX() > Gdx.graphics.getWidth() || playerEntity.getPosY() > Gdx.graphics.getHeight()) {
            game.getSceneManager().transitionToScreen(WinLoseScreen.class, true);
        }
    }

    @Override
    public void hide() {
        game.getSceneManager().removeScreen(PlayScreen.class);
    }
}
