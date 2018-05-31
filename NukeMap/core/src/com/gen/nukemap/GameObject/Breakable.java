package com.gen.nukemap.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.NukeMap;

public class Breakable extends InteractiveTileObject{

  //private Fixture fixture;

  public Breakable(World world, TiledMap map, Rectangle bounds){
    super(world, map, bounds);
  }

  @Override
  public void onBombExplode() {
    Gdx.app.log("Break Brick","Explosion");
  }


}