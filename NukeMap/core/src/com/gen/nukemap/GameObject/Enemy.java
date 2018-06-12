package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gen.nukemap.NukeMap;

public class Enemy extends Personage {
    public Enemy() {
        super();
    }

    public Enemy(String id, World world, Vector2 position, Texture texture, float x, float y, float width, float height, int life,
                 int onKillScore) {
        super(id, world, position, texture, x, y, width, height, life, onKillScore);
        createBody();

    }

    public void updateEnemy(){
        setPosition(body.getPosition().x - this.getWidth() / 4, body.getPosition().y - this.getHeight() / 2);
    }


    public void defineFixture(){
        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.filter.categoryBits = NukeMap.PERSO_BIT;
        fixtureDef.filter.maskBits = NukeMap.DEFAULT_BIT | NukeMap.UNBREAK_BIT | NukeMap.BREAK_BIT | NukeMap.PERSO_BIT;


        CircleShape shape = new CircleShape();
        shape.setRadius(getWidth() / 4f + getHeight() / 4f);

        fixtureDef.shape = shape;
        fixtureDef.density = 10f;
        fixture = body.createFixture(fixtureDef);

        // TEST
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("enemy");

        shape.dispose();
    }

    @Override
    public Fixture getFixture() {
        return fixture;
    }
}
