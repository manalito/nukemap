package com.gen.nukemap.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.NukeMap;

public class Unbreackable extends InteractiveTileObject {

    public Unbreackable(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

        fixture.setUserData(this);
        setCategoryFilter(NukeMap.UNBREAK_BIT);

    }
}
