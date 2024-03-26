package com.mygdx.game.GameLayer.Screens;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.Player;
import com.mygdx.game.GameEngine.Screens.BaseScreen;
import com.mygdx.game.GameEngine.Managers.AIControlManager;
import com.mygdx.game.GameEngine.Managers.LevelManager;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameLayer.AIControl.*;
import com.mygdx.game.GameLayer.Entities.*;

public class PlayScreen extends BaseScreen {
	private PlayerGame pEntity;
	private Monster monsterEntity;
	
	// Player Stats
	private Label scoreLabel;
	private Label healthLabel;
	private Label nextLevelLabel;
	private Label currentLevelLabel;
	private Label targetLabel;
	private int nextTrashIndex = 0; // Index of the next trash entity to generate
	private final float generationInterval = 3; // Interval between generations, in seconds
	private float timeSinceLastGeneration = generationInterval; // Timer to track time since last generation

	private float menuGameButtonX, menuGameButtonY, menuGameButtonWidth = 160, menuGameButtonHeight = 60;
	private float resumeGameButtonX, resumeGameButtonY, resumeGameButtonWidth = 160, resumeGameButtonHeight = 60;

	// pause screen and stuffs
	private boolean paused;
	private Stage pStage;

	// background UI
	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	
	public PlayScreen(SimulationLifeCycleManager game) {
		super(game);
		setBgColour(Color.SKY);
		initialiseUI();

		backgroundTexture = new Texture(Gdx.files.internal("forest.jpg"));
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		AIControlManager aiControlManager = new AIControlManager();
	    ChasingPlayer chaseBehaviour = new ChasingPlayer(aiControlManager);
	    this.monsterEntity = new Monster(chaseBehaviour);
	    

	}

	@Override
	public void initialiseUI() {
		scoreLabel = createText("Player Score Counter: ", 50, 580);
		healthLabel = createText("Player Health: ", 50, 550);
		currentLevelLabel = createText("This is Level: ", 500, 580);
		nextLevelLabel = createText("Points to next level: ",500,550);
		targetLabel = createText("Dispose to go to the next level!", 50,500);
	}

	@Override
	public void show() {
		super.show();
		startAudio("Gameplay", 1.0f);

		pEntity = new PlayerGame();
		

		Bin glassbinEntity = new Bin("glassbin.png", 150, 10, RecyclableType.GLASS); // trashbin2
		Bin paperbinEntity = new Bin("paperbin.png", 300, 10, RecyclableType.PAPER); // trashbin2
		Bin canbinEntity = new Bin("canbin.png", 450, 10, RecyclableType.METAL); // trashbin2
		Bin plasticbinEntity = new Bin("plasticbin.png", 600, 10, RecyclableType.PLASTIC);

		// Check for existing entity before adding
		if (game.getEntityManager().checkClass(Player.class) == null) {
			game.getEntityManager().addEntity(pEntity);
		}		
		

		// Add bin entities
		game.getEntityManager().addEntity(monsterEntity);
		game.getEntityManager().addEntity(glassbinEntity);
		game.getEntityManager().addEntity(plasticbinEntity);
		game.getEntityManager().addEntity(paperbinEntity);
		game.getEntityManager().addEntity(canbinEntity);

		// Set collision range
		game.getEntityManager().getCollisionManager().setCollisionRange(60);

	}

	@Override
	public void render(float delta) {
		if (paused) { // DONT TOUCH - PAUSE STUFF
			pStage.act(delta);
			pStage.draw();

			Gdx.gl.glClearColor(getBgColour().r, getBgColour().g, getBgColour().b, getBgColour().a);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			game.getBatch().begin();
			backgroundSprite.draw(game.getBatch()); // Draw the pause background
			game.getBatch().end();

			pStage.act(delta);
			pStage.draw();

			handleMouseInput();

		} else {
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

			game.getBatch().end();

			// To generate trash entities randomly at intervals
			ArrayList<int[]> generatedCoordinates = Recyclables.generateCoordinates();
			timeSinceLastGeneration += delta;
			if (timeSinceLastGeneration >= generationInterval && nextTrashIndex < generatedCoordinates.size()) {
				timeSinceLastGeneration = 0; // Reset the timer

				int[] coord = generatedCoordinates.get(nextTrashIndex);
				String[] thrashImages = { "plastic.png", "can.png", "glass.png", "paper.png" };
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

				Recyclables thrashEntity = new Recyclables(thrashImages[index], coord[0], coord[1], type);

				game.getEntityManager().addEntity(thrashEntity);

				nextTrashIndex++; // Prepare for the next entity
			}
			checkGameConditions();

			updatePlayerScore();

		}

		if (game.getInputOutputManager().getInputKeyboard().ifEscPressed()) {
			paused = true;
			game.getAudioManager().getMusic("Gameplay").stop();
			setupPauseMenu();
		}
	}

	private void handleMouseInput() {
		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

		if (game.getInputOutputManager().getInputMouse().ifLMBPressed()) {
			if (mouseX >= menuGameButtonX && mouseX <= menuGameButtonX + menuGameButtonWidth
					&& mouseY >= menuGameButtonY && mouseY <= menuGameButtonY + menuGameButtonHeight) {

				// Logic to go to Main Menu
				game.getAudioManager().getMusic("Gameplay").stop();
				game.getEntityManager().disposeEntities();
				game.getSceneManager().removeScreen(PlayScreen.class);
				game.getSceneManager().setScreen(game.getSceneManager().getScreen(MainScreen.class));

			} else if (mouseX >= resumeGameButtonX && mouseX <= resumeGameButtonX + resumeGameButtonWidth
					&& mouseY >= resumeGameButtonY && mouseY <= resumeGameButtonY + resumeGameButtonHeight) {
				paused = false;
				game.getAudioManager().getMusic("Gameplay").play();
			}
		}
	}

	// Pause stuff, maybe need to link to the methods that is already inside
	// BaseScreen,
	// see how to implement BaseScreen methods to do all these
	private void setupPauseMenu() {
		pStage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(pStage);

		Texture resumeGameTexture = new Texture(Gdx.files.internal("backbutton.png"));
		Texture menuTexture = new Texture(Gdx.files.internal("menuButton.png"));

		Drawable menuDrawable = new TextureRegionDrawable(new TextureRegion(menuTexture));
		Drawable menuHoverDrawable = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("menubuttonhover.png"))));
		Drawable resumeGameDrawable = new TextureRegionDrawable(new TextureRegion(resumeGameTexture));
		Drawable resumeGameHoverDrawable = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("backbuttonhover.png"))));

		TextButton menuButton = new TextButton("",
				new TextButton.TextButtonStyle(menuDrawable, menuDrawable, menuDrawable, game.getFont()));
		TextButton resumeGameButton = new TextButton("", new TextButton.TextButtonStyle(resumeGameDrawable,
				resumeGameDrawable, resumeGameDrawable, game.getFont()));

		menuButton.setSize(160, 60);
		resumeGameButton.setSize(160, 60);

		menuButton.getStyle().over = menuHoverDrawable;
		resumeGameButton.getStyle().over = resumeGameHoverDrawable;

		float spaceBetweenButtons = 20; // 20 pixels space between buttons
		float buttonWidth = Math.max(resumeGameButton.getWidth(), menuButton.getWidth());
		float totalWidth = buttonWidth * 2 + spaceBetweenButtons;
		float startX = (Gdx.graphics.getWidth() - totalWidth) / 2;
		resumeGameButton.setPosition(startX, Gdx.graphics.getHeight() / 2 - resumeGameButton.getHeight() / 2);
		menuButton.setPosition(startX + resumeGameButton.getWidth() + spaceBetweenButtons,
				Gdx.graphics.getHeight() / 2 - menuButton.getHeight() / 2);

		pStage.addActor(resumeGameButton);
		pStage.addActor(menuButton);
		// Initialize button positions
		menuGameButtonX = (Gdx.graphics.getWidth() - (Math.max(resumeGameButtonWidth, menuGameButtonWidth) * 2 + 20))
				/ 2 + resumeGameButtonWidth + 20;
		menuGameButtonY = Gdx.graphics.getHeight() / 2 - menuGameButtonHeight / 2;
		resumeGameButtonX = (Gdx.graphics.getWidth() - Math.max(resumeGameButtonWidth, menuGameButtonWidth) * 2 + 20)
				/ 2;
		resumeGameButtonY = Gdx.graphics.getHeight() / 2 - resumeGameButtonHeight / 2;
	}

	private void updatePlayerScore() {
		int scoreCounter = pEntity.getScoreCounter();
		int healthCounter = pEntity.getPlayerHealth();
		scoreLabel.setText("Player Score: " + scoreCounter);
		healthLabel.setText("Player Health: " + healthCounter);
		nextLevelLabel.setText("Points to next level: " + (game.getLevelManager().getPointsToWin() - scoreCounter));
		currentLevelLabel.setText("Current Level: " + (game.getLevelManager().getCurrentLevel()+1));
		
		if (game.getLevelManager().getCurrentLevel() == 0)
		{
			targetLabel.setText("Dispose 4 recyclables to go Level 2!");
		}
		else if (game.getLevelManager().getCurrentLevel() == 1)
		{
			targetLabel.setText("Dispose 6 recyclables to go Level 3!");

		}
		else {
			nextLevelLabel.setText(null);
			targetLabel.setText("Dispose 8 recyclables to WIN!");

		}
	}

	private void handleInput() {
		if (game.getInputOutputManager().getInputMouse().mousePressed()) { // check if mouse pressed

			if (game.getInputOutputManager().getInputMouse().ifLMBPressed()) {
				game.getInputOutputManager().getInputMouse().setInputReceived(true);
				game.getInputOutputManager().getOutput().onPressLMB(Buttons.LEFT,
						game.getInputOutputManager().getInputMouse().getInputReceived());
			}

			if (game.getInputOutputManager().getInputMouse().ifRMBPressed()) {
				game.getInputOutputManager().getInputMouse().setInputReceived(true);
				game.getInputOutputManager().getOutput().onPressRMB(Buttons.RIGHT,
						game.getInputOutputManager().getInputMouse().getInputReceived());
			}

		}

	}

	private float clampValue(float value, float min, float max) {
		return Math.max(min, Math.min(max, value));
	}

	private void drawEntities() {
		for (int i = 0; i < game.getEntityManager().getEntities().size(); i++) {
			for (Entity entity : game.getEntityManager().getEntities()) {
				entity.draw(game.getBatch());
			}

		}
	}

	private void moveEntities() {
		for (int i = 0; i < game.getPlayerManager().getPlayerList().size(); i++) {
			if (pEntity != null) {
				// Assuming pEntity.posX and pEntity.posY are your entity's current positions
				// And assuming pEntity.width and pEntity.height are the entity's dimensions
				// Screen width and height can be retrieved via Gdx.graphics.getWidth() and
				// Gdx.graphics.getHeight()
				pEntity.setPosX(clampValue(pEntity.getPosX(), 0, Gdx.graphics.getWidth() - pEntity.getWidth()));
				pEntity.setPosY(clampValue(pEntity.getPosY(), 0, Gdx.graphics.getHeight() - pEntity.getHeight()));
			}
			if (game.getPlayerManager().getPlayerList().get(i) instanceof Player
					&& game.getInputOutputManager().getInputKeyboard().keyPressed() == true) {
				// move horizontal
				if (game.getInputOutputManager().getInputKeyboard().ifDPressed() == true
						|| game.getInputOutputManager().getInputKeyboard().ifAPressed() == true) {
					game.getPlayerManager().getPlayerControlManager()
							.walk((Player) game.getPlayerManager().getPlayerList().get(i));
				} // move vertical 
				else if (game.getInputOutputManager().getInputKeyboard().ifWPressed() == true
						|| game.getInputOutputManager().getInputKeyboard().ifSPressed() == true) {
					game.getPlayerManager().getPlayerControlManager()
							.jump((Player) game.getPlayerManager().getPlayerList().get(i));
				}
			}

		}

		// Make monster entity follow player
		monsterEntity.chase(pEntity, game);

		if (pEntity != null) {
			pEntity.updateAttachedEntities();
		}

		// Check for throwing
		if (game.getInputOutputManager().getInputMouse().ifLMBPressed()) {
			if (pEntity.hasAttachedEntities()) {
				// Get mouse position in screen coordinates
				Vector3 mousePos = new Vector3(game.getInputOutputManager().getInputMouse().getposXPressed(),
						game.getInputOutputManager().getInputMouse().getposYPressed(), 0);
				// Inverting the Y-coordinate if necessary
				float mouseYInverted = Gdx.graphics.getHeight() - mousePos.y;
				// Calculate the direction vector from the player to the cursor
				Vector2 direction = new Vector2(mousePos.x - pEntity.getPosX(), mouseYInverted - pEntity.getPosY())
						.nor();
				pEntity.throwAttachedEntity(direction);

			}
		}
	}

	private void checkGameConditions() {
		game.getEntityManager().getCollisionManager().checkForCollision(game);
		checkWinCondition();
	}

	private void checkWinCondition() {
		LevelManager levelManager = ((SimulationLifeCycleManager)game).getLevelManager();

		boolean foundRecyclables = false;

		// Loop through the list to check for any instance of Recyclables
		for (Entity entity : game.getEntityManager().getEntities()) {
			if (entity instanceof Recyclables) {
				foundRecyclables = true;
				break; // exit loop because found 1 Recyclables instance
			}
		}

		if (!foundRecyclables && pEntity.getScoreCounter() < levelManager.getPointsToWin()) {
			game.getSceneManager().transitionToScreen(WinLoseScreen.class, false);

		}
		if (pEntity.getScoreCounter() == levelManager.getPointsToWin()) {
			game.getEntityManager().disposeEntities();
	        // Transition to the next level
	        levelManager.nextLevel();
	    } else if (pEntity.getPlayerHealth() == 0) {
	        // Player loses the current level
	        game.getSceneManager().transitionToScreen(WinLoseScreen.class, false);
	    }

	}

	@Override
	public void hide() {
		game.getSceneManager().removeScreen(PlayScreen.class);
	}

}
