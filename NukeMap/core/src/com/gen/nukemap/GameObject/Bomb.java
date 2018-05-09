package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bomb extends GameObject {

    private int radius;
    private String idPlayer = "";

    public Bomb() {
        super();
    }

    public Bomb(String idPlayer, Vector2 position, Texture texture, float x, float y, float width, float height, int radius) {
        super(position, texture, x, y, width, height);
        this.radius = radius;
        this.idPlayer = idPlayer;

    }

    public Bomb(Bomb bomb) {

        super(bomb.position, bomb.getTexture(), bomb.getX(), bomb.getY(), bomb.getWidth(), bomb.getWidth());
        this.radius = bomb.radius;
        this.idPlayer = bomb.idPlayer;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer){
        this.idPlayer = idPlayer;
    }

    public void explode() {

    }


}
