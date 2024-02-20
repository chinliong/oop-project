package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Entities.Player;

public class PlayerControlManager {
    //private Player player;
    private int acceleration;
    private float jumpHeight;
    private float speed;
    public PlayerControlManager(){
      //  this.player = player;
    }
/* 
    public PlayerControlManager(int acceleration, float jumpHeight, float speed){
        this.acceleration = acceleration;
        this.jumpHeight = jumpHeight;
        this.speed = speed;
    }
*/
//    public void walk(){
//
//        if(Gdx.input.isKeyPressed(Input.Keys.A)) player.setPosX(player.getPosX() - (int) (200 * Gdx.graphics.getDeltaTime()));
//        if(Gdx.input.isKeyPressed(Input.Keys.D)) player.setPosX(player.getPosX() + (int) (200 * Gdx.graphics.getDeltaTime()));
//
//    }
    //this time round , take in an argument, if its true then go right else go left
    public void walk(Player player,int key){
        if(key == Input.Keys.RIGHT){
            player.setPosX(player.getPosX() + (int) (200 * Gdx.graphics.getDeltaTime()));
        }
        else{
            player.setPosX(player.getPosX() - (int) (200 * Gdx.graphics.getDeltaTime()));
        }

    }
    public void jump(Player player, boolean jump){
        if(jump) player.setPosY(player.getPosY() + (int) (200 * Gdx.graphics.getDeltaTime()));
        //do an actual jump, so it goes back down

    }

//    public void jump(){
//        if(Gdx.input.isKeyPressed(Input.Keys.W)) player.setPosY(player.getPosY() + (int) (200 * Gdx.graphics.getDeltaTime()));
//
//    }

    public void interact(){

    }

    public void run(){

    }
}
