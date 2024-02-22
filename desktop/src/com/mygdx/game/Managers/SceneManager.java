 package com.mygdx.game.Managers;

import com.mygdx.game.GameMaster;
import com.mygdx.game.Screens.*;
import com.badlogic.gdx.Screen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

//Manages the creation and lifecycle of game screens to ensure they are instantiated when needed
public class SceneManager {
    private List<Screen> screenList; // List to keep track of instantiated screens
    private GameMaster game; 

    // Constructor initializes the scene manager with a reference to the GameMaster
    public SceneManager(GameMaster game){
        this.game = game;
        this.screenList = new ArrayList<>();
    }
    
    // Sets the last screen in the list as the current screen, creating the MainScreen if no screens exist
    public void setScreen(){
    	 // If no screens have been created yet, create and add the MainScreen
        if (screenList.isEmpty()) {
        	screenList.add(createScreen(MainScreen.class));
        }
        game.setScreen(screenList.get(screenList.size() - 1)); // Set the game's current screen to the last one in the list
    }

    // Overloaded method to directly set a specific screen as the current one
    public void setScreen(Screen screen) {
    	// Directly set the provided screen
        game.setScreen(screen);
    }


    // Retrieves an existing screen of the specified type, or creates a new one if it does not exist
    public <T extends Screen> T getScreen(Class<T> type) {
        for (Screen screen : screenList) {
            if (screen.getClass().equals(type)) {
                return (T) screen;
            }
        }
        // If the screen does not exist, create a new instance of it
        try {
            T screen = type.cast(createScreen(type));
            return screen; // Return the newly created screen
        } catch (Exception e) {
            throw new RuntimeException("Failed to create screen: " + type.getName(), e);
        }
    }

    // Removes a screen of the specified type from the list
    public void removeScreen(Class<? extends Screen> type) {
        for (Screen screen : screenList) {
            if (screen.getClass().equals(type)) {
            	screenList.remove(screen);
                return;
            }
        }
    }
    
    // Creates a new screen instance of the specified class, using reflection to invoke the constructor with arguments
    public Screen createScreen(Class<? extends Screen> screenClass, Object... args) {
        try {
        	// Prepare constructor argument types, including GameMaster and any additional arguments
            Class<?>[] argTypes = new Class[args.length + 1];
            argTypes[0] = GameMaster.class;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Boolean) {
                    argTypes[i + 1] = boolean.class;
                } else {
                    argTypes[i + 1] = args[i].getClass();
                }
            }
            // Locate the constructor and create a new screen instance with the provided arguments
            Constructor<? extends Screen> constructor = screenClass.getConstructor(argTypes);
            Object[] constructorArgs = new Object[args.length + 1];
            constructorArgs[0] = game;
            System.arraycopy(args, 0, constructorArgs, 1, args.length);
            Screen screen = constructor.newInstance(constructorArgs);
            screenList.add(screen); // Add the new screen to the list
            return screen; // Return the newly created screen for immediate use
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null; // Return null if screen creation fails
        }
    }
    
    // Screen transition logic with the ability to pass additional arguments for specific screens
    public void transitionToScreen(Class<? extends BaseScreen> screenClass, Object... args) {
        // Special handling for transitioning to WinLoseScreen with arguments to indicate win/loss
        if (screenClass.equals(WinLoseScreen.class)) {
            if (args.length > 0) {
                boolean win = (boolean) args[0]; // Determine win or loss based on argument
                // Transition to the screen with the win/loss state
                this.game.getSceneManager().setScreen(this.game.getSceneManager().createScreen(screenClass, win));
//                resetEntities(this.game.getEntityManager().getEntities()); // Reset entities for new game state
                game.getAudioManager().getMusic("Gameplay").stop(); // Stop current game music
            }
            else {
                // Default to loss if no arguments provided, indicating an error or oversight
                this.game.getSceneManager().setScreen(this.game.getSceneManager().createScreen(screenClass, false));
//                resetEntities(this.game.getEntityManager().getEntities());
                game.getAudioManager().getMusic("Gameplay").stop();
            }
        }
    }


}
