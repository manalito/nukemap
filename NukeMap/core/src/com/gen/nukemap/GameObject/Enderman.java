package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enderman extends Enemy {

    public Enderman(Vector2 position, Texture texture, float x, float y, float width, float height, int life, int onKillScore) {
        super(position, texture, x, y, width, height, life, onKillScore);
    }
}
