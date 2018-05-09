package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Player extends Personage {

    private Score score;
    private int bombOnField;
    private int maxBombOnField = 3;



    public Player() {
        super();
    }

    public Player(Vector2 position, Texture texture, int x, int y, int width, int height, int life, int onKillScore) {
        super(position, texture, x, y, width, height, life, onKillScore);

    }

    public Player(Player player) {
        super(player.position, player.getTexture(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), player.life, player.onKillScore);
        setPlayer(player);
    }

    public void setPlayer(Player player) {
        this.setPosition(player.position);
        this.score = new Score(score);
    }

    public void dropBomb() {


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
