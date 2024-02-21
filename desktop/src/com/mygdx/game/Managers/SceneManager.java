 package com.mygdx.game.Managers;

import com.badlogic.gdx.utils.reflect.ArrayReflection;
import com.mygdx.game.GameMaster;
import com.mygdx.game.Screens.*;
import com.badlogic.gdx.Screen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

// instances of screens should not be created instantly but instead be created when needed
// so i need to have a method that creates a new instance of a screen when needed
public class SceneManager {
    private List<Screen> screens;
    private GameMaster game; 


    public SceneManager(GameMaster game){
        this.game = game;
        this.screens = new ArrayList<>();
    }

    public void setScreen(){
        //check if screenlist is empty
        if (screens.isEmpty()) {
            screens.add(createScreen(MainScreen.class));
        }
        game.setScreen(screens.get(screens.size() - 1)); //set the last screen in the list
    }


    public void setScreen(Screen screen) {
     //   System.out.println("screen setting is now getting called by my sceneManager class instead");
        game.setScreen(screen);
    }



    public <T extends Screen> T getScreen(Class<T> type) {
        for (Screen screen : screens) {
            if (screen.getClass().equals(type)) {
                return (T) screen;
            }
        }
        try {
            T screen = type.cast(createScreen(type));

            //T screen = type.getConstructor(GameMaster.class).newInstance(game);
           // screens.add(screen); // Add the newly created screen to the list
            return screen; // Return the newly created screen
        } catch (Exception e) {
            throw new RuntimeException("Failed to create screen: " + type.getName(), e);
        }
    }

    //removeScreen method
    public void removeScreen(Class<? extends Screen> type) {
        for (Screen screen : screens) {
            if (screen.getClass().equals(type)) {
                screens.remove(screen);
                return;
            }
        }
    }

    public Screen createScreen(Class<? extends Screen> screenClass, Object... args) {
        try {
            Class<?>[] argTypes = new Class[args.length + 1];
            argTypes[0] = GameMaster.class;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Boolean) {
                    argTypes[i + 1] = boolean.class;
                } else {
                    argTypes[i + 1] = args[i].getClass();
                }
            }
            Constructor<? extends Screen> constructor = screenClass.getConstructor(argTypes);
            Object[] constructorArgs = new Object[args.length + 1];
            constructorArgs[0] = game;
            System.arraycopy(args, 0, constructorArgs, 1, args.length);
            Screen screen = constructor.newInstance(constructorArgs);
            screens.add(screen);
            return screen;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }



}
