package com.mygdx.game.GameLayer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.GameEngine.SimulationLifeCycleManager;
import com.mygdx.game.GameEngine.Screens.BaseScreen;



public class WinLoseScreen extends BaseScreen{
    private boolean win;
    private Sprite backgroundSprite;
    private Sprite outcomeSprite;
    
	private float menuGameButtonX, menuGameButtonY, menuGameButtonWidth = 160, menuGameButtonHeight = 60;
	private float exitGameButtonX, exitGameButtonY, exitGameButtonWidth = 160, exitGameButtonHeight = 60;


    public WinLoseScreen(SimulationLifeCycleManager game, boolean win) {
        super(game);
        this.win = win;
        initialiseUI();
        
        Texture backgroundTexture = new Texture(Gdx.files.internal("forest.jpg"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
		// Initialize button positions
        menuGameButtonX = (Gdx.graphics.getWidth() - menuGameButtonWidth) / 2 - 80;
        menuGameButtonY = (Gdx.graphics.getHeight() + menuGameButtonHeight) / 2 - 150;
		exitGameButtonX = (Gdx.graphics.getWidth() - exitGameButtonWidth) / 2 + 80;
		exitGameButtonY = (Gdx.graphics.getHeight() + exitGameButtonHeight) / 2 - 150; 
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
        
		handleMouseInput();
    	
    }
    
	private void handleMouseInput() {
		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

		if (game.getInputOutputManager().getInputMouse().ifLMBPressed()) {
			if (mouseX >= menuGameButtonX && mouseX <= menuGameButtonX + menuGameButtonWidth
					&& mouseY >= menuGameButtonY && mouseY <= menuGameButtonY + menuGameButtonHeight) {
				game.getSceneManager().setScreen(game.getSceneManager().getScreen(MainScreen.class));
			} else if (mouseX >= exitGameButtonX && mouseX <= exitGameButtonX + exitGameButtonWidth
					&& mouseY >= exitGameButtonY && mouseY <= exitGameButtonY + exitGameButtonHeight) {
				Gdx.app.exit();
			}
		}
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
