package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends  Entity {
    private int playerID;
    private String playerName;
    private int playerScore;
    private int numberofLives;

    private Sprite playerSprite;
    public Player() {
        super(new Texture("wizard.png"), 0, 10);
        playerName = "Player 1";
        playerScore = 0;
        numberofLives = 3;
        
        this.playerSprite = new Sprite(getEntityImage());
        this.playerSprite.setPosition(getPosX(), getPosY());
        this.playerSprite.setSize(100, 100);

    }
    // Method to get the bounding rectangle of the player sprite for collision detection
    public Rectangle getBounds() {
        return playerSprite.getBoundingRectangle();
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getNumberofLives() {
        return numberofLives;
    }

    public void setNumberofLives(int numberofLives) {
        this.numberofLives = numberofLives;
    }

    public void testmove(){
        //left right arrow to move player left and right
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) setPosX(getPosX() - (int) (200 * Gdx.graphics.getDeltaTime()));
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) setPosX(getPosX() + (int) (200 * Gdx.graphics.getDeltaTime()));
    }


}
