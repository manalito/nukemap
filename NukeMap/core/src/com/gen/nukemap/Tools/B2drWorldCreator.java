package com.gen.nukemap.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.GameObject.Breakable;
import com.gen.nukemap.GameObject.Unbreackable;
import com.gen.nukemap.NukeMap;

public class B2drWorldCreator {

    public B2drWorldCreator(World world, TiledMap map){
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        // Unbreakable objects
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Breakable(world, map, rect);
        }

        // Breakable objects
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Unbreackable(world, map, rect);
        }
    }
}
