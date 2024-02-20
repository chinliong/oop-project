package com.mygdx.game.InputOutput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;

public class InputMouse extends Input {

    public boolean isLMBPressed(int input){
        if (mousePressed() && input == Buttons.LEFT) return true;
        else return false;
    }

    public boolean isRMBPressed(int input){
        if (mousePressed() && input == Buttons.RIGHT) return true;
        else return false;
    }

    //Function to check if a keyboard button was pressed. Since this is using a mouse, always return false
    @Override
    public boolean keyPressed() {
        //Not applicable for mouse
        return false;
    }

    //Function to check if a mouse button was pressed. Since this is using a mouse, always return true
    @Override
    public boolean mousePressed() {
        return true;
    }

    //if<Button>Pmouseressed() function will return true if specific mouse buttons are pressed, current way of getting which button was pressed since inputProcessor is not implemented
    public boolean ifLMBPressed(){
        return Gdx.input.isButtonJustPressed(Buttons.LEFT);
    }

    public boolean ifRMBPressed(){
        return Gdx.input.isButtonJustPressed(Buttons.RIGHT);
    }

    //get<mouseButton>Pressed() function will return which button on the mouse is pressed if it receives a boolean that is true, else it returns -1 which is not a button assigned.
    public int getLMBPressed(boolean correctButton){
        if (correctButton==true){
            return Buttons.LEFT;
        } 
        else return -1;
    }

    public int getRMBPressed(boolean correctButton){
        if (correctButton==true){
            return Buttons.RIGHT;
        } 
        else return -1;
    }
}
