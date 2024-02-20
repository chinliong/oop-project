package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.Entities.AI;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameMaster;

public class PlayScreen extends BaseScreen {
    //* SMALL BUG - at level 4, expected behavior is to have a blank screen
    // however i have a player sprite and an AI sprite on the screen
    // highly becuase in my checkwin() function, i have a condition that checks if the player is out of the window bounds
    // getEntity would create a new player entity if the player entity is not found
    // therefore, the player entity is always present in the entity list
    // which would then allow the loops happening in the render function to start looping adn then get the AI entity
    // which then creates the AI entity on the screen as well
    // i can just cap the level at 3 when it hits max but yea

    public PlayScreen(GameMaster game) {
        super(game);
        initUI();

        game.getSimulationLifeCycleManager().setupLevel();

        System.out.println("Number of entities: " + game.getEntityManager().getEntities().size());

        //print out my entitiy in the list
        for (int i = 0; i < game.getEntityManager().getEntities().size(); i++) {
            System.out.println(game.getEntityManager().getEntities().get(i));
        }
    }


    public PlayScreen(GameMaster game,int level) {
        super(game);
        initUI();
       // this.level = level;
        game.getSimulationLifeCycleManager().setupLevel();

        //print out my entitiy in the list
        for (int i = 0; i < game.getEntityManager().getEntities().size(); i++) {
            System.out.println(game.getEntityManager().getEntities().get(i));
        }
    }

    @Override
    public void initUI() {
        addText("Play Screen");
    }

    @Override
    public void show() {
        super.show();
        game.getSoundManager().setVolume(1.0f);
        game.getSoundManager().getMusic().play(); //play the music
        game.getCollisionManager().setProximityRange(20);
    }
    @Override
    public void render(float delta) {
        //clear color to blue
        Gdx.gl.glClearColor(0, 0, 1, 1); //this clear the screen to blue
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        super.stage.act(delta);
        super.stage.draw();
        
        game.batch.begin();

        if(game.getInputOutputManager().getInputMouse().mousePressed()){ //while the batch is running, the IOManager will listen for left and right mouse button being clicked
            //if LMB was clicked, it will notify on the terminal. The ifLMBPressed() will return true if the left mouse button (LMB) is clicked, therefore it will enter the if statement if LMB is pressed
            //The setInputReceived() function is a setter that sets the value of inputReceived to whatever value is parsed in, in this case true
            //The onPressLMB() function will return a boolean true if both the Buttons parsed is correct (Buttons.LEFT) and if the boolean parsed as the second argument is correct
            //Currently the onPress will send a message in the terminal to show it works
            if(game.getInputOutputManager().getInputMouse().ifLMBPressed()) {
                game.getInputOutputManager().getInputMouse().setInputReceived(true);
                game.getInputOutputManager().getOutput().onPressLMB(Buttons.LEFT, game.getInputOutputManager().getInputMouse().getInputReceived());
            }

            //if LMB was clicked, it will notify on the terminal. The ifRMBPressed() will return true if the right mouse button (RMB) is clicked, therefore it will enter the if statement if RMB is pressed
            //The setInputReceived() function is a setter that sets the value of inputReceived to whatever value is parsed in, in this case true
            //The onPressRMB() function will return a boolean true if both the Buttons parsed is correct (Buttons.RIGHT) and if the boolean parsed as the second argument is true
            //Currently the onPress will send a message in the terminal to show it works
            if(game.getInputOutputManager().getInputMouse().ifRMBPressed()) {
                game.getInputOutputManager().getInputMouse().setInputReceived(true);
                game.getInputOutputManager().getOutput().onPressRMB(Buttons.RIGHT, game.getInputOutputManager().getInputMouse().getInputReceived());
            }
        }
        //loop through the entities and draw them
        for (int i = 0; i < game.getEntityManager().getEntities().size(); i++) {
            //Not working movementRandom
            //game.getAIControlManager().movementRandom((AI)game.getEntityManager().getEntity(AI.class), 3, endLocationX,endLocationY);
            //game.getAIControlManager().getMovementVertical().movementFall((AI)game.getEntityManager().getEntity(AI.class), 3, 600);
            //game.getAIControlManager().getMovementVertical().movementFly((AI)game.getEntityManager().getEntity(AI.class), 1, 0);
            game.getAIControlManager().getMovementHorizontal().movementLeft((AI)game.getEntityManager().getEntity(AI.class), 1, 800);
            //game.getAIControlManager().getMovementHorizontal().movementRight((AI)game.getEntityManager().getEntity(AI.class), 1, -20);
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

        pauseScreen();

        //checkForCollision();
        game.getCollisionManager().checkandHandleCollisions(game, game.getEntityManager().getEntities());
        //checkwin();

            game.batch.end();
        }


        public void pauseScreen() {
            if (game.getInputOutputManager().getInputKeyboard().ifEscPressed()) { //If the IOManager detects the escape button pressed
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(PauseScreen.class));
                game.getSoundManager().getMusic().stop();
            }
        }

        public void checkForCollision(){ //essentially checklose
           //for each AI entity in the list of entities, if its within 20 pixels of the player, the player loses and goes to the lose screen
            int distance = 20;
            for (int i = 0; i < game.getEntityManager().getEntities().size(); i++) { //loop the entity list
                if (game.getEntityManager().getEntities().get(i) instanceof AI) { // if its AI, check for collision
                    int id = game.getEntityManager().getEntities().get(i).getID();
                    //abs value of the difference between the player and the AI should be less than 20 to be considered a collision
                    if (Math.abs(game.getEntityManager().getEntity(Player.class).getPosX() - game.getEntityManager().getEntity(id).getPosX()) < distance && Math.abs(game.getEntityManager().getEntity(Player.class).getPosY() - game.getEntityManager().getEntity(id).getPosY()) < distance) {
                        game.getSimulationLifeCycleManager().transitionToScreen(WinLoseScreen.class, false);
                    }
                }

            }
        }

        public void checkwin(){
            //if the player maange to get out of the window then he wins, goes to main screen
            if (game.getEntityManager().getEntity(Player.class).getPosX() > Gdx.graphics.getWidth() || game.getEntityManager().getEntity(Player.class).getPosY() > Gdx.graphics.getHeight()) {
                game.getSimulationLifeCycleManager().transitionToScreen(WinLoseScreen.class, true);
            }
        }


        @Override
        public void hide() {
            game.getSceneManager().removeScreen(PlayScreen.class);
        }

    }

