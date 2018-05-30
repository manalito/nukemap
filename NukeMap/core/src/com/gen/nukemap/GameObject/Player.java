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
    private int bombOnField;
    private int maxBombOnField = 3;
    private int bombRadius;

    public Player() {
        super();
    }

    public Player(World world, Vector2 position, Texture texture, int x, int y, float width, float height, int life, int onKillScore) {
        super(world, position, texture, x, y, width, height, life, onKillScore);
        createBody();

    }

    public Player(Player player) {
        super(player.world, player.position, player.getTexture(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), player.life, player.onKillScore);
        setPlayer(player);
        createBody();
    }

    public void setPlayer(Player player) {
        this.setPosition(player.position);
        this.score = new Score(score);
    }



    public void updatePlayer(){
        setPosition(body.getPosition().x - this.getWidth() / 2, body.getPosition().y - this.getHeight() / 2);
    }

    public void updateForOtherPlayers(){
        setPosition(body.getPosition().x - this.getWidth() / 2, body.getPosition().y - this.getHeight() / 2);
    }
    public Bomb dropBomb() {
        //Bomb bomb = new Bomb(world, body.getPosition(), this, bombRadius);
        //return bomb;
        return null;
    }

    public int getBombRadius() {
        return bombRadius;
    }

    public void obtainPowerUp(PowerUp powerUp) {
        /*if (this.amount + powerUp.getAmountBonus() < maxAmount) {
            this.amount += powerUp.getAmountBonus();
        }
        if (this.radius + powerUp.getRadiusBonus() < maxRadius) {
            this.radius += powerUp.getRadiusBonus();
        }*/
    }



}
