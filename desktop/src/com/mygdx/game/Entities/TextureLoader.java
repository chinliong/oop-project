package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureLoader {
    public static final Map< String, Texture> nonPlayableTextures = new HashMap<>();


    public static Texture loadTexture(String textureName) {
        if (nonPlayableTextures.containsKey(textureName)) {
            return nonPlayableTextures.get(textureName);
        }
        Texture texture = new Texture(textureName + ".png");
        nonPlayableTextures.put(textureName, texture);
        return texture;
    }
}
