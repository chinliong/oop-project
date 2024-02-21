package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameMaster;

public abstract class BaseScreen implements Screen {
    protected GameMaster game;
    protected Stage stage;
    private Color bgColour = Color.BLACK;

    public BaseScreen(GameMaster game) {
        this.game = game;
        stage = new Stage(new ScreenViewport()); 
    }

    protected abstract void initialiseUI();
    
    public void setBgColour(Color colour) {
    	this.bgColour = colour;
    }

    //set stage as input processor to send input to stage
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







