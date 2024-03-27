package com.mygdx.game.GameLayer.InputOutput;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.GameEngine.InputOutput.*;

public class InputKeyboard extends Input { // extends abstract Input class
	// handle keyboard inputs
	
	// observer 
    private List<InputObserver> observers = new ArrayList<>();

    public void addObserver(InputObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(InputObserver observer) {
        observers.remove(observer);
    }

    public void notifyKeyEvent(KeyEvent event) {
        for (InputObserver observer : observers) {
            observer.onKeyEvent(event);
        }
    }

    // Method that gets called when a key event occurs
    public void onKeyPress(KeyEvent event) {
        notifyKeyEvent(event);
    }

    @Override
    public boolean keyPressed() {
        return true; // always true as key press events is handled by this class
    }

    @Override
    public boolean mousePressed() {
        return false; // mouse pressed events are not handled by this class
    }

    // check if specific keys are pressed
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
    
    // Game

    public boolean ifDPressed(){
        return Gdx.input.isKeyPressed(Keys.D);
    }
    public boolean ifAPressed(){
        return Gdx.input.isKeyPressed(Keys.A);
    }
    public boolean ifSPressed(){
        return Gdx.input.isKeyPressed(Keys.S);
    }
    public boolean ifWPressed(){
        return Gdx.input.isKeyPressed(Keys.W);
    }
    
    public boolean ifEnterPressed() {
    	return Gdx.input.isKeyJustPressed(Keys.ENTER);
    }
    
}