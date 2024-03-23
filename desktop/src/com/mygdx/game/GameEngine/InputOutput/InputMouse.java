package com.mygdx.game.GameEngine.InputOutput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;

public class InputMouse extends Input { // extends abstract Input class
	// handle mouse inputs

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
