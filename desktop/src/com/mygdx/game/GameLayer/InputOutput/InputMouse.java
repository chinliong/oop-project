package com.mygdx.game.GameLayer.InputOutput;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.mygdx.game.GameEngine.InputOutput.*;

public class InputMouse extends Input { // extends abstract Input class
	// handle mouse inputs
	
	// observer 
    private List<InputObserver> observers = new ArrayList<>();

    public void addObserver(InputObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(InputObserver observer) {
        observers.remove(observer);
    }

    public void notifyMouseEvent(MouseEvent event) {
        for (InputObserver observer : observers) {
            observer.onMouseEvent(event);
        }
    }

    // Method that gets called when a key event occurs
    public void onKeyPress(MouseEvent event) {
    	notifyMouseEvent(event);
    }

	public boolean isLMBPressed(int input) {
		if (mousePressed() && input == Buttons.LEFT)
			return true;
		else
			return false;
	}

	public boolean isRMBPressed(int input) {
		if (mousePressed() && input == Buttons.RIGHT)
			return true;
		else
			return false;
	}

	@Override
	public boolean keyPressed() {
		return false; // key press events are not handled by this class
	}


	@Override
	public boolean mousePressed() {
		return true; // always true as mouse press events is handled by this class
	}

	// check if pressed
	public boolean ifLMBPressed() {
		return Gdx.input.isButtonJustPressed(Buttons.LEFT);
	}

	public boolean ifRMBPressed() {
		return Gdx.input.isButtonJustPressed(Buttons.RIGHT);
	}

	public int getLMBPressed(boolean correctButton) {
		if (correctButton == true) {
			return Buttons.LEFT; // return code for left mouse button 
		} else
			return -1; // no valid button press
	}

	public int getRMBPressed(boolean correctButton) {
		if (correctButton == true) {
			return Buttons.RIGHT; // return code for left mouse button 
		} else
			return -1; // no valid button press
	}
	
	//Get mouse coords
	public int getposXPressed() {
		return Gdx.input.getX();
		}
			
	public int getposYPressed() {
		return Gdx.input.getY();
		}
}

