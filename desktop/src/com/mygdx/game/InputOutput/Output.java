package com.mygdx.game.InputOutput;
//import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
public class Output {

    //onPress<keyboardKey> functions will return true when appropriate key on keyboard is pressed
    public boolean onPressEsc(int key, boolean properKey){
        if (key == Keys.ESCAPE && properKey){
            //System.out.println("Escape was pressed");
            return true;
        }else{
            //System.out.println("Escape was not pressed");
            return false;
        }
    }

    public boolean onPressLeft(int key, boolean properKey){
        if (key == Keys.LEFT && properKey){
            //System.out.println("Left was pressed");
            return true;
        }else{
            //System.out.println("Left was not pressed");
            return false;
        }
    }

    public boolean onPressRight(int key, boolean properKey){
        if (key == Keys.RIGHT && properKey){
            //System.out.println("Right was pressed");
            return true;
        }else{
            //System.out.println("Right was not pressed");
            return false;
        }
    }

    public boolean onPressW(int key, boolean properKey){
        if (key == Keys.W && properKey){
            //System.out.println("W was pressed");
            return true;
        }else{
            //System.out.println("W was not pressed");
            return false;
        }
    }

    //Currently unused onPressed functions, will shift up when needed
    public boolean onPressP(int key, boolean properKey){
        if (key == Keys.P && properKey){
            //System.out.println("P was pressed");
            return true;
        }else{
            //System.out.println("P was not pressed");
            return false;
        }
    }

    public boolean onPressSpaceBar(int key, boolean properKey){
        if (key == Keys.SPACE && properKey){
            //System.out.println("Space bar was pressed");
            return true;
        }else{
            //System.out.println("Space bar was not pressed");
            return false;
        }
    }

    public boolean onPressDown(int key, boolean properKey){
        if (key == Keys.DOWN && properKey){
            //System.out.println("Down was pressed");
            return true;
        }else{
            //System.out.println("Down was not pressed");
            return false;
        }
    }

    public boolean onPressUp(int key, boolean properKey){
        if (key == Keys.UP && properKey){
            //System.out.println("Up was pressed");
            return true;
        }else{
            //System.out.println("Up was not pressed");
            return false;
        }
    }

    public boolean onPressA(int key, boolean properKey){
        if (key == Keys.A && properKey){
            //System.out.println("A was pressed");
            return true;
        }else{
            //System.out.println("A was not pressed");
            return false;
        }
    }

    public boolean onPressS(int key, boolean properKey){
        if (key == Keys.S && properKey){
            //System.out.println("S was pressed");
            return true;
        }else{
            //System.out.println("S was not pressed");
            return false;
        }
    }

    public boolean onPressD(int key, boolean properKey){
        if (key == Keys.D && properKey){
            //System.out.println("D was pressed");
            return true;
        }else{
            //System.out.println("D was not pressed");
            return false;
        }
    }

    //onPress<mouseButton> functions that will return true when appropriate button on mouse is pressed else return false. States that which button is pressed in terminal
    public boolean onPressLMB(int button, boolean properButton){
        if (button == Buttons.LEFT && properButton){
            System.out.println("LMB was pressed");
            return true;
        }else{
            //System.out.println("LMB was not pressed");
            return false;
        }
    }

    public boolean onPressRMB(int button, boolean properButton){
        if (button == Buttons.RIGHT && properButton){
            System.out.println("RMB was pressed");
            return true;
        }else{
            //System.out.println("RMB was not pressed");
            return false;
        }
    }
}
