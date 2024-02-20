package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameMaster;

public class PauseScreen extends BaseScreen {
    // i will have 3 buttons here, go back to play screen, go to main screen, exit,
    // dispose will call the enttiy manager's dispose to dispose the entities


    public PauseScreen(GameMaster game) {
        super(game);
        initUI();
    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    protected void initUI() {
        addButton("Resume", 100, 100, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
                //dispose();
            }
        });

        addButton("Main Menu", 100, 200, new ClickListener() {
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


        addButton("Exit", 100, 300, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        addButton("Volume +", 500, 100, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.getSoundManager().increaseVolume();
            }
        });

        addButton("Volume -", 500, 200, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.getSoundManager().decreaseVolume();
            }
        });

        addButton("Mute/Unmute", 500, 300, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.getSoundManager().toggleMute();
            }
        });

    }






}
