package com.mygdx.game.Managers;
import com.mygdx.game.InputOutput.InputKeyboard;
import com.mygdx.game.InputOutput.InputMouse;
import com.mygdx.game.InputOutput.Output;

public class IOManager {
    private Output output; 
    private InputKeyboard inputKeyboard; 
    private InputMouse inputMouse; 
    
    
    public IOManager(){
        this.inputKeyboard = new InputKeyboard(); 
        this.inputMouse = new InputMouse(); 
        this.output = new Output(); 
        
    }

    public Output getOutput(){
        return this.output;
    }

    public InputKeyboard getInputKeyboard(){
        return this.inputKeyboard;
    }
 
    public InputMouse getInputMouse(){
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
