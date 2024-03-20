package com.mygdx.game.GameLayer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Screens.BaseScreen;

public class WinLoseScreen extends BaseScreen{
    private boolean win;

    public WinLoseScreen(SimulationLifeCycleManager game, boolean win) {
        super(game);
        this.win = win;
        initialiseUI();
    }

    @Override
    public void show() {
        super.show();

    }
    public void initialiseUI() {
        if (win) {
        	createText("WinLoseScreen - YOU WIN");
            setBgColour(Color.TEAL);
            
        } else {
        	createText("WinLoseScreen - YOU LOSE");
            setBgColour(Color.RED);

        }


        createButton("Main Menu", 100, 100, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(MainScreen.class));
       
            }
        });

        createButton("Exit", 200, 100, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
            	//Clear saved data
            	Preferences prefs = Gdx.app.getPreferences("MyGamePrefs");
            	prefs.clear();
            	prefs.flush();
                Gdx.app.exit();
            }
        });
    }


    @Override
    public void hide() {
        //remove this screen from the list of screens
        game.getSceneManager().removeScreen(WinLoseScreen.class);
    }
}
