package com.mygdx.game.InputOutput;

public abstract class Input { // abstract to allow easier implementation of input devices in the future (eg. joystick)

    private boolean inputReceived;
    private int input;
    
    // default constructor 
    public Input(){
        this.inputReceived = false; // as no input received initially
        this.input = -1;
    }
    
    // parameterized constructor 
    public Input(boolean inputReceived, int input){
        this.inputReceived = inputReceived;
        this.input = input;
    }
    
    // getters and setters
    public int getInput(){
        return this.input;
    }

    public void setInput(int new_input){
        this.input = new_input;
    }

    public boolean getInputReceived(){
        return this.inputReceived;
    }

    public void setInputReceived(boolean new_inputReceived){
        this.inputReceived = new_inputReceived;
    }
    
    // abstract methods
    public abstract boolean keyPressed();
    public abstract boolean mousePressed();
    
    

}
