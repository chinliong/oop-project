package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.Entities.AI;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameMaster;


public class PlayScreen extends BaseScreen {

    public PlayScreen(GameMaster game) {
        super(game);
        initialiseUI();
    }

    @Override
    public void initialiseUI() {
    	createText("Play Screen");
    }

    @Override
    public void show() {
        super.show();
        game.getAudioManager().getMusic().setVolume(1.0f);
        game.getAudioManager().getMusic().play();
        game.getAudioManager().getMusic().isLooping();
        game.getCollisionManager().setProximityRange(20);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        super.stage.act(delta);
        super.stage.draw();
        
        game.batch.begin();
        handleInput();
        drawEntities();
        game.batch.end();
        
        checkGameConditions();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
    }

    private void handleInput() {
    	if(game.getInputOutputManager().getInputMouse().mousePressed()){ 
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
            game.getAIControlManager().getMoveHorizontal().moveLeft((AI)game.getEntityManager().getEntity(AI.class), 1, 800);
            game.getEntityManager().getEntities().get(i).draw(game.batch);

            if (game.getEntityManager().getEntities().get(i) instanceof Player && game.getInputOutputManager().getInputKeyboard().keyPressed()==true) { //if ... and a keyboard input is detected using keyPressed() from InputKeyboard
                if (game.getInputOutputManager().getInputKeyboard().ifRightPressed()==true) { //if the right arrow key button was pressed using a keyboard, ifRightPressed() from InputKeyboard will return true
                    game.getPlayerControlManager().walk((Player) game.getEntityManager().getEntities().get(i), Keys.RIGHT);
                } else if (game.getInputOutputManager().getInputKeyboard().ifLeftPressed()==true) { //else if the left arrow key button was pressed using a keyboard, ifLeftPressed() from InputKeyboard will return true
                    game.getPlayerControlManager().walk((Player) game.getEntityManager().getEntities().get(i), Keys.LEFT);                    
                } 
                else if (game.getInputOutputManager().getInputKeyboard().ifWPressed()==true) { //else if the W key button was pressed using a keyboard, ifWPressed() from InputKeyboard will return true
                    game.getPlayerControlManager().jump((Player) game.getEntityManager().getEntities().get(i), true);
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
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            game.getSceneManager().setScreen(game.getSceneManager().getScreen(PauseScreen.class));
            game.getAudioManager().getMusic().stop();
        }
    }

    private void checkForCollision() {
    	//for each AI entity in the list of entities, if its within 20 pixels of the player, the player loses and goes to the lose screen
        int distance = 45;
        for (int i = 0; i < game.getEntityManager().getEntities().size(); i++) { //loop the entity list
            if (game.getEntityManager().getEntities().get(i) instanceof AI) { // if its AI, check for collision
                int id = game.getEntityManager().getEntities().get(i).getID();
                //abs value of the difference between the player and the AI should be less than 20 to be considered a collision
                if (Math.abs(game.getEntityManager().getEntity(Player.class).getPosX() - game.getEntityManager().getEntity(id).getPosX()) < distance && Math.abs(game.getEntityManager().getEntity(Player.class).getPosY() - game.getEntityManager().getEntity(id).getPosY()) < distance) {
                    game.getAudioManager().playSound();
                	game.getSimulationLifeCycleManager().transitionToScreen(WinLoseScreen.class, false);
                }
            }

        }
    }

    private void checkWinCondition() {
    	if (game.getEntityManager().getEntity(Player.class).getPosX() > Gdx.graphics.getWidth() || game.getEntityManager().getEntity(Player.class).getPosY() > Gdx.graphics.getHeight()) {
            game.getSimulationLifeCycleManager().transitionToScreen(WinLoseScreen.class, true);
        }
    }

    @Override
    public void hide() {
        game.getSceneManager().removeScreen(PlayScreen.class);
    }
}
