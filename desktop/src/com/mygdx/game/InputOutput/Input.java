package com.mygdx.game.InputOutput;

public abstract class Input { //Abstract class Input to allow for easier implementation of input devices in the future (i.e joystick)

    private boolean inputReceived;
    private int input;
    public Input(){
        this.inputReceived = false;
        this.input = -1;
    }
    public Input(boolean inputReceived, int input){
        this.inputReceived = inputReceived;
        this.input = input;
    }
    
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

    //abstract functions
    public abstract boolean keyPressed();
    public abstract boolean mousePressed();

}
