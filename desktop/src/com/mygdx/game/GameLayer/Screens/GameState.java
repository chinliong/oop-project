package com.mygdx.game.GameLayer.Screens;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Vector2 playerPosition;
    private int playerScore;
    private int playerHealth;
    private Vector2 monsterPosition;
    private List<Vector2> recyclablePositions = new ArrayList<>();
    

    public GameState() {
        this.playerPosition = new Vector2();
    }

    public Vector2 getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Vector2 playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public Vector2 getMonsterPosition() {
        return monsterPosition;
    }

    public void setMonsterPosition(Vector2 monsterPosition) {
        this.monsterPosition = monsterPosition;
    }

    public List<Vector2> getRecyclablePositions() {
        return recyclablePositions;
    }

    public void setRecyclablePositions(List<Vector2> recyclablePositions) {
        this.recyclablePositions = recyclablePositions;
    }

    public void addRecyclablePosition(Vector2 position) {
        this.recyclablePositions.add(position);
    }
}
