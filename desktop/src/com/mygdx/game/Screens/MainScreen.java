package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.mygdx.game.GameMaster;
import com.mygdx.game.SimulationLifeCycleManager;

public class MainScreen extends BaseScreen { 

    public MainScreen(SimulationLifeCycleManager game) {
        super(game);
        setBgColour(Color.TEAL);
        initialiseUI();
        }

    @Override
    public void show() {
        //set stage as input processor, set volume, play volume and loop it
        super.show();
       startAudio("MainMenu", 0.5f);
    }

    //Setup UI
    @Override
    protected void initialiseUI() {
        createImageButton("playBut.png", new ClickListener() {
        @Override
        public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
        // Handle the click event
        	game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
            game.getAudioManager().getMusic("MainMenu").stop();
                }
            }, 300, 200, 250, 200); //first 2 is size then position
        }
}




