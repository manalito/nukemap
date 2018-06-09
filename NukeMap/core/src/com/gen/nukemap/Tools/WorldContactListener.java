package com.gen.nukemap.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.Client.Client;
import com.gen.nukemap.Client.ClientController;
import com.gen.nukemap.GameObject.*;
import com.gen.nukemap.Screens.PlayScreen;

public class WorldContactListener implements ContactListener {

    private ClientController clientController;

    public WorldContactListener(ClientController clientController){
        this.clientController = clientController;
    }


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

        if( (fixA.getUserData() == "player" && fixB.getUserData() == "enemy") || (fixA.getUserData() == "enemy" && fixB.getUserData() == "player")){
            Fixture shape = (fixA.getUserData() == "player") ? fixA : fixB;
            Fixture object = (shape == fixA) ? fixB : fixA;


            if(object.getUserData() != null /*&& InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass()*/){
                //((Breakable)object.getUserData()).onBombExplode();
                Gdx.app.log("Enemy hit player","life minus 1");
                clientController.handleCollision(shape);

            }
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
