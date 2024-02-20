package com.mygdx.game.Managers;
import com.mygdx.game.InputOutput.InputKeyboard;
import com.mygdx.game.InputOutput.InputMouse;
import com.mygdx.game.InputOutput.Output;

public class InputOutputManager {
    private Output output; //Declare object Output
    private InputKeyboard inputKeyboard; //Declare object InputKeyboard
    private InputMouse inputMouse; //Declare object InputMouse
    
    //Default constructor
    public InputOutputManager(){
        this.inputKeyboard = new InputKeyboard(); //initialise object InputKeyBoard
        this.inputMouse = new InputMouse(); //initialise object InputMouse
        this.output = new Output(); //initialise object Output
        
    }

    //Return the initialized output to use their functions    
    public Output getOutput(){
        return this.output;
    }

    //Return the initialized inputKeyboard to use their functions        
    public InputKeyboard getInputKeyboard(){
        return this.inputKeyboard;
    }

    //Return the initialized inputMouse to use their functions    
    public InputMouse getInputMouse(){
        return this.inputMouse;
    }
}
