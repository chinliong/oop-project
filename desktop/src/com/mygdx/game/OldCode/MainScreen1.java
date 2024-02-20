//package com.mygdx.game.OldCode;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Texture;
//import com.mygdx.game.GameMaster;
//
//public class MainScreen1 implements Screen {
//    private GameMaster game;
//    Texture texture;
//    public MainScreen1(GameMaster game) {
//        this.game = game; //this screen  should have access to my managers so i can switch to the play screen
//        texture = new Texture("badlogic.jpg");
//    }
//
//    @Override
//    public void show() {
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(1, 0, 0, 1); // more controlled version than ScreenUtils.clear
//        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
//        game.batch.begin();
//        game.batch.draw(texture, 0, 0);
//        game.batch.end();
//
//        //when clicked on the image it will change to the play screen
//        if (Gdx.input.isTouched()){
//            int x = Gdx.input.getX();
//            int y = Gdx.graphics.getHeight() - Gdx.input.getY(); //this is to invert the y axis
//
//            //bounadaries of the image
//            int TextureWidth = texture.getWidth();
//            int TextureHeight = texture.getHeight();
//            System.out.println("texture width " + TextureWidth + " texture height " + TextureHeight);
//            //range would be from 0 to the width and height of the texture
//
//            // if x and y equals to the texture width and height
//            if (x >=0 && x <= TextureWidth && y >=0 && y <= TextureHeight){ //if the image is clicked (can be a collision function)
//
//                game.getSceneManager().setScreen(game.getSceneManager().getScreen(PlayScreen.class));
//                dispose();
//            }else{
//                System.out.println("image is not clicked, you clicked on x " + x + " and y " + y); //this is for debugging
//            }
//
//
//            //why is my y inverted ? the y is inverted because the origin of the screen is at the top left corner
//            ///drawing the image from the bottom left corner
//        }
//
//
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
