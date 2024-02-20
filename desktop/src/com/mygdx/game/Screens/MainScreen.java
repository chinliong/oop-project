package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameMaster;
import com.badlogic.gdx.Gdx;

public class MainScreen extends BaseScreen { //basescreen already implemented Screen
    //Texture texture;

    public MainScreen(GameMaster game) {
        super(game);
       // texture = new Texture("badlogic.jpg");
        initUI();


        }


    @Override
    public void show() {

        super.show(); //this is to set the stage as the input processor
        game.getSoundManager().setVolume(0.5f); //set mainscreen volume to 50%
        game.getSoundManager().getMusic("menu").play(); //play the music

    }

    //Overriding the initUI from base
    @Override
    protected void initUI(){
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getFont();
        TextButton button = new TextButton("Play", textButtonStyle);

        float buttonWidth = button.getWidth();
        float buttonHeight = button.getHeight();
        button.remove(); // Remove the button as we only needed it to get the dimensions

        float x = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        float y = (Gdx.graphics.getHeight() - buttonHeight) / 2;

        addButton("Play", x,y,new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {

                //game.getSceneManager().setScreen(game.getSceneManager().getPlayScreen(true));
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
                game.getSoundManager().getMusic("menu").stop(); //stop the music
                System.out.println("play button clicked while extending base screen");
                //dispose();
            }
        });




    }



}
