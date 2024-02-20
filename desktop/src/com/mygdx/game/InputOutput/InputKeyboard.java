package com.mygdx.game.InputOutput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputKeyboard extends Input {

    // check if a keyboard button was pressed, since this is using a keyboard, always return true
    @Override
    public boolean keyPressed() {
        return true;
    }

    // check if a mouse button was pressed, since this is using a keyboard, always return false
    @Override
    public boolean mousePressed() {
        return false;
    }

    
    public boolean isWPressed(int input){
        if (keyPressed() && input == Keys.W) return true;
        else return false;
    }

    public boolean isLeftPressed(int input){
        if (keyPressed() && input == Keys.LEFT) return true;
        else return false;
    }

    public boolean isRightPressed(int input){
        if (keyPressed() && input == Keys.RIGHT) return true;
        else return false;
    }

    public boolean isEscPressed(int input){
        if (keyPressed() && input == Keys.ESCAPE) return true;
        else return false;
    }

    //if<keyboardButton>Pressed() function will return true if specific keyboard buttons are pressed, 
    // current way of getting which key was pressed since inputProcessor is not implemented
    public boolean ifLeftPressed(){
        return Gdx.input.isKeyPressed(Keys.LEFT);
    }

    public boolean ifRightPressed(){
        return Gdx.input.isKeyPressed(Keys.RIGHT);
    }

    public boolean ifWPressed(){
        return Gdx.input.isKeyPressed(Keys.W);
    }

    public boolean ifEscPressed(){
        return Gdx.input.isKeyPressed(Keys.ESCAPE);
    }

    public boolean ifUpPressed(){
        return Gdx.input.isKeyPressed(Keys.UP);
    }

    public boolean ifDownPressed(){
        return Gdx.input.isKeyPressed(Keys.DOWN);
    }

    public boolean ifAPressed(){
        return Gdx.input.isKeyPressed(Keys.A);
    }

    public boolean ifSPressed(){
        return Gdx.input.isKeyPressed(Keys.S);
    }

    public boolean ifDPressed(){
        return Gdx.input.isKeyPressed(Keys.D);
    }

    public boolean ifSpacePressed(){
        return Gdx.input.isKeyPressed(Keys.SPACE);
    }
    
}