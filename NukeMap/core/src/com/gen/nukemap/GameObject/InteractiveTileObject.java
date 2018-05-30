package com.gen.nukemap.GameObject;


import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.NukeMap;

public abstract class InteractiveTileObject extends GameObject{

    private World world;
    private Body body;
    private Map map;
    private Rectangle bounds;
    private Fixture fixture;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){

        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / NukeMap.PPM,(bounds.getY() + bounds.getHeight() / 2) / NukeMap.PPM );

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / NukeMap.PPM,(bounds.getY() + bounds.getHeight() / 2) /  NukeMap.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);

        fixture = body.createFixture(fdef);
    }


    public abstract void onBombExplode();


}
