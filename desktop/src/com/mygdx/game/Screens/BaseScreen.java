package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameMaster;

public abstract class BaseScreen implements Screen {
    // i will use protected cos i only want my child classes to access this
    protected GameMaster game;
    protected Stage stage;

    public BaseScreen(GameMaster game) {
        this.game = game;
        stage = new Stage(new ScreenViewport()); // actually idk if i should new screenviewport or mygame viewport
    }

    protected abstract void initUI();

    @Override
    public void show() {
        //this is to set the stage as the input processor, so it will
        //send the input to the stage
        Gdx.input.setInputProcessor(stage);
    }




    protected void addButton(String text, float x, float y, ClickListener listener){
        // this is like a template to adda button to the stage
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getFont(); // Assuming you have a method to get the font
        TextButton button = new TextButton(text, textButtonStyle);
        button.setPosition(x, y);
        button.addListener(listener);
        stage.addActor(button);


    }

    //add a text in the middle top of the screen
    protected void addText(String text){
       // game.getFont().draw(game.batch, text, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getFont();
        Label label = new Label(text, labelStyle);
        label.setColor(Color.WHITE);
        label.setPosition((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight() - 20);

        stage.addActor(label);
    }


    @Override
    public void render(float delta) {
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







