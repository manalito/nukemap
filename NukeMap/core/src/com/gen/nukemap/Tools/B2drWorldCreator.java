package com.gen.nukemap.Tools;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.GameObject.Breakable;
import com.gen.nukemap.GameObject.Unbreackable;
import com.gen.nukemap.NukeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class B2drWorldCreator {

    public static final List<Breakable> breakables = new ArrayList<Breakable>();
    public static final HashMap<TiledMapTileLayer.Cell,Breakable> refToBreakables = new HashMap<TiledMapTileLayer.Cell, Breakable>();
    public B2drWorldCreator(World world, TiledMap map){
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;



        // Unbreakable objects
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Unbreackable(world, map, rect);
        }

        // Breakable objects
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer) map.getLayers().get(1);



            //System.out.println("Rect: x : " + rect.getX() + " y" + rect.getY());
            Breakable b = new Breakable(world, map, rect);

            TiledMapTileLayer.Cell cell = b.getCell();

            refToBreakables.put(cell, b);

            breakables.add(b);
            //b.onBombExplode();
        }

       // MapLayers mapLayersBreakable= map.getLayers().get(3);
        // Breakable objects
        /*for(MapObject object : ){

            if(object instanceof RectangleMapObject){
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                RectangleMapObject mapObj = (RectangleMapObject) object;
                TiledMapTileLayer layer = (TiledMapTileLayer) mapObj.getL;
                System.out.println("Rect: x : " + rect.getX() + " y" + rect.getY());
                Breakable b = new Breakable(world, map, rect);

                breakables.add(b);
            }
            //b.onBombExplode();
        }*/
    }
}
