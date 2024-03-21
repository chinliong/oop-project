package com.mygdx.game.GameLayer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Screens.BaseScreen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;


public class WinLoseScreen extends BaseScreen{
    private boolean win;
    private Sprite backgroundSprite;
    private Sprite outcomeSprite;

    public WinLoseScreen(SimulationLifeCycleManager game, boolean win) {
        super(game);
        this.win = win;
        initialiseUI();
        
        Texture backgroundTexture = new Texture(Gdx.files.internal("forest.jpg"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        super.show();

    }
    
    @Override
    public void render(float delta) {
    	super.render(delta);
    	
    	SpriteBatch batch = game.getBatch();
        batch.begin();
        backgroundSprite.draw(batch);
        if (outcomeSprite != null) {
            outcomeSprite.draw(batch);
        }
        batch.end();
        
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    	
    }
    
    public void initialiseUI() {
    	
    	Texture outcomeTexture;
        if (win) {
        	outcomeTexture = new Texture(Gdx.files.internal("win.png"));
            
        } else {
        	outcomeTexture = new Texture(Gdx.files.internal("lose.png"));

        }
        outcomeSprite = new Sprite(outcomeTexture);        
        
        Texture menuTexture = new Texture(Gdx.files.internal("menuButton.png"));
        Texture exitGameTexture = new Texture(Gdx.files.internal("backbutton.png"));
        
        Drawable menuDrawable = new TextureRegionDrawable(new TextureRegion(menuTexture));
    	Drawable menuHoverDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("menubuttonhover.png"))));
    	Drawable exitGameDrawable = new TextureRegionDrawable(new TextureRegion(exitGameTexture));
    	Drawable exitGameHoverDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("backbuttonhover.png"))));
    	
    	TextButton menuButton = new TextButton("", new TextButton.TextButtonStyle(menuDrawable, menuDrawable, menuDrawable, game.getFont()));
    	TextButton exitGameButton = new TextButton("", new TextButton.TextButtonStyle(exitGameDrawable, exitGameDrawable, exitGameDrawable, game.getFont()));
    	
    	menuButton.setSize( 160, 60);
    	exitGameButton.setSize( 160, 60);
    	
    	menuButton.setPosition((Gdx.graphics.getWidth() - menuButton.getWidth()) / 2 - 80, (Gdx.graphics.getHeight() + menuButton.getHeight()) / 2 - 150);
    	exitGameButton.setPosition((Gdx.graphics.getWidth() - exitGameButton.getWidth()) / 2 + 80, (Gdx.graphics.getHeight() + exitGameButton.getHeight()) / 2 - 150);
    	
    	menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	game.getSceneManager().setScreen(game.getSceneManager().getScreen(MainScreen.class));
            }
        });
    	
    	exitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	Gdx.app.exit();
            }
        });
    	
    	menuButton.getStyle().over = menuHoverDrawable;
    	exitGameButton.getStyle().over = exitGameHoverDrawable;

        stage.addActor(menuButton);
        stage.addActor(exitGameButton);
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        
        outcomeSprite.setSize(width / 2, height / 2); 
        outcomeSprite.setPosition((width - outcomeSprite.getWidth()) / 2, (height - outcomeSprite.getHeight()) / 2 + 100);

        backgroundSprite.setSize(width, height);
        stage.getViewport().update(width, height, true);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    }


    @Override
    public void hide() {
        //remove this screen from the list of screens
        game.getSceneManager().removeScreen(WinLoseScreen.class);
    }
}
