package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Entities.AI;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameMaster;


public class PlayScreen extends BaseScreen {

    public PlayScreen(GameMaster game) {
        super(game);
        setBgColour(Color.SKY);
        initialiseUI();
    }

    @Override
    public void initialiseUI() {
    	createText("Play Screen");
    }

    @Override
    public void show() {
        super.show();
        game.getAudioManager().getMusic("Gameplay").setVolume(1.0f);
        game.getAudioManager().getMusic("Gameplay").play();
        game.getAudioManager().getMusic("Gameplay").isLooping();
        game.getCollisionManager().setCollisionRange(30);
        game.getEntityManager().addEntity(new Player());
        game.getEntityManager().addEntity(new AI());
    }

    @Override
    public void render(float delta) {
        super.stage.act(delta);
        super.stage.draw();
        
        game.batch.begin();
        handleInput();
        drawEntities();
        game.batch.end();
        
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

    private void drawEntities() {
    	//loop through the entities and draw them
        for (int i = 0; i < game.getEntityManager().getEntities().size(); i++) {
            game.getAIControlManager().getMoveHorizontal().moveLeft((AI)game.getEntityManager().checkClass(AI.class), 1, 800);
          // .get will loop through ArrayList's index to draw each entity in entitylist
            game.getEntityManager().getEntities().get(i).draw(game.batch);

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
        checkForCollision();
        checkWinCondition();
    }

    private void pauseScreenIfRequested() {
        if (game.getInputOutputManager().getInputKeyboard().ifEscPressed()) {
            game.getSceneManager().setScreen(game.getSceneManager().getScreen(PauseScreen.class));
            game.getAudioManager().getMusic("Gameplay").stop();
        }
    }

    private void checkForCollision() {
        Player playerEntity = null;
        for (Entity entity : game.getEntityManager().getEntities()) {
            if (entity instanceof Player) {
                playerEntity = (Player) entity; // if is type player = typecast it
                break; 
            }
        }

        if (playerEntity == null) return; // No player found, so exit the method

        for (Entity entity : game.getEntityManager().getEntities()) {
            if (entity instanceof AI) {
                AI aiEntity = (AI) entity;
  
                int distance = 30; 
                // if player and AI  < distance = collide
                if (Math.abs(playerEntity.getPosX() - aiEntity.getPosX()) < distance &&
                    Math.abs(playerEntity.getPosY() - aiEntity.getPosY()) < distance) {
                    game.getAudioManager().playSound();
                    game.getSimulationLifeCycleManager().transitionToScreen(WinLoseScreen.class, false);
                    return;
                }
            }
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
            game.getSimulationLifeCycleManager().transitionToScreen(WinLoseScreen.class, true);
        }
    }

    @Override
    public void hide() {
        game.getSceneManager().removeScreen(PlayScreen.class);
    }
}
