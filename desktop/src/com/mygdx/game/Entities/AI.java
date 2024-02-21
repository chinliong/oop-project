package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class AI extends Entity{

    private String AIObject;

    //private static final Map<String, Texture> enemyTextures = new HashMap<>();

    public AI() {
        //coordinates are randomly generated within the game screen
        super(TextureLoader.loadTexture("whale"), (int)(Math.random() * 800), (int)(Math.random() * 500));
        this.AIObject = "whale"; //default AIObject
    }

    public AI(String AIObject, int situationalRadius) {
        super(TextureLoader.loadTexture(AIObject), (int)(Math.random() * 800), (int)(Math.random() * 500));
        this.AIObject = AIObject;

        //System.out.println("Created AI object: " + AIObject);
        //System.out.print("Situational Radius: " + situationalRadius);
        //System.out.println(" X: " + this.getPosX() + " Y: " + this.getPosY());
    }

}
