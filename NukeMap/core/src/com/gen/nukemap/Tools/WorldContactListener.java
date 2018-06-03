package com.gen.nukemap.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.GameObject.Breakable;
import com.gen.nukemap.GameObject.InteractiveTileObject;

public class WorldContactListener implements ContactListener {
    /**
     * Called when two fixtures begin to touch.
     *
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {

        Gdx.app.log("Begin Contact","Touch object");

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "bomb" || fixB.getUserData() == "breakable"){
            Fixture shape = (fixA.getUserData() == "shape") ? fixA : fixB;
            Fixture object = (shape == fixA) ? fixB : fixA ;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((Breakable)object.getUserData()).onBombExplode();
                Gdx.app.log("BOMB","bombienfjef");
            }
        } else{
            System.out.println("Not Bomb");
        }
    }

    /**
     * Called when two fixtures cease to touch.
     *
     * @param contact
     */
    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact","");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
