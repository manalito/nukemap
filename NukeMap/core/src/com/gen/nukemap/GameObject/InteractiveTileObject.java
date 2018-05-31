package com.gen.nukemap.GameObject;


import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.NukeMap;

public abstract class InteractiveTileObject extends MapObject {

    protected World world;
    protected Map map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){

        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bDef = new BodyDef();
        FixtureDef fDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set((bounds.getX() + bounds.getWidth() / 2) / NukeMap.PPM, (bounds.getY() + bounds.getHeight() / 2) / NukeMap.PPM);

        body = world.createBody(bDef);
        shape.setAsBox(bounds.getWidth() / 2 / NukeMap.PPM, bounds.getHeight() / 2  / NukeMap.PPM);
        fDef.shape = shape;

        body.createFixture(fDef);


        //fixture.setUserData(this);
    }


    public abstract void onBombExplode();
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
    }

}
