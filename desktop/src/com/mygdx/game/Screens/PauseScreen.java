package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameMaster;
//The pausescreen will have 3 buttons, calling the createButton() function, to go to play screen, main 
//screen of exit.

public class PauseScreen extends BaseScreen {

    public PauseScreen(GameMaster game) {
        super(game);
        initialiseUI();
    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    protected void initialiseUI() {
    	createButton("Resume", 100, 100, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
            	game.getAudioManager().getMusic("Gameplay").play();
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
                //dispose();
            }
        });

        createButton("Main Menu", 100, 200, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                //game.getEntityManager().disposeEntities();
                //game.getSimulationLifeCycleManager().disposeEntities(game.getEntityManager().getEntities());
                game.getSimulationLifeCycleManager().resetEntities(game.getEntityManager().getEntities());
                //remove the play screen from the list of screens
                game.getSceneManager().removeScreen(PlayScreen.class);
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(MainScreen.class));

            }
        });


        createButton("Exit", 100, 300, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

    }






}
