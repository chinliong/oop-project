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

    //Setup UI
    @Override
    protected void initialiseUI(){
    	//Create Button to get dimension
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getFont();
        TextButton button = new TextButton("Start", textButtonStyle);
        float buttonHeight = button.getHeight();
        float buttonWidth = button.getWidth();
        //Remove button after getting width and height
        button.remove(); 

        float x = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        float y = (Gdx.graphics.getHeight() - buttonHeight) / 2;

        //Create button for user to start game and go into playscreen
        createButton("Start", x,y,new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {         
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
                game.getAudioManager().getMusic("MainMenu").stop();
                System.out.println("Play button clicked");
          
            }
        });




    }



}
