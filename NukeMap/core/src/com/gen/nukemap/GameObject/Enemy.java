package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Enemy extends Personage {
    public Enemy() {
        super();
    }

    public Enemy(World world, Vector2 position, Texture texture, float x, float y, float width, float height, int life, int onKillScore) {
        super(world, position, texture, x, y, width, height, life, onKillScore);
        createBody();
    }

    public void updateEnemy(){
        setPosition(body.getPosition().x - this.getWidth() / 4, body.getPosition().y - this.getHeight() / 2);
    }
}
