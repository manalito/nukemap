package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Creeper extends Enemy {

    public Creeper(Vector2 position, Texture texture, float x, float y, float width, float height, int life, int onKillScore) {
        super(position, texture, x, y, width, height, life, onKillScore);
    }

    public Creeper(Creeper creeper){
        super(creeper.position, creeper.getTexture(), creeper.getX(), creeper.getY(), creeper.getWidth(), creeper.getHeight(), creeper.life, creeper.onKillScore);
        setEnemy(creeper);
    }
    public void setEnemy(Enemy enemy) {
        this.setPosition(enemy.position);
    }

}
