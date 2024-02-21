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

public class MainScreen extends BaseScreen { 

    public MainScreen(GameMaster game) {
        super(game);
        initialiseUI();

        }


    @Override
    public void show() {
        //set stage as input processor, set volume, play volume and loop it
        super.show();
        game.getAudioManager().getMusic("MainMenu").setVolume(0.5f); 
        game.getAudioManager().getMusic("MainMenu").play(); 
        game.getAudioManager().getMusic("MainMenu").isLooping();
    }

    //Overriding the initUI from base
    @Override
    protected void initialiseUI(){
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getFont();
        TextButton button = new TextButton("Play", textButtonStyle);

        float buttonWidth = button.getWidth();
        float buttonHeight = button.getHeight();
        button.remove(); // Remove the button as we only needed it to get the dimensions

        float x = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        float y = (Gdx.graphics.getHeight() - buttonHeight) / 2;

        createButton("Play", x,y,new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {

                //game.getSceneManager().setScreen(game.getSceneManager().getPlayScreen(true));
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
                game.getAudioManager().getMusic("MainMenu").stop(); //stop the music
                System.out.println("play button clicked while extending base screen");
                //dispose();
            }
        });




    }



}
