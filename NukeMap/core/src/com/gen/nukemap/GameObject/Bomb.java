package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Bomb extends GameObject {

    private int radius;
    private Player player;
    private static Texture bombTexture = new Texture("bombs.jpg");
    private Texture currentTexture;
    private static int height = 32;
    private static int width = 32;

    public Bomb() {
        super();
    }

    public Bomb(World world, Vector2 position, Player player, int radius) {
        super(world, position, bombTexture, 0, 0, width, height);
        this.radius = radius;
        this.player = player;
        currentTexture = bombTexture;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Player getIdPlayer() {
        return player;
    }

    public void setIdPlayer(Player player){
        this.player = player;
    }

    public void explode() {

    }


}
