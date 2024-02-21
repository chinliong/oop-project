package com.mygdx.game.InputOutput;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

public class Output { 
	// handles output based on specific input events

	public boolean onPressEsc(int key, boolean properKey) {
		if (key == Keys.ESCAPE && properKey) {
			return true;
		} else {
			return false;
		}
	}

	public boolean onPressLeft(int key, boolean properKey) {
		if (key == Keys.LEFT && properKey) {
			return true;
		} else {
			return false;
		}
	}

	public boolean onPressRight(int key, boolean properKey) {
		if (key == Keys.RIGHT && properKey) {
			return true;
		} else {
			return false;
		}
	}

	public boolean onPressDown(int key, boolean properKey) {
		if (key == Keys.DOWN && properKey) {
			return true;
		} else {
			return false;
		}
	}

	public boolean onPressUp(int key, boolean properKey) {
		if (key == Keys.UP && properKey) {
			return true;
		} else {
			return false;
		}
	}

	public boolean onPressLMB(int button, boolean properButton) {
		if (button == Buttons.LEFT && properButton) {
			System.out.println("left mouse button was pressed");
			return true;
		} else {
			return false;
		}
	}

	public boolean onPressRMB(int button, boolean properButton) {
		if (button == Buttons.RIGHT && properButton) {
			System.out.println("right mouse button was pressed");
			return true;
		} else {
			return false;
		}
	}
}
