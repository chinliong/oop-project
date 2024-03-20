
package com.mygdx.game.GameLayer.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

import com.mygdx.game.GameEngine.Camera;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.Player;
import com.mygdx.game.GameEngine.Screens.BaseScreen;

// Game Layer imports
import com.mygdx.game.GameLayer.Entities.*;

public class PlayScreen extends BaseScreen {
	private AI draggedEntity = null;
	//camera stuff
	private Vector3 position = new Vector3();
	private Camera camera1;
	private PlayerGame pEntity;
	private Monster monsterEntity;
	//Player Stats
	private Label scoreLabel;
	private Label healthLabel;
	
	private int nextTrashIndex = 0; // Index of the next trash entity to generate
	private final float generationInterval = 3; // Interval between generations, in seconds
	private float timeSinceLastGeneration = generationInterval; // Timer to track time since last generation
	
	private ArrayList<int[]> generatedCoordinates = new ArrayList<>();
	private String[] thrashImages = {"plastic.png", "can.png", "glass.png", "paper.png"};
	private String[] thrashTypes = { "plastic", "metal", "glass", "paper" };

	
	//To prevent re-instantiation
	private static PlayerGame pEntityStatic;
	private static Monster monsterEntitystatic;
	
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
   
        
        //Create entities, preventing re-instantion
        if (pEntityStatic == null) {
            pEntityStatic = new PlayerGame();
        }
        pEntity = pEntityStatic;
        
        if (monsterEntitystatic == null) {
        	monsterEntitystatic = new Monster();
        }
        monsterEntity = monsterEntitystatic;
        
        
        startAudio("Gameplay", 1.0f);
        
        AI aEntity = new AI();
       
        
        Preferences prefs = Gdx.app.getPreferences("MyGamePrefs");
        //Load player coords
        if (prefs.contains("playerX") && prefs.contains("playerY")) {
            float playerX = prefs.getFloat("playerX", 0); // Default to 0 if not found
            float playerY = prefs.getFloat("playerY", 0);
            pEntity.setPosX(playerX);
            pEntity.setPosY(playerY);
            Gdx.app.log("GameState", "Restored pEntity state: X=" + pEntity.getPosX() + ", Y=" + pEntity.getPosY());
        }
        //Load monster coords
        if (prefs.contains("monsterX") && prefs.contains("monsterY")) {
            float monsterX = prefs.getFloat("monsterX", 0); // Default to 0 if not found
            float monsterY = prefs.getFloat("monsterY", 0);
            monsterEntity.setPosX(monsterX);
            monsterEntity.setPosY(monsterY);
        }
        //Load time since last generated thrash to prevent repeated generation after resuming
        if (prefs.contains("timeSinceLastGeneration")) {
            timeSinceLastGeneration = prefs.getFloat("timeSinceLastGeneration", 0);
        } else {
            timeSinceLastGeneration = generationInterval;
        }
  
        generatedCoordinates = generateCoordinates();
      
//        AI glassbinEntity = new AI("glassbin.png",300, 10); // trashbin2
//        AI plasticbinEntity = new AI("plasticbin.png",400, 10); // trashbin2
//        AI paperbinEntity = new AI("paperbin.png",500, 10); // trashbin2
//        AI canbinEntity = new AI("canbin.png",600, 10); // trashbin2
//        AI binEntity = new AI("thrashbin.png",200,10);
        
        Bin glassbinEntity = new Bin("glassbin.png",300, 10, RecyclableType.GLASS); // trashbin2
        Bin paperbinEntity = new Bin("paperbin.png",500, 10, RecyclableType.PAPER); // trashbin2
        Bin canbinEntity = new Bin("canbin.png",600, 10, RecyclableType.METAL); // trashbin2
        Bin plasticbinEntity = new Bin("plasticbin.png",400,10,RecyclableType.PLASTIC);
       
//        Recyclables glassTrash = new Recyclables("thrashbin.png", 200, 10,RecyclableType.GLASS);
        
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
        System.out.println("Checking for existing PlayerGame entity...");
        if (game.getEntityManager().checkClass(PlayerGame.class) == null) {
        	 game.getEntityManager().addEntity(pEntity);
        }
        if (game.getEntityManager().checkClass(AI.class) == null) {
        
        if(game.getEntityManager().checkClass(AI.class) == null) {
        game.getEntityManager().addEntity(aEntity);
        }
        
//        monsterEntity.setType("waste");
//        glassbinEntity.setType("glass");
//        plasticbinEntity.setType("plastic");
//        paperbinEntity.setType("paper");
//        canbinEntity.setType("can");
        
        //Add bin entities
        //Add Monster entity
        if (game.getEntityManager().checkClass(Monster.class) == null) {
        game.getEntityManager().addEntity(monsterEntity);
        }
        
        //Add Bin entities
        game.getEntityManager().addEntity(glassbinEntity);
        game.getEntityManager().addEntity(plasticbinEntity);
        game.getEntityManager().addEntity(paperbinEntity);
        game.getEntityManager().addEntity(canbinEntity);
        
//        game.getEntityManager().addEntity(binEntity);
               
        //Set collision range
        game.getEntityManager().getCollisionManager().setCollisionRange(24);
        }}
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
            draggedEntity.setPosX(centerX);
            draggedEntity.setPosY(centerY);
        }
        game.getBatch().end();
        
        
      //To generate trash entities randomly at intervals
        timeSinceLastGeneration += delta;
        if (timeSinceLastGeneration >= generationInterval && nextTrashIndex < generatedCoordinates.size()) {
            timeSinceLastGeneration = 0; // Reset the timer

            int[] coord = generatedCoordinates.get(nextTrashIndex);
            String[] thrashImages = {"plastic.png", "can.png", "glass.png", "paper.png"};
        	RecyclableType type = RecyclableType.PLASTIC;
            int index = nextTrashIndex % thrashImages.length; // Calculate index for type, % to prevent index error
            switch (thrashImages[index]) {
            case "plastic.png":
                type = RecyclableType.PLASTIC;
                break;
            case "can.png":
                type = RecyclableType.METAL;
                break;
            case "glass.png":
                type = RecyclableType.GLASS;
                break;
            case "paper.png":
                type = RecyclableType.PAPER;
                break;
            default:
                break;
        }

            Recyclables thrashEntity = new Recyclables(thrashImages[index], coord[0], coord[1],type);
//            thrashEntity.setType(type); // Set the type for the trash entity

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
    	// Function for dragging
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
         //Check for player input and update pEntity's position.
        if (game.getInputOutputManager().getInputKeyboard().keyPressed()) {
            // Assume these methods in PlayerControlManager handle movement based on direction
            if (game.getInputOutputManager().getInputKeyboard().ifRightPressed()) {
            	 Gdx.app.log("Debug", "Right key pressed. Moving pEntity.");
                game.getPlayerControlManager().walk(pEntity, Keys.RIGHT);
            } else if (game.getInputOutputManager().getInputKeyboard().ifLeftPressed()) {
                game.getPlayerControlManager().walk(pEntity, Keys.LEFT);
            } 
            
            if (game.getInputOutputManager().getInputKeyboard().ifUpPressed()) {
                game.getPlayerControlManager().jump(pEntity, true);
            } else if (game.getInputOutputManager().getInputKeyboard().ifDownPressed()) {
                game.getPlayerControlManager().jump(pEntity, false);
            }
        }
        
        
      //Make monster entity follow player
        Gdx.app.log("Before chasePlayer()", "pEntity X: " + pEntity.getPosX() + " pEntityY: " + pEntity.getPosY());
        monsterEntity.chasePlayer(pEntity, game);
        
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
        	saveGameState();	
        	Gdx.app.log("Pausealert", "Paused");
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
    	
        boolean foundRecyclables = false;
        
        // Loop through the list to check for any instance of Recyclables
        for (Entity entity : game.getEntityManager().getEntities()) {
            if (entity instanceof Recyclables) {
                foundRecyclables = true;
                break; // exit loop because found 1 Recyclables instance
            }
        }
        
        if (!foundRecyclables && pEntity.getScoreCounter() < 4) {
        	Gdx.app.log("Tag","foundrecyclables false and score is less than 4");
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
    
        while (coordinates.size() < 8) { // Generate until 4 unique coordinates are found
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
    
    //Function to save 
    public void saveGameState() {
        Preferences prefs = Gdx.app.getPreferences("MyGamePrefs");
        //Save coords for player
    	prefs.putFloat("playerX", pEntity.getPosX());
    	prefs.putFloat("playerY", pEntity.getPosY());
    	//Save coords for monster
    	prefs.putFloat("monsterX", monsterEntity.getPosX());
    	prefs.putFloat("monsterY", monsterEntity.getPosY());
    	//Save last generaeted thrash time
        prefs.putFloat("timeSinceLastGeneration", timeSinceLastGeneration);
        prefs.flush();
    }
    
 
  }