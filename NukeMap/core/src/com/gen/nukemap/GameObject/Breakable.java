package com.gen.nukemap.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.gen.nukemap.NukeMap;

public class Breakable extends InteractiveTileObject{

  public Breakable(World world, TiledMap map, Rectangle bounds){
    super(world, map, bounds);
    fixture.setUserData(this);
    setCategoryFilter(NukeMap.BREAK_BIT);

  }


  public void onBombExplode() {
    Gdx.app.log("Break Brick","Explosion");
    setCategoryFilter(NukeMap.DESTROYED_BIT);
    getCell().setTile(null);
  }


}