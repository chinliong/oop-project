package com.mygdx.game.GameEngine.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;

public class AudioManager {
	private HashMap<String, Music> mapMusic;

	// sound map
	private HashMap<String, Sound> mapSound;

	public AudioManager() {
		mapMusic = new HashMap<>();
		// Assigning music files to hashmap , soundeffect to a Sound attribute
		mapMusic.put("Gameplay", Gdx.audio.newMusic(Gdx.files.internal("maple.mp3")));
		mapMusic.put("MainMenu", Gdx.audio.newMusic(Gdx.files.internal("supermario.mp3")));

		mapSound = new HashMap<>();
		mapSound.put("Hit", Gdx.audio.newSound(Gdx.files.internal("boing.wav")));
		mapSound.put("correct", Gdx.audio.newSound(Gdx.files.internal("correct.wav")));
		mapSound.put("wrong", Gdx.audio.newSound(Gdx.files.internal("wrong.wav")));
		mapSound.put("lose", Gdx.audio.newSound(Gdx.files.internal("lose.wav")));
		mapSound.put("win", Gdx.audio.newSound(Gdx.files.internal("win.wav")));
		mapSound.put("nextLevel", Gdx.audio.newSound(Gdx.files.internal("next-level.wav")));

	}

	// Get the music based on the key ("Gameplay" / "MainMenu")
	public Music getMusic(String mapKey) {
		return mapMusic.get(mapKey);
	}

	public Sound getSound(String mapKey) {
		return mapSound.get(mapKey);
	}

	public void dispose() {
		// Dispose of all Music objects in the map
		for (Music music : mapMusic.values()) {
			if (music != null)
				music.dispose();
		}
		mapMusic.clear(); // Clear the map after dispose

		// Dispose of the Sound effect
		for (Sound sound : mapSound.values()) {
			if (sound != null)
				sound.dispose();
		}
		mapSound.clear(); // Clear the map after dispose
	}
}
