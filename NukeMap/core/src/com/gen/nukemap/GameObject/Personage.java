package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.NukeMap;

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

    public void createBody(){

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(getX(), getY());

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(getWidth() / 4f + getHeight() / 4f);

        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.filter.categoryBits = NukeMap.PERSO_BIT;
        fixtureDef.filter.maskBits = NukeMap.DEFAULT_BIT | NukeMap.UNBREAK_BIT | NukeMap.BREAK_BIT | NukeMap.PERSO_BIT;



        fixtureDef.shape = shape;
        fixtureDef.density = 10f;
        body.createFixture(fixtureDef);

        // TEST
        fixtureDef.isSensor = true;


        body.createFixture(fixtureDef).setUserData("shape");

        //TEST


        shape.dispose();
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
