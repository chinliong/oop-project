package com.mygdx.game.GameLayer.Screens;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.Player;
import com.mygdx.game.GameEngine.Screens.BaseScreen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.mygdx.game.GameEngine.Entities.AI;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.Player;
import com.mygdx.game.GameEngine.Screens.BaseScreen;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
// Game Layer imports
import com.mygdx.game.GameLayer.Entities.*;

public class PlayScreen extends BaseScreen {
	private AI draggedEntity = null;
	private PlayerGame pEntity;
	private Monster monsterEntity;
	//Player Stats
	private Label scoreLabel;
	private Label healthLabel;
	
	private int nextTrashIndex = 0; // Index of the next trash entity to generate
	private final float generationInterval = 3; // Interval between generations, in seconds
	private float timeSinceLastGeneration = generationInterval; // Timer to track time since last generation
	
	private ArrayList<int[]> generatedCoordinates = new ArrayList<>();

	// pause screen and stuffs
	private boolean paused;
	private Stage pStage;
	
	// background UI 
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    
    private ShapeRenderer shape;
	
	
	
    public PlayScreen(SimulationLifeCycleManager game) {
        super(game);
        setBgColour(Color.SKY);
        initialiseUI();
        
        backgroundTexture = new Texture(Gdx.files.internal("forest.jpg"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void initialiseUI() {
    	createText("This is the PlayScreen screen");
    	scoreLabel = createText("Player Score Counter: ", 50,580);
    	healthLabel = createText("Player Health: ", 500,580);
    
    }

    @Override
    public void show() {
        super.show();
        startAudio("Gameplay", 1.0f);
        
        AI aEntity = new AI();
        pEntity = new PlayerGame();
       shape = new ShapeRenderer();
        generatedCoordinates = generateCoordinates();
         monsterEntity = new Monster("1.png",200, 10); // Monster entity that follows player
//        AI glassbinEntity = new AI("glassbin.png",300, 10); // trashbin2
//        AI plasticbinEntity = new AI("plasticbin.png",400, 10); // trashbin2
//        AI paperbinEntity = new AI("paperbin.png",500, 10); // trashbin2
//        AI canbinEntity = new AI("canbin.png",600, 10); // trashbin2
//        AI binEntity = new AI("thrashbin.png",200,10);
        
        Bin glassbinEntity = new Bin("glassbin.png",150, 10, RecyclableType.GLASS); // trashbin2
        Bin paperbinEntity = new Bin("paperbin.png",300, 10, RecyclableType.PAPER); // trashbin2
        Bin canbinEntity = new Bin("canbin.png",450, 10, RecyclableType.METAL); // trashbin2
        Bin plasticbinEntity = new Bin("plasticbin.png",600,10,RecyclableType.PLASTIC);
       
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
        }
        if (game.getEntityManager().checkClass(AI.class) == null) {
        game.getEntityManager().addEntity(aEntity);
        }
        
//        monsterEntity.setType("waste");
//        glassbinEntity.setType("glass");
//        plasticbinEntity.setType("plastic");
//        paperbinEntity.setType("paper");
//        canbinEntity.setType("can");
        
        //Add bin entities
        game.getEntityManager().addEntity(monsterEntity);
        game.getEntityManager().addEntity(glassbinEntity);
        game.getEntityManager().addEntity(plasticbinEntity);
        game.getEntityManager().addEntity(paperbinEntity);
        game.getEntityManager().addEntity(canbinEntity);
        
//        game.getEntityManager().addEntity(binEntity);
               
        //Set collision range
        game.getEntityManager().getCollisionManager().setCollisionRange(24);
        
        // Pause stage window
//        Window pStage = new Window("PAUSE", skin);
    }

    @Override
    public void render(float delta) {
    	if (paused)
    	{ // DONT TOUCH - PAUSE STUFF
    	    pStage.act(delta);
    	    pStage.draw();
    	    
    	    Gdx.gl.glClearColor(getBgColour().r, getBgColour().g, getBgColour().b, getBgColour().a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            game.getBatch().begin();
            backgroundSprite.draw(game.getBatch()); // Draw the pause background
            game.getBatch().end();

            pStage.act(delta);
            pStage.draw();
    	}
    	else {
    		shape.begin(ShapeRenderer.ShapeType.Line);

    		Gdx.gl.glClearColor(getBgColour().r, getBgColour().g, getBgColour().b, getBgColour().a);
    		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // Draw the background
            game.getBatch().begin();
            backgroundSprite.draw(game.getBatch());
            game.getBatch().end();

			super.stage.act(delta);
            super.stage.draw();
            
            moveEntities();

            game.getBatch().begin();
            handleInput();
            drawEntities();
            
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
            shape.end();
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
//                thrashEntity.setType(type); // Set the type for the trash entity

                game.getEntityManager().addEntity(thrashEntity);

                nextTrashIndex++; // Prepare for the next entity
            }
            
            
            updatePlayerScore();
            checkGameConditions();
//             Update the camera to follow the player
//            if (pEntity != null) {
//                camera1.camera.position.set(pEntity.getPosX(), pEntity.getPosY(), 0);
//                camera1.camera.update();
//            }

            // Set the batch's projection matrix
//            game.getBatch().setProjectionMatrix(camera1.camera.combined);
            
    	}
    	
    	if(game.getInputOutputManager().getInputKeyboard().ifEscPressed()) {
			paused = true;
			setupPauseMenu();
		}
    }
    
    // Pause stuff, maybe need to link to the methods that is already inside BaseScreen, 
    // see how to implement BaseScreen methods to do all these
    private void setupPauseMenu() {
        pStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(pStage);

        Texture resumeGameTexture = new Texture(Gdx.files.internal("backbutton.png"));
        Texture menuTexture = new Texture(Gdx.files.internal("menuButton.png"));
        
        Drawable menuDrawable = new TextureRegionDrawable(new TextureRegion(menuTexture));
    	Drawable menuHoverDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("menubuttonhover.png"))));
    	Drawable resumeGameDrawable = new TextureRegionDrawable(new TextureRegion(resumeGameTexture));
    	Drawable resumeGameHoverDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("backbuttonhover.png"))));
    	
    	TextButton menuButton = new TextButton("", new TextButton.TextButtonStyle(menuDrawable, menuDrawable, menuDrawable, game.getFont()));
    	TextButton resumeGameButton = new TextButton("", new TextButton.TextButtonStyle(resumeGameDrawable, resumeGameDrawable, resumeGameDrawable, game.getFont()));
    	
    	menuButton.setSize( 160, 60);
    	resumeGameButton.setSize( 160, 60);
    	
    	//menuButton.setPosition((Gdx.graphics.getWidth() - menuButton.getWidth()) / 2, (Gdx.graphics.getHeight() + menuButton.getHeight()) / 2);
    	//resumeGameButton.setPosition((Gdx.graphics.getWidth() - resumeGameButton.getWidth()) / 2 - 80, (Gdx.graphics.getHeight() + resumeGameButton.getHeight()) / 2);
    	
    	menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	// Logic to go to Main Menu
                game.getAudioManager().getMusic("Gameplay").stop();
                game.getEntityManager().disposeEntities();
                game.getSceneManager().removeScreen(PlayScreen.class);
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(MainScreen.class));
            }
        });
    	
    	resumeGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	paused = false;
            }
        });
    	
    	menuButton.getStyle().over = menuHoverDrawable;
    	resumeGameButton.getStyle().over = resumeGameHoverDrawable;        

        float spaceBetweenButtons = 20; // 20 pixels space between buttons
        float buttonWidth = Math.max(resumeGameButton.getWidth(), menuButton.getWidth());
        float totalWidth = buttonWidth * 2 + spaceBetweenButtons;
        float startX = (Gdx.graphics.getWidth() - totalWidth) / 2;
        resumeGameButton.setPosition(startX, Gdx.graphics.getHeight() / 2 - resumeGameButton.getHeight() / 2);
        menuButton.setPosition(startX + resumeGameButton.getWidth() + spaceBetweenButtons, Gdx.graphics.getHeight() / 2 - menuButton.getHeight() / 2);

        pStage.addActor(resumeGameButton);
        pStage.addActor(menuButton);
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
    
    private float clampValue(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private void drawEntities()
    {
    	for(int i = 0 ; i < game.getEntityManager().getEntities().size(); i++)
    	{
    		for(Entity entity: game.getEntityManager().getEntities())
    		{
    			entity.draw(game.getBatch());
    		}
    		for (Entity cEntity: game.getEntityManager().getEntities())
    		{
    			CollidableEntity eEntity = (CollidableEntity) cEntity;
    			eEntity.draw(shape);
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
        	 if (pEntity != null) {
        	        // Assuming pEntity.posX and pEntity.posY are your entity's current positions
        	        // And assuming pEntity.width and pEntity.height are the entity's dimensions
        	        // Screen width and height can be retrieved via Gdx.graphics.getWidth() and Gdx.graphics.getHeight()
        	        
        	        pEntity.setPosX(clampValue(pEntity.getPosX(), 0, Gdx.graphics.getWidth() - pEntity.getWidth()));
        	        pEntity.setPosY(clampValue(pEntity.getPosY(), 0, Gdx.graphics.getHeight() - pEntity.getHeight()));

        	        // Update other entities' positions similarly...
        	    }
        	
            if (game.getEntityManager().getEntities().get(i) instanceof Player && game.getInputOutputManager().getInputKeyboard().keyPressed()==true) { 
                if (game.getInputOutputManager().getInputKeyboard().ifDPressed()==true) { 
                    game.getPlayerControlManager().walk((Player) game.getEntityManager().getEntities().get(i), Keys.RIGHT);
                } else if (game.getInputOutputManager().getInputKeyboard().ifAPressed()==true) { 
                    game.getPlayerControlManager().walk((Player) game.getEntityManager().getEntities().get(i), Keys.LEFT);                    
                } 
                else if (game.getInputOutputManager().getInputKeyboard().ifWPressed()==true) { 
                    game.getPlayerControlManager().jump((Player) game.getEntityManager().getEntities().get(i), true);
                }
                else if (game.getInputOutputManager().getInputKeyboard().ifSPressed()==true) { 
                    game.getPlayerControlManager().jump((Player) game.getEntityManager().getEntities().get(i), false);
                }
            } 
        }
        
        // Make monster entity follow player
        monsterEntity.chasePlayer(pEntity, game);
        
        if (pEntity != null) {
            pEntity.updateAttachedEntities();
        }
        
        // Check for throwing
        if (game.getInputOutputManager().getInputMouse().ifLMBPressed()) {
            if(pEntity.hasAttachedEntities()) {
            	 // Get mouse position in screen coordinates
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                // Inverting the Y-coordinate if necessary
                float mouseYInverted = Gdx.graphics.getHeight() - mousePos.y;
                // Calculate the direction vector from the player to the cursor
                Vector2 direction = new Vector2(mousePos.x - pEntity.getPosX(), mouseYInverted - pEntity.getPosY()).nor();
                pEntity.throwAttachedEntity(direction);

            }
        }

 
    }

    private void checkGameConditions() {
//        pauseScreenIfRequested();
        game.getEntityManager().getCollisionManager().checkForCollision(game);
        checkWinCondition();
    }


//    private void pauseScreenIfRequested() {
//        if (game.getInputOutputManager().getInputKeyboard().ifEscPressed()) {
////            game.getSceneManager().setScreen(game.getSceneManager().getScreen(PauseScreen.class));
////            game.getAudioManager().getMusic("Gameplay").stop();
//        	pause();
//        }
//    }
    
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

    }

