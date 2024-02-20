package com.mygdx.game.Managers;

import com.mygdx.game.Entities.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;


public class EntityManager {

    // i can have a entity arraylist here, everytime i create a new entity i add it to the arraylist
    // then i can loop through the arraylist and update and render each entity
    private List<Entity> entities;

    public EntityManager() {

        this.entities = new ArrayList<>();

    }

    public Entity createEntity(Class<? extends Entity> entityClass, Object... args) {
        try {
            Entity entity;
            if (args.length > 0){
                //get the classes of the arguments, so it can figure out which constructor to use
                Class<?>[] argClasses = new Class<?>[args.length];
                for (int i = 0; i < args.length; i++){
                   // System.out.println("args[i]: " + args[i]);
                    argClasses[i] = args[i].getClass();

                    if (args[i] instanceof Integer){
                        argClasses[i] = int.class;
                    }

                }
                Constructor<? extends Entity> constructor = entityClass.getConstructor(argClasses);
//                System.out.println("Constructor: " + constructor);
                //this creates a new instance of the entity, with the correct constructor found
                entity = constructor.newInstance(args);

            }else{
                //uses the default constructor
                entity = entityClass.newInstance();
            }
            addEntity(entity);
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public void addEntity(Entity entity) {
        entities.add(entity);
    }


    //A method to get the entity from the list, if it doesnt exist, create and add it to the list
    //this always returns the first entity of the type, if there are multiple entities of the same type,
    // it will not return the correct one
    //* Useful if i want to get the instance of a class without knowing its ID

    public Entity getEntity(Class<? extends Entity> entityClass, Object... args) {
        //loop through my current entities, if the entity exists, return it
        for (Entity entity : entities) {
            if (entity.getClass().equals(entityClass)) {
                if (entity instanceof AI && args.length > 0){
                    AI enemy = (AI) entity; //cast the entity to an AI so i can get the enemy type
                    if (enemy.getSubType().equals(args[0])){
                        return entity;
                    }

                }else{
                    //this is not the AI, so i know its definitely the entity i want since theres only 1 of each entity
                    //either that or its an AI with no declared args, so i return it anyways
                    return entity;
                }
            }
        }
        //if the entity does not exist, create it and add it to the list
          return createEntity(entityClass, args);
    }

    //a method to get the entity through its id
    public Entity getEntity(int id){
        for (Entity entity : entities) {
            if (entity.getID() == id){
                return entity;
            }
        }
        return null;
    }

    //i need 2 other methods to getEntity, one through subtype, another through class, i create an array of entiity for those that fulfill the condition and return it
    public List<Entity> getEntities(Class<? extends Entity> entityClass){
        List<Entity> entityList = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getClass().equals(entityClass)){
                entityList.add(entity);
            }
        }
        return entityList;
    }

    public List<Entity> getEntities(String subType){
        List<Entity> entityList = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof NonPlayable) {
                NonPlayable nonPlayableEntity = (NonPlayable) entity;
                if (nonPlayableEntity.getSubType().equals(subType)){
                    entityList.add(entity);
                }
            }
        }
        return entityList;
    }





    public List<Entity> getEntities() {
        return entities;
    }


    public void disposeEntities() {
        for (Entity entity : entities) {
            //check if entity contains
            entity.dispose(); //dispose of the entity
        }
        entities.clear(); //clear the entities list
    }


}
