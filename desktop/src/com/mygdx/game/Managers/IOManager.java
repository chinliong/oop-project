package com.mygdx.game.Managers;
import com.mygdx.game.InputOutput.InputKeyboard;
import com.mygdx.game.InputOutput.InputMouse;
import com.mygdx.game.InputOutput.Output;

//Manages input and output for the game, encapsulating keyboard and mouse input as well as game output
public class IOManager {
    private Output output; // Handles game output, potentially for displaying information or results
    private InputKeyboard inputKeyboard; // Manages keyboard input, capturing and processing key presses
    private InputMouse inputMouse; // Manages mouse input, capturing and processing mouse clicks and movements
    
    // Constructor initializes input and output handlers
    public IOManager(){
    	this.inputKeyboard = new InputKeyboard(); // Initialize keyboard input handler
        this.inputMouse = new InputMouse(); // Initialize mouse input handler
        this.output = new Output(); // Initialize output handler
        
    }
    
    // Returns the current Output handler instance
    public Output getOutput(){
        return this.output;
    }

    // Returns the current InputKeyboard handler instance
    public InputKeyboard getInputKeyboard(){
        return this.inputKeyboard;
    }
 
    // Returns the current InputMouse handler instance
    public InputMouse getInputMouse(){
        return this.inputMouse;
    }

    // Sets a new Output handler instance
	public void setOutput(Output output) {
		this.output = output;
	}

	// Sets a new InputKeyboard handler instance
	public void setInputKeyboard(InputKeyboard inputKeyboard) {
		this.inputKeyboard = inputKeyboard;
	}

	// Sets a new InputMouse handler instance
	public void setInputMouse(InputMouse inputMouse) {
		this.inputMouse = inputMouse;
	}
    
    
}
