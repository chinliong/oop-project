package com.mygdx.game.GameEngine.Managers;

import com.mygdx.game.GameEngine.InputOutput.InputKeyboard;
import com.mygdx.game.GameEngine.InputOutput.InputMouse;
import com.mygdx.game.GameEngine.InputOutput.Output;

public class IOManager {
	private Output output;
	private InputKeyboard inputKeyboard;
	private InputMouse inputMouse;
	
	// Default constructor, initializes new instance
	public IOManager() { 
		this.inputKeyboard = new InputKeyboard();
		this.inputMouse = new InputMouse();
		this.output = new Output();

	}

	// Getters and setters
	public Output getOutput() {
		return this.output;
	}

	public InputKeyboard getInputKeyboard() {
		return this.inputKeyboard;
	}

	public InputMouse getInputMouse() {
		return this.inputMouse;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public void setInputKeyboard(InputKeyboard inputKeyboard) {
		this.inputKeyboard = inputKeyboard;
	}

	public void setInputMouse(InputMouse inputMouse) {
		this.inputMouse = inputMouse;
	}

}
