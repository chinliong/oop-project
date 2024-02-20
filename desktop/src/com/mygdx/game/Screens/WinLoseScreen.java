package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameMaster;

public class WinLoseScreen extends BaseScreen{
    private boolean win;

    public WinLoseScreen(GameMaster game, boolean win) {
        super(game);
        this.win = win;
        initUI();
    }

    @Override
    public void show() {
        super.show();

    }
    public void initUI() {
        if (win) {
            addText("You Win");
            
        } else {
            addText("You Lose");
        }


        addButton("Main Menu", 100, 100, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.getSceneManager().setScreen(game.getSceneManager().getScreen(MainScreen.class));
       
            }
        });

        addButton("Exit", 100, 200, new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }


    @Override
    public void hide() {
        //remove this screen from the list of screens
        game.getSceneManager().removeScreen(WinLoseScreen.class);
        System.out.println("Lose Screen is being removed through the hide function!");
    }
}
