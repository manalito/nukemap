package com.gen.nukemap.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class Breakable extends InteractiveTileObject{

  private Fixture fixture;

  public Breakable(World world, TiledMap map, Rectangle bounds){
    super(world, map, bounds);
    fixture.setUserData(this);
  }

  @Override
  public void onBombExplode() {
    Gdx.app.log("Break Brick","Explosion");
  }


}