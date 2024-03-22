package com.mygdx.game.GameEngine.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;

public class AudioManager {
	 private Sound soundEffect;
	    private HashMap<String, Music> mapMusic; 

	    public AudioManager(){
	    	mapMusic = new HashMap<>();
	    	// Assigning music files to hashmap , soundeffect to a Sound attribute
	    	mapMusic.put("Gameplay", Gdx.audio.newMusic(Gdx.files.internal("maple.mp3")));
	    	mapMusic.put("MainMenu", Gdx.audio.newMusic(Gdx.files.internal("supermario.mp3")));
	        soundEffect = Gdx.audio.newSound(Gdx.files.internal("boing.wav"));
	    }
	    
	    public Music getMusic(String mapKey){
	        return mapMusic.get(mapKey); // Get the music based on the key ("Gameplay" / "MainMenu")
	    }
	    
	    public void playSound()
	    {
	    	if(soundEffect != null)
	    	{
	        	soundEffect.play(0.5f);
	    	}
	    }
	    
	    public void dispose() {
	        // Dispose of all Music objects in the map
	        for (Music music : mapMusic.values()) {
	            if (music != null) music.dispose();
	        }
	        mapMusic.clear(); // Clear the map after dispose

	        // Dispose of the Sound effect
	        if (soundEffect != null) soundEffect.dispose();
	    }
}

