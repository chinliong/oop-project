package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;

public class AudioManager {
	 private Sound soundEffect;
	    private HashMap<String, Music> musicMap; // using a hashmap to map the sounds to a string

	    public AudioManager(){
	        musicMap = new HashMap<>();
	        musicMap.put("Gameplay", Gdx.audio.newMusic(Gdx.files.internal("maple.mp3")));
	        musicMap.put("MainMenu", Gdx.audio.newMusic(Gdx.files.internal("supermario.mp3")));
	        soundEffect = Gdx.audio.newSound(Gdx.files.internal("boing.wav"));
	    }

	    public Music getMusic(String mapKey){
	        return musicMap.get(mapKey); // get the music based on the key ("Gameplay" / "MainMenu")
	    }

	    public void playSound()
	    {
	    	if(soundEffect != null)
	    	{
	        	soundEffect.play(0.5f);
	    	}
	    }
}
