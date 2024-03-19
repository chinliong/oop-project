package com.mygdx.game.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.AI;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Entities.PlayerGame;
import com.mygdx.game.Camera;
//import com.mygdx.game.GameMaster;
import com.mygdx.game.SimulationLifeCycleManager;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class PlayScreen extends BaseScreen {
	private AI draggedEntity = null;
	//camera stuff
	private Vector3 position = new Vector3();
	private Camera camera1;
	private PlayerGame pEntity;
	//Player Stats
	private Label scoreLabel;
	private Label healthLabel;
	
	private int nextTrashIndex = 0; // Index of the next trash entity to generate
	private final float generationInterval = 3; // Interval between generations, in seconds
	private float timeSinceLastGeneration = generationInterval; // Timer to track time since last generation
	
	private ArrayList<int[]> generatedCoordinates = new ArrayList<>();
	private String[] thrashImages = {"plastic.png", "can.png", "glass.png", "paper.png"};
	private String[] thrashTypes = { "plastic", "metal", "glass", "paper" };

	
    public PlayScreen(SimulationLifeCycleManager game) {
        super(game);
        setBgColour(Color.SKY);
        initialiseUI();
        
        camera1 = new Camera(); 
    }

    @Override
    public void initialiseUI() {
    	createText("This is the PlayScreen screen");
    	scoreLabel = createText("Player Score Counter: ", 50,100);
    	healthLabel = createText("Player Health: ", 50,80);
    }

    @Override
    public void show() {
        super.show();
        startAudio("Gameplay", 1.0f);
        
        AI aEntity = new AI();
        pEntity = new PlayerGame();
       
        generatedCoordinates = generateCoordinates();
        AI monsterEntity = new AI("thrashbin.png",200, 10); // Monster entity that follows player
        AI glassbinEntity = new AI("glassbin.png",300, 10); // trashbin2
        AI plasticbinEntity = new AI("plasticbin.png",400, 10); // trashbin2
        AI paperbinEntity = new AI("paperbin.png",500, 10); // trashbin2
        AI canbinEntity = new AI("canbin.png",600, 10); // trashbin2
        AI binEntity = new AI("thrashbin.png",200,10);

        
        //Generate coordinates for thrash entities
      //  ArrayList<int[]> generatedCoordinates = generateCoordinates();
        // Array of thrash entity images, assuming you have different images for each
       // String[] thrashImages = {"plastic.png", "can.png", "glass.png", "paper.png"};
        
        // Create thrash entities with generated coordinates and add them to the entity manager
        //if (game.getEntityManager().checkClass(AI.class) == null) {
       // for (int i = 0; i < generatedCoordinates.size(); i++) {
        //    int[] coord = generatedCoordinates.get(i);
      //      AI thrashEntity = new AI(thrashImages[i], coord[0], coord[1]);
      //      game.getEntityManager().addEntity(thrashEntity);
       // }
      //  }
        
        //Check for existing entity before adding
        if (game.getEntityManager().checkClass(Player.class) == null) {
        game.getEntityManager().addEntity(pEntity);
        }
        if (game.getEntityManager().checkClass(AI.class) == null) {
        game.getEntityManager().addEntity(aEntity);
        }
        
        monsterEntity.setType("waste");
        glassbinEntity.setType("glass");
        plasticbinEntity.setType("plastic");
        paperbinEntity.setType("paper");
        canbinEntity.setType("can");
        
        //Add bin entities
        game.getEntityManager().addEntity(monsterEntity);
        game.getEntityManager().addEntity(glassbinEntity);
        game.getEntityManager().addEntity(plasticbinEntity);
        game.getEntityManager().addEntity(paperbinEntity);
        game.getEntityManager().addEntity(canbinEntity);
        
        game.getEntityManager().addEntity(binEntity);
               
        //Set collision range
        game.getEntityManager().getCollisionManager().setCollisionRange(24);
    }

    @Override
    public void render(float delta) {
        super.stage.act(delta);
        super.stage.draw();
        
        
        game.getBatch().begin();
        handleInput();
        drawEntities();
        moveEntities();
        
        //To drag entity with mouse cursor
        if (draggedEntity != null) {
            // Update the entity's position to follow the mouse cursor
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y-axis
            //Calculate center position
            float centerX = mouseX - draggedEntity.getWidth() / 2;
            float centerY = mouseY - draggedEntity.getHeight() / 2;
            
            game.getEntityManager().getAIControlManager().getDirections().setPosition(draggedEntity, centerX, centerY);
        }
        game.getBatch().end();
        
        
      //To generate trash entities randomly at intervals
        timeSinceLastGeneration += delta;
        if (timeSinceLastGeneration >= generationInterval && nextTrashIndex < generatedCoordinates.size()) {
            timeSinceLastGeneration = 0; // Reset the timer

            int[] coord = generatedCoordinates.get(nextTrashIndex);
            int index = nextTrashIndex % thrashImages.length; // Calculate index for type
            String image = thrashImages[index]; // Select image based on index
            String type = thrashTypes[index]; // Select type based on same index

            AI thrashEntity = new AI(image, coord[0], coord[1]);
            thrashEntity.setType(type); // Set the type for the trash entity

            game.getEntityManager().addEntity(thrashEntity);

            nextTrashIndex++; // Prepare for the next entity
        }
        
        
        updatePlayerScore();
        checkGameConditions();
//         Update the camera to follow the player
//        if (pEntity != null) {
//            camera1.camera.position.set(pEntity.getPosX(), pEntity.getPosY(), 0);
//            camera1.camera.update();
//        }

        // Set the batch's projection matrix
//        game.getBatch().setProjectionMatrix(camera1.camera.combined);
    }

    private void updatePlayerScore()
    {
    	int scoreCounter = pEntity.getScoreCounter();
    	int healthCounter = pEntity.getPlayerHealth();
    	scoreLabel.setText("Player Score: " + scoreCounter);
    	healthLabel.setText("Player Health: " + healthCounter);
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
    	
    	
    	
    	// if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
    	     //   if (draggedEntity == null) {
    	            // Get mouse position
    	      //      float mouseX = Gdx.input.getX();
    	      //      float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y-axis

    	            // Check if any thrash entity is clicked
    	        //    for (AI entity : game.getEntityManager().getEntitiesByClass(AI.class)) {
    	         //       if (mouseX >= entity.getPosX() && mouseX <= entity.getPosX() + entity.getWidth()
    	         //           && mouseY >= entity.getPosY() && mouseY <= entity.getPosY() + entity.getHeight()) {
    	         //           draggedEntity = entity; // Mark this entity as being dragged
    	         //           break;
    	         //       }
    	         //   }
    	      //  }
    	  //  } else {
    	   //     draggedEntity = null; // Release the entity when the mouse button is released
    	   // }
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
        	
        	/* PREVIOUS CODE TO MOVE WHALE LEFT
        	//Move AI entities to the left up to distance of 800 and speed of 1
            game.getEntityManager().getAIControlManager().getDirections().moveLeft((AI)game.getEntityManager().checkClass(AI.class), 1, 800);
            */
        	
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
        
        //Make monster entity follow player
        if (pEntity != null) {
            AI monsterEntity = null;
            // Find the monsterEntity in the list of entities
            for (Entity entity : game.getEntityManager().getEntities()) {
                if (entity instanceof AI) {
                    AI ai = (AI) entity;
                    
                    // Check if this AI entity is the monsterEntity
                    if (ai.getAIObjectName().equals("thrashbin.png")) {
                    	monsterEntity = ai;
                        break;
                    }
                }
            }

            // If monsterEntity is found, move it towards pEntity
            if (monsterEntity != null) {
                // Calculate direction vector from monsterEntity to pEntity
                float dx = pEntity.getPosX() - monsterEntity.getPosX();
                float dy = pEntity.getPosY() - monsterEntity.getPosY();
                float distance = (float) Math.sqrt(dx * dx + dy * dy);
                if (distance > 0) { // To avoid division by zero
                    // Normalize direction vector
                    float nx = dx / distance;
                    float ny = dy / distance;
                    // Move monsterEntity towards the player
                    float speed = 1; // Adjust speed as needed
                    float canbinposX = monsterEntity.getPosX() + nx * speed;
                    float canbinposY = monsterEntity.getPosY() + ny * speed;

                    game.getEntityManager().getAIControlManager().getDirections().setPosition(monsterEntity,canbinposX ,canbinposY );
                }
            }
        }
        
        if (pEntity != null) {
            pEntity.updateAttachedEntities();
        }
        

 
    }

    private void checkGameConditions() {
        pauseScreenIfRequested();
        game.getEntityManager().getCollisionManager().checkForCollision(game);
        checkWinCondition();
    }
    




    private void pauseScreenIfRequested() {
        if (game.getInputOutputManager().getInputKeyboard().ifEscPressed()) {
            game.getSceneManager().setScreen(game.getSceneManager().getScreen(PauseScreen.class));
            game.getAudioManager().getMusic("Gameplay").stop();
        }
    }
    
    private void checkWinCondition() {
    	if (pEntity.getScoreCounter() == 4)
    	{
            game.getSceneManager().transitionToScreen(WinLoseScreen.class, true);
    	}
    	if (pEntity.getPlayerHealth() == 0)
    	{
            game.getSceneManager().transitionToScreen(WinLoseScreen.class, false);

    	}
    }

    @Override
    public void hide() {
        game.getSceneManager().removeScreen(PlayScreen.class);
    }
    
   //Function to generate random coordinates higher than 200units, and at least 50units apart from each other
    private ArrayList<int[]> generateCoordinates() {
    	
        ArrayList<int[]> coordinates = new ArrayList<>();
        
        while (coordinates.size() < 4) { // Generate until 4 unique coordinates are found
            // Generate coordinates within screen bounds, ensuring y is at least 200
            int[] newCoordinate = {
                MathUtils.random(700), //screen width
                200 + MathUtils.random(500 - 200) //min height + random(screenheight - minheight)
            };
            
            boolean isValid = true;
            // Check new coordinate against all existing coordinates for minimum distance
            for (int[] coord : coordinates) {
                if (Math.sqrt(Math.pow(newCoordinate[0] - coord[0], 2) + Math.pow(newCoordinate[1] - coord[1], 2)) < 50) {
                    isValid = false; // New coordinate is too close to an existing one
                    break;
                }
            }
            
            if (isValid) {
                coordinates.add(newCoordinate); // Add valid coordinate to the list
            }
        }
        
        return coordinates;
    }
    }
