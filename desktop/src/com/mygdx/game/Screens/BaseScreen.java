package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.mygdx.game.GameMaster;
import com.mygdx.game.SimulationLifeCycleManager;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public abstract class BaseScreen implements Screen {
//    protected GameMaster game;
    protected SimulationLifeCycleManager game;
    protected Stage stage;
    private Color bgColour = Color.BLACK;

//    public BaseScreen(GameMaster game) {
//        this.game = game;
//        stage = new Stage(new ScreenViewport()); 
//    }    
    
    public BaseScreen(SimulationLifeCycleManager game) {
        this.game = game;
        stage = new Stage(new ScreenViewport()); 
    }

    protected abstract void initialiseUI();
    
    public void setBgColour(Color colour) {
    	this.bgColour = colour;
    }

    //Route all user input to stage
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    protected void createButton(String text, float x, float y, ClickListener listener){
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getFont(); 
        TextButton button = new TextButton(text, textButtonStyle);
        button.setPosition(x, y);
        button.addListener(listener);
        stage.addActor(button);

    }
    protected void createImageButton(String imagePath, ClickListener listener) {
        // Load the texture for the button image
        Texture buttonTexture = new Texture(Gdx.files.internal(imagePath));
        Drawable drawable = new TextureRegionDrawable(buttonTexture);

        // Create the ImageButtonStyle
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = drawable; // Set the image to be used for the button's default state

        // Create the ImageButton
        ImageButton imageButton = new ImageButton(imageButtonStyle);
        float x = (Gdx.graphics.getWidth() - imageButton.getWidth()) / 2;
        float y = (Gdx.graphics.getHeight() - imageButton.getHeight()) / 2;
        imageButton.setPosition(x, y); // Position the button on the screen
        imageButton.addListener(listener); //
        // Add the button to the stage
        stage.addActor(imageButton);
    }
    
    //Function to add text at the top middle of the screen
    protected void createText(String text){
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getFont();
        Label label = new Label(text, labelStyle);
        label.setColor(Color.WHITE);
        label.setPosition((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight() - 20);

        stage.addActor(label);
    }


    @Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(bgColour.r, bgColour.g, bgColour.b, bgColour.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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







