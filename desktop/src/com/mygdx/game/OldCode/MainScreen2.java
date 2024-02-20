//package com.mygdx.game.OldCode;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.mygdx.game.GameMaster;
//
//public class MainScreen2 implements Screen {
//    private GameMaster game;
//    Texture texture;
//
//    private Stage stage;
//
//
//    public MainScreen2(GameMaster game) {
//        this.game = game; //this screen  should have access to my managers so i can switch to the play screen
//        texture = new Texture("badlogic.jpg");
//
//
//        //trying out stage
//        stage = new Stage(new ScreenViewport());
//        BitmapFont font = new BitmapFont();
//        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//        textButtonStyle.font = font;
//        TextButton playButton = new TextButton("Play", textButtonStyle);
//        playButton.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
//        playButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                game.getSceneManager().setScreen(game.getSceneManager().getPlayScreen());
//                System.out.println("play button clicked");
//                dispose();
//            }
//
//    });
//
//        TextButton closeButton = new TextButton("Close", textButtonStyle);
//        closeButton.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2 - 100);
//        closeButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                Gdx.app.exit();
//            }
//        });
//            stage.addActor(playButton);
//            stage.addActor(closeButton);
//        }
//
//
//    @Override
//    public void show() {
//         Gdx.input.setInputProcessor(stage); //this is to set the stage as the input processor
//    }
//
//    @Override
//    public void render(float delta) {
//        stage.act();
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//    }
//
//    @Override
//    public void pause() {
//    }
//
//    @Override
//    public void resume() {
//    }
//
//    @Override
//    public void hide() {
//    }
//
//    @Override
//    public void dispose() {
//    }
//}
