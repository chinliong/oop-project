package com.mygdx.game.Entities;


import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Player extends CollidableEntity {
	 private List<CollidableEntity> pickedupEntities = new ArrayList<>();

    public Player() {
    	// Entity constructor 1
        super("fish.png", 0, 10);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(super.getPosX(), super.getPosY(), super.getWidth(), super.getHeight());
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    
    @Override
    public boolean hasCollided(CollidableEntity cEntity, int range)
    {
  		return Math.abs(this.getPosX() - cEntity.getPosX()) < range && Math.abs(this.getPosY()- cEntity.getPosY()) < range;
    }
    
   // Method to attach a CollidableEntity to the player
    public void attachEntity(CollidableEntity entity) {
        this.pickedupEntities.add(entity);
    }
    
    public List<CollidableEntity> getPickedUpEntities() {
        return new ArrayList<>(pickedupEntities); // Returns a copy to protect the original list
    }

    // Method to update the positions of attached entities
    public void updateAttachedEntities() {
        for (CollidableEntity entity : pickedupEntities) {
            entity.setPosX(this.getPosX());
            entity.setPosY(this.getPosY());
        }
    }
    
 // Method to simulate recycling an item into a bin
    public boolean recycleItem(AI bin) {
        // Assuming the last item picked up is the one to be recycled
        if (!pickedupEntities.isEmpty()) {
            CollidableEntity lastItem = pickedupEntities.get(pickedupEntities.size() - 1);
            if (lastItem instanceof AI) {
                AI item = (AI) lastItem;
                // Check if the item can be recycled in the bin
                if (isCorrectBinForItem(item, bin)) {
                    // Remove the item from picked up items
                    pickedupEntities.remove(item);
                    // Additional logic for successful recycling, such as playing sound or incrementing score
                    return true; // Recycling successful
                }
            }
        }
        return false; // Recycling unsuccessful
    }

    // Helper method to determine if an item can be recycled in a bin
    private boolean isCorrectBinForItem(AI item, AI bin) {
        // Implement the logic based on item and bin types
        String itemType = item.getAIObjectName(); // Assuming this method returns a string identifier
        String binType = bin.getAIObjectName(); // for the type of item/bin

        switch (binType) {
            case "plasticbin.png":
                return "bottle.png".equals(itemType);
            case "paperbin.png":
                return "paper.png".equals(itemType);
            case "canbin.png":
                return "can.png".equals(itemType);
            case "glassbin.png":
                return "glass.png".equals(itemType);
            default:
                return false; // The item does not match the bin
        }
    }

}
