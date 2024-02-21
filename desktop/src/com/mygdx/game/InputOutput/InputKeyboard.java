package com.mygdx.game.InputOutput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputKeyboard extends Input { // extends abstract Input class
	// handle keyboard inputs

    @Override
    public boolean keyPressed() {
        return true; // always true as key press events is handled by this class
    }

    @Override
    public boolean mousePressed() {
        return false; // mouse pressed events are not handled by this class
    }

    // check if specific keys are pressed
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
    
    // use gdx.input.iskeypressed to check 
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

    public boolean ifSpacePressed(){
        return Gdx.input.isKeyPressed(Keys.SPACE);
    }
    
}