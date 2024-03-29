package com.mygdx.game.GameEngine.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;

public abstract class BaseScreen implements Screen {
//    protected GameMaster game;
	protected SimulationLifeCycleManager game;
	protected Stage stage;
	private Color bgColour = Color.BLACK;

	public BaseScreen(SimulationLifeCycleManager game) {
		this.game = game;
		stage = new Stage(new ScreenViewport());
	}

	protected abstract void initialiseUI();

	public Color getBgColour() {
		return bgColour;
	}

	public void setBgColour(Color colour) {
		this.bgColour = colour;
	}

	// Route all user input to stage
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	// Function to add text at the top middle of the screen
//    protected void createText(String text){
	protected Label createText(String text) {
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = game.getFont();
		Label label = new Label(text, labelStyle);
		label.setColor(Color.WHITE);
		label.setPosition((float) Gdx.graphics.getWidth() / 2 - 50, (float) Gdx.graphics.getHeight() / 2);

		stage.addActor(label);

		return label;
	}

	protected Label createText(String text, int posX, int posY) {
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = game.getFont();
		Label label = new Label(text, labelStyle);
		label.setColor(Color.WHITE);
		label.setPosition((float) posX, (float) posY);
		// Scale the font size
		float scaleFactor = 1.5f; // Example scaling factor; adjust as needed
		labelStyle.font.getData().setScale(scaleFactor);
		stage.addActor(label);

		return label;
	}

	protected void startAudio(String audioName, float volume) {
		game.getAudioManager().getMusic(audioName).setVolume(volume);
		game.getAudioManager().getMusic(audioName).play();
		game.getAudioManager().getMusic(audioName).isLooping();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(bgColour.r, bgColour.g, bgColour.b, bgColour.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	// Pause Screen
	public Skin createBasicSkin() {
		// Create a font
		BitmapFont font = new BitmapFont();
		Skin skin = new Skin();
		skin.add("default", font);

		// Create a texture
		Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("background", new Texture(pixmap));

		// Create a button style
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
		textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
		textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		return skin;
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}
}
