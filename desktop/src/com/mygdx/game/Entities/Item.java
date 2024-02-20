package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Item extends Entity implements NonPlayable {
    private String itemType;
   // private static final Map<String, Texture> itemTextures = new HashMap<>();

    public Item() {
        super(TextureLoader.loadTexture("scythe"), (int)(Math.random() * 800), 0);
        this.itemType = "scythe";
    }

    public Item(String itemType) {
        super(TextureLoader.loadTexture(itemType), (int)(Math.random() * 800), 0);
        this.itemType = itemType;
    }

    @Override
    public String getSubType() {
        return itemType;
    }

    @Override
    public void setSubType(String subType) {
        this.itemType = subType;
    }



}
