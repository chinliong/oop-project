package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class AI extends Entity implements NonPlayable{

    private int awarenessRange;
    private String enemyType;

    //private static final Map<String, Texture> enemyTextures = new HashMap<>();

    public AI() {
        //coordinates are randomly generated within the game screen
        super(TextureLoader.loadTexture("whale"), (int)(Math.random() * 800), (int)(Math.random() * 500));
        this.awarenessRange = 100;
        this.enemyType = "guard"; //default enemy type


    }

    public AI(String enemyType, int awarenessRange) {
        super(TextureLoader.loadTexture(enemyType), (int)(Math.random() * 800), (int)(Math.random() * 500));
        this.awarenessRange = awarenessRange;
        this.enemyType = enemyType;


        System.out.println("Created Enemy type: " + enemyType);
        System.out.print("Awareness range: " + awarenessRange);
        System.out.println(" X: " + this.getPosX() + " Y: " + this.getPosY());
    }

    public int getAwarenessRange() {
        return awarenessRange;
    }

    public void setAwarenessRange(int awarenessRange) {
        this.awarenessRange = awarenessRange;
    }

    @Override
    public String getSubType() {
        return enemyType;
    }

    @Override
    public void setSubType(String subType) {
        this.enemyType = subType;
    }

}
