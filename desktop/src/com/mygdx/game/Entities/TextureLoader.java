package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureLoader {
    public static final Map< String, Texture> characterTextures = new HashMap<>();


    public static Texture loadTexture(String textureName) {
        Texture texture = new Texture(textureName + ".png");
        characterTextures.put(textureName, texture);
        return texture;
    }
}
