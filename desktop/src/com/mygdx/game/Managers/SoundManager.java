package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.HashMap;

public class SoundManager {
    private Music music;
    private HashMap<String, Music> musicMap; // using a hashmap to map the sounds to a string
    private float volume = 0.5f; // dafault volume will be at 50%
    private boolean isMuted = false; //check muted state

    public SoundManager(){
        //music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        musicMap = new HashMap<>();
        musicMap.put("game", Gdx.audio.newMusic(Gdx.files.internal("music.mp3")));
        musicMap.put("menu", Gdx.audio.newMusic(Gdx.files.internal("loadingMusic.mp3")));
    }

    public Music getMusic(){
        return musicMap.get("game"); // get the game music
    }
    public Music getMusic(String key){
        return musicMap.get(key); // get the music based on the key
    }

    public void increaseVolume() {
        volume = Math.min(1.0f, volume + 0.1f); // increase by 10%, can change up to preference(?)
        updateVolume();
    }

    public void decreaseVolume() {
        volume = Math.max(0.0f, volume - 0.1f); // decrease by 10%, can change up to preference(?)
        updateVolume();
    }

    private void updateVolume() {
        float currentVolume = isMuted ? 0.0f : volume;
        for (Music music : musicMap.values()) {// this updates to all sounds in the game
            music.setVolume(currentVolume);
        }
    }

    public void setVolume(float volume) {
        this.volume = Math.max(0.0f, Math.min(1.0f, volume)); //this setting default sound volume
        updateVolume();
    }

    public void toggleMute() {
        if (isMuted) {
            // if currently muted, unmute by restoring volume
            isMuted = false;
            updateVolume();
        } else {
            // if currently unmuted, mute by setting volume to 0
            isMuted = true;
            muteVolume();
        }
    }

    private void muteVolume() {
        for (Music music : musicMap.values()) {
            music.setVolume(0.0f);
        }
    }


}
