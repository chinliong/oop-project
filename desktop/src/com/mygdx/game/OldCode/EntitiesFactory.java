//package com.mygdx.game.Factories;
//
//import com.mygdx.game.Entities.AI;
//import com.mygdx.game.Entities.Item;
//import com.mygdx.game.Entities.Player;
//import com.mygdx.game.Entities.iEntity;
//
////this in charge of creating all the entities in the game
//public class EntitiesFactory {
//
//    public Player createPlayer() {
//        //create player
//        return new Player();
//    }
//
//    public AI createAI() {
//        //create AI
//        return new AI();
//    }
//
//    public Item createItem() {
//        //create item
//        return new Item();
//    }
//
//    public iEntity createEntity(Class<? extends iEntity> entityClass) {
//        if (entityClass.equals(Player.class)) {
//            return createPlayer();
//        } else if (entityClass.equals(AI.class)) {
//            return createAI();
//        } else if (entityClass.equals(Item.class)) {
//            return createItem();
//        } else {
//            return null;
//        }
//
//    }
//}
