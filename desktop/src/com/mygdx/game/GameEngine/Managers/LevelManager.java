package com.mygdx.game.GameEngine.Managers;

import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameLayer.Screens.PlayScreen;
import com.mygdx.game.GameLayer.Screens.WinLoseScreen;

public class LevelManager {
    private SimulationLifeCycleManager game;
    private int currentLevel = 0;
    private static final int[] POINTS_TO_WIN_PER_LEVEL = {4, 6, 8}; // Example

    public LevelManager(SimulationLifeCycleManager game) {
        this.game = game;
    }

    public void nextLevel() {
        currentLevel++;
        if (currentLevel < POINTS_TO_WIN_PER_LEVEL.length) {
            game.getSceneManager().setScreen(new PlayScreen(game));
        } else {
            game.getSceneManager().setScreen(new WinLoseScreen(game, true)); // Assuming WinLoseScreen has a constructor that accepts a game instance and a win condition.
        }
    }

    public int getPointsToWin() {
        if (currentLevel >= POINTS_TO_WIN_PER_LEVEL.length) {
            return POINTS_TO_WIN_PER_LEVEL[POINTS_TO_WIN_PER_LEVEL.length - 1];
        }
        return POINTS_TO_WIN_PER_LEVEL[currentLevel];
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    // You might want to add more methods here depending on your game's needs
}