package com.mygdx.game.GameEngine;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Lab P8- Team 5");
		config.setWindowedMode(800, 600); // set dimensions of windowed
		new Lwjgl3Application(new SimulationLifeCycleManager(), config);
	}
}
