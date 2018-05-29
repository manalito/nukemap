package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gen.nukemap.NukeMap;

public abstract class GameObject extends Sprite {

    protected Vector2 position;
    protected World world;


    protected GameObject() {
        super();
        position = new Vector2(0,0);
    }

    protected GameObject(World world,Vector2 position,  Texture texture, float x, float y, float width, float height) {
        super(texture);
        this.world = world;
        setBounds(x,y,width / NukeMap.PPM,height / NukeMap.PPM);
        this.position = position;
        setPosition(position.x / NukeMap.PPM, position.y / NukeMap.PPM);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }




}
