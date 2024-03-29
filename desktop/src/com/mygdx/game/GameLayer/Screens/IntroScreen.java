package com.mygdx.game.GameLayer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.Player;
import com.mygdx.game.GameEngine.Managers.AIControlManager;
import com.mygdx.game.GameEngine.Screens.BaseScreen;
import com.mygdx.game.GameLayer.AIControl.ChasingPlayer;
import com.mygdx.game.GameLayer.Entities.Bin;
import com.mygdx.game.GameLayer.Entities.Monster;
import com.mygdx.game.GameLayer.Entities.PlayerGame;
import com.mygdx.game.GameLayer.Entities.RecyclableType;
import com.mygdx.game.GameLayer.Entities.Recyclables;

public class IntroScreen extends BaseScreen {

	private Label gameText1;
	private Label gameText2;
	private Label gameText3;
	private Label gameText4;
	private Label gameText5;
	private PlayerGame playerEntity;
	private Monster monsterEntity;
	private boolean done;

	public IntroScreen(SimulationLifeCycleManager game) {
		super(game);
		initialiseUI();
		done = false;
		
		AIControlManager aiControlManager = new AIControlManager();
	    ChasingPlayer chaseBehaviour = new ChasingPlayer(aiControlManager);
	    this.monsterEntity = new Monster(chaseBehaviour);
	}

	@Override
	public void show() {
		playerEntity = new PlayerGame();

		game.getEntityManager().addEntity(playerEntity);
		game.getEntityManager().addEntity(monsterEntity);

	}

	private void setupClickListener() {
		
		if (game.getInputOutputManager().getInputKeyboard().ifEnterPressed()) {

			if(!done)
			{
				game.getEntityManager().getEntities().clear();
				initializeRecyclablesAndBins();
				done = true;
			}
			else{
				game.getEntityManager().getEntities().clear();
				game.getAudioManager().getMusic("MainMenu").stop();
				game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
			}
			
		}
	}

	private void playerMovement() {
		for (int i = 0; i < game.getPlayerManager().getPlayerList().size(); i++) {

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
	}
	
	private void initializeRecyclablesAndBins() {
		// bins
		Bin glassbinEntity = new Bin("glassbin.png", 150, 10, RecyclableType.GLASS); // trashbin2
		Bin paperbinEntity = new Bin("paperbin.png", 300, 10, RecyclableType.PAPER); // trashbin2
		Bin canbinEntity = new Bin("canbin.png", 450, 10, RecyclableType.METAL); // trashbin2
		Bin plasticbinEntity = new Bin("plasticbin.png", 600, 10, RecyclableType.PLASTIC);

		// recyclables
		Recyclables glassEntity = new Recyclables("glass.png", 150, 150, RecyclableType.GLASS); // trashbin2
		Recyclables paperEntity = new Recyclables("paper.png", 300, 150, RecyclableType.PAPER); // trashbin2
		Recyclables canEntity = new Recyclables("can.png", 450, 150, RecyclableType.METAL); // trashbin2
		Recyclables plasticEntity = new Recyclables("plastic.png", 600, 150, RecyclableType.PLASTIC);

		game.getEntityManager().addEntity(glassbinEntity);
		game.getEntityManager().addEntity(paperbinEntity);
		game.getEntityManager().addEntity(canbinEntity);
		game.getEntityManager().addEntity(plasticbinEntity);
		game.getEntityManager().addEntity(glassEntity);
		game.getEntityManager().addEntity(paperEntity);
		game.getEntityManager().addEntity(canEntity);
		game.getEntityManager().addEntity(plasticEntity);
		gameText1.setText("Collect and Dispose recyclables to the correct bin to WIN the 3-level game!");
		gameText5.setText("1st level: 4pts    2nd level: 6pts    3rd level: 8pts");
		gameText3.setText("Press 'SPACE' when collecting recyclables and disposing them into bins.");
		gameText4.setText("Aim with your mouse, 'LEFT CLICK' to throw recyclables at the monster!");
		gameText2.setText("Press 'ENTER' to BEGIN PLAYING");
	}

	private void resetCollide() {
		if (playerEntity.hasCollided(monsterEntity, 20)) {
			monsterEntity.setPosX(500);
			monsterEntity.setPosY(200);
		}
	}
	
	@Override
	public void render(float delta) {
		 // Set the clear color to dark green
	    Gdx.gl.glClearColor(0, 0.2f, 0, 1);
	    // Clear the screen with the current clear color
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
		super.stage.act(delta);
		super.stage.draw();
		game.getBatch().begin();
		for (Entity entity : game.getEntityManager().getEntities()) {
			entity.draw(game.getBatch());
		}
		game.getBatch().end();
		setupClickListener();
		playerMovement();
		resetCollide();
		
		monsterEntity.chase(playerEntity, game);
	}

	@Override
	protected void initialiseUI() {
	    gameText1 = createText("Press W,A,S,D to move your player", 50,400);
	    gameText5 = createText("", 50,525);
	    gameText2 = createText("Press 'ENTER' to continue", 50,300);
	    gameText3 = createText("DO NOT GET CAUGHT BY THE MONSTER, YOU HAVE 3 LIVES!", 50,350);
	    gameText4 = createText("", 50,280);
	}
}
