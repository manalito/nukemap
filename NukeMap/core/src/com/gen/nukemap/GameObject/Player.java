package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.NukeMap;
import com.sun.scenario.effect.impl.prism.ps.PPSDisplacementMapPeer;

import java.util.ArrayList;
import java.util.List;

public class Player extends Personage {

    private Score score;

    private int bombOnField = 0;
    private int maxBombOnField = 3;

    private int bombRadius = 1;

    public Player() {
        super();
    }

    public Player(String id, World world, Vector2 position, Texture texture, int x, int y, float width, float height, int life, int onKillScore) {
        super(id, world, position, texture, x, y, width, height, life, onKillScore);
        createBody();
        this.score = new Score();

    }

    public Player(Player player){
        super(player.id, player.world, player.position, player.getTexture(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), player.life, player.onKillScore);
        this.score = new Score(player.score);
        setPlayer(player);
        createBody();
    }

    public void setPlayer(Player player) {
        this.setPosition(player.position);
        this.score = new Score(player.score);
    }


    public void defineFixture(){
        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.filter.categoryBits = NukeMap.PERSO_BIT;
        fixtureDef.filter.maskBits = NukeMap.DEFAULT_BIT | NukeMap.UNBREAK_BIT | NukeMap.BREAK_BIT | NukeMap.PERSO_BIT;


        CircleShape shape = new CircleShape();
        shape.setRadius(getWidth() / 4f + getHeight() / 4f);

        fixtureDef.shape = shape;
        fixtureDef.density = 10f;
        body.createFixture(fixtureDef);

        // TEST
        fixtureDef.isSensor = true;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData("player");

        shape.dispose();
    }

    public void updatePlayer(){
        setPosition(body.getPosition().x - this.getWidth() / 2, body.getPosition().y - this.getHeight() / 2);
    }

    public boolean decreaseLife(){
        --life;
        if(life == 0){
            isAlive = false;
        }
        return isAlive;
    }

    public Fixture getFixture(){
        return fixture;
    }

    public int getBombRadius() {
        return bombRadius;
    }

    public int getMaxBombOnField() {
        return maxBombOnField;
    }

    public int getBombOnField(){
        return bombOnField;
    }

    public void increaseBombOnField(){
        ++bombOnField;
    }
    public void decreaseBombOnField(){
        --bombOnField;
    }

    public void setBombOnField(int bombOnField) {
        this.bombOnField = bombOnField;
    }

    public Score getScore(){
        return score;
    }
}
