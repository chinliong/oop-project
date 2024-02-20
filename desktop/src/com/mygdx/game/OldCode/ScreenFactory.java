//package com.mygdx.game.Factories;
//
//import com.badlogic.gdx.Screen;
//import com.mygdx.game.GameMaster;
//import com.mygdx.game.Screens.MainScreen;
//import com.mygdx.game.Screens.PauseScreen;
//import com.mygdx.game.Screens.PlayScreen;
//
//public class ScreenFactory {
//    //have the gamemaster and returns the screens
//    private GameMaster game;
//    public ScreenFactory(GameMaster game) {
//        this.game = game;
//
//    }
//    public MainScreen createMainScreen() {
//        return new MainScreen(game);
//    }
//    public MainScreen createLoseScreen() {
//        return new MainScreen(game);
//    }
//    public MainScreen createLoadingScreen() {
//        return new MainScreen(game);
//    }
//    public PauseScreen createPauseScreen() {
//        return new PauseScreen(game);
//    }
//    public MainScreen createNextStageScreen() {
//        return new MainScreen(game);
//    }
//    public PlayScreen createPlayScreen() {
//        return new PlayScreen(game);
//    }
//
//    public Screen createScreen(Class<? extends Screen> screenClass) {
//        if (screenClass.equals(MainScreen.class)) {
//            return createMainScreen();
//        } else if (screenClass.equals(PauseScreen.class)) {
//            return createPauseScreen();
//        } else if (screenClass.equals(PlayScreen.class)) {
//            return createPlayScreen();
//        } else {
//            return null;
//        }
//    }
//
//}
