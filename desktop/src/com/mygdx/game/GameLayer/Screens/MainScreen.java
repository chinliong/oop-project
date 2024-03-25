package com.mygdx.game.GameLayer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Screens.BaseScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class MainScreen extends BaseScreen {

	private Sprite backgroundSprite;

	private float newGameButtonX, newGameButtonY, newGameButtonWidth = 160, newGameButtonHeight = 60;
	private float exitGameButtonX, exitGameButtonY, exitGameButtonWidth = 160, exitGameButtonHeight = 60;
	
	private boolean firstTime = true;
	public MainScreen(SimulationLifeCycleManager game) {
		super(game);
		initialiseUI();

		Texture backgroundTexture = new Texture(Gdx.files.internal("forest.jpg"));
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Initialize button positions
		newGameButtonX = (Gdx.graphics.getWidth() - newGameButtonWidth) / 2;
		newGameButtonY = (Gdx.graphics.getHeight() + newGameButtonHeight) / 2;
		exitGameButtonX = newGameButtonX;
		exitGameButtonY = newGameButtonY - newGameButtonHeight; 

	}

	@Override
	public void show() {
		// set stage as input processor, set volume, play volume and loop it
		super.show();
		startAudio("MainMenu", 0.5f);

	}

	@Override
	public void render(float delta) {
		super.render(delta);

		SpriteBatch batch = game.getBatch();
		batch.begin();
		backgroundSprite.draw(batch);
		batch.end();

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();

		handleMouseInput();

	}

	private void handleMouseInput() {
		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

		if (game.getInputOutputManager().getInputMouse().ifLMBPressed()) {
			if (mouseX >= newGameButtonX && mouseX <= newGameButtonX + newGameButtonWidth && mouseY >= newGameButtonY
					&& mouseY <= newGameButtonY + newGameButtonHeight) {
				if(firstTime)
				{
					game.getSceneManager().setScreen(game.getSceneManager().getScreen(IntroScreen.class));
					firstTime = false;
				}
				else {
					game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
					game.getAudioManager().getMusic("MainMenu").stop();
				}

			} else if (mouseX >= exitGameButtonX && mouseX <= exitGameButtonX + exitGameButtonWidth
					&& mouseY >= exitGameButtonY && mouseY <= exitGameButtonY + exitGameButtonHeight) {
				Gdx.app.exit();
			}
		}
	}

	// Setup UI
	@Override
	protected void initialiseUI() {

		Texture newGameTexture = new Texture(Gdx.files.internal("startbutton.png"));
		Texture exitGameTexture = new Texture(Gdx.files.internal("backbutton.png"));

		Drawable newGameDrawable = new TextureRegionDrawable(new TextureRegion(newGameTexture));
		Drawable newGameHoverDrawable = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("startbuttonhover.png"))));
		Drawable exitGameDrawable = new TextureRegionDrawable(new TextureRegion(exitGameTexture));
		Drawable exitGameHoverDrawable = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("backbuttonhover.png"))));

		TextButton newGameButton = new TextButton("",
				new TextButton.TextButtonStyle(newGameDrawable, newGameDrawable, newGameDrawable, game.getFont()));
		TextButton exitGameButton = new TextButton("",
				new TextButton.TextButtonStyle(exitGameDrawable, exitGameDrawable, exitGameDrawable, game.getFont()));

		newGameButton.setSize(160, 60);
		exitGameButton.setSize(160, 60);

		newGameButton.setPosition((Gdx.graphics.getWidth() - newGameButton.getWidth()) / 2,
				(Gdx.graphics.getHeight() + newGameButton.getHeight()) / 2);
		exitGameButton.setPosition((Gdx.graphics.getWidth() - exitGameButton.getWidth()) / 2,
				(Gdx.graphics.getHeight() + exitGameButton.getHeight()) / 2 - 50);


		newGameButton.getStyle().over = newGameHoverDrawable;
		exitGameButton.getStyle().over = exitGameHoverDrawable;

		stage.addActor(newGameButton);
		stage.addActor(exitGameButton);

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		backgroundSprite.setSize(width, height);
		stage.getViewport().update(width, height, true);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	}
}
