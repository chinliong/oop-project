package com.mygdx.game.GameLayer.Screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.Player;
import com.mygdx.game.GameEngine.Screens.BaseScreen;
import com.mygdx.game.GameLayer.Entities.Bin;
import com.mygdx.game.GameLayer.Entities.Monster;
import com.mygdx.game.GameLayer.Entities.PlayerGame;
import com.mygdx.game.GameLayer.Entities.RecyclableType;
import com.mygdx.game.GameLayer.Entities.Recyclables;

public class IntroScreen extends BaseScreen {

	private Label gameText;
	private Label gameText2;
	private Label gameText3;
	private Label gameText4;
	private PlayerGame playerEntity;
	private Monster monsterEntity;
	private boolean done;

	public IntroScreen(SimulationLifeCycleManager game) {
		super(game);
		initialiseUI();
		done = false;
	}

	@Override
	public void show() {
		playerEntity = new PlayerGame();
		monsterEntity = new Monster(500, 200);

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
		gameText.setText("These are the recyclables to collect");
		gameText2.setText("Press 'ENTER' to BEGIN PLAYING");
		gameText3.setText("Press 'SPACE' when picking up trash and disposing into bins.");
		gameText4 = createText(" Aim with your mouse, 'LEFT CLICK' to throw trash at monster!",50,250);
	}

	private void resetCollide() {
		if (playerEntity.hasCollided(monsterEntity, 20)) {
			monsterEntity.setPosX(500);
			monsterEntity.setPosY(200);
		}
	}
	
	@Override
	public void render(float delta) {
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
		// monsterEntity.chasePlayer(playerEntity, game);
	}

	@Override
	protected void initialiseUI() {
		// TODO Auto-generated method stub
		gameText = createText("Press W,A,S,D to move your player ", 50, 580);
		gameText2 = createText("Press 'ENTER' to continue",50,400);
		gameText3 = createText("DO NOT GET CAUGHT BY THE MONSTER",50,300);
	}
}
