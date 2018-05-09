package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GameObject extends Sprite {

    protected Vector2 position;


    public GameObject() {
        super();
        position = new Vector2(0,0);
    }

    public GameObject(Vector2 position,  Texture texture, float x, float y, float width, float height) {
        super(texture);
        setBounds(x,y,width,height);
        this.position = new Vector2(getX(), getY());
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }




}
