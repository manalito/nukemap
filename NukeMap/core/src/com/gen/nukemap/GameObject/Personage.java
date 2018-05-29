package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Personage extends GameObject {

    protected Body body;
    public enum STATE {FRONT, BOTTOM, LEFT, RIGHT}

    protected STATE state = STATE.BOTTOM; // default texture region
    protected int life = 0;
    protected int onKillScore = 0;


    public Personage() {
        super();
    }

    public Personage(World world, Vector2 position, Texture texture, float x, float y, float width, float height, int life, int onKillScore) {
        super(world, position, texture, x , y, width, height);
        this.life = life;
        this.onKillScore = onKillScore;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getOnKillScore() {
        return onKillScore;
    }

    public void setOnKillScore(int onKillScore) {
        this.onKillScore = onKillScore;
    }

    public void deplacement() {

    }

    public void die() {

    }

    public boolean hasMoved(){
        if(position.x != getX() || position.y !=getY() ){
            position.x = getX();
            position.y = getY();
            return true;
        }
        return false;
    }

    public STATE getState(){
        return state;
    }

    public void setState(STATE newState){
        state = newState;
    }

    public Body getBody(){
        return body;
    }
}
