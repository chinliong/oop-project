package com.mygdx.game.GameLayer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Screens.BaseScreen;

public class PauseScreen extends BaseScreen {

    public PauseScreen(SimulationLifeCycleManager game) {
        super(game);
        setBgColour(Color.TEAL);
        initialiseUI();
    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    protected void initialiseUI() {
//    	createButton("Resume", 100, 100, new ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//            	game.getAudioManager().getMusic("Gameplay").play();
//                game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
//            }
//        });
//
//        createButton("Main Menu", 100, 200, new ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                game.getEntityManager().disposeEntities();
//                //remove the play screen from the list of screens
//                game.getSceneManager().removeScreen(PlayScreen.class);
//                game.getSceneManager().setScreen(game.getSceneManager().getScreen(MainScreen.class));
//
//            }
//        });
//
//
//        createButton("Exit", 100, 300, new ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                Gdx.app.exit();
//            }
//        });

    }






}
