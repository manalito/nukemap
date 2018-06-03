package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.NukeMap;

public class Bomb extends GameObject {

    private int radius;
    private Player player;
    //private static Texture bombTexture = new Texture("bomb.png");
    private Texture currentTexture;
    private static int height = 32;
    private static int width = 32;
    private Body body;

    public Bomb() {
        super();
    }

    public Bomb(World world, Vector2 position, Texture texture, Player player, int radius) {
        super(world, position,  texture, 0, 0, width, height);
        this.radius = radius;
        this.player = player;
        setPosition(position.x-0.1f, position.y-0.12f);
        createBody();
    }
    
    public void updatePosition() {
        setPosition(body.getPosition().x - this.getWidth() / 2, body.getPosition().y - this.getHeight() / 2);
    }

    public void createBody(){

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(getX(), getY());

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(getWidth() / 4f + getHeight() / 4f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = NukeMap.BOMB_BIT;
        fixtureDef.filter.maskBits = NukeMap.DEFAULT_BIT | NukeMap.UNBREAK_BIT | NukeMap.BREAK_BIT ;

        fixtureDef.shape = shape;
        fixtureDef.density = 10f;
        fixtureDef.isSensor = true;

        body.setMassData(new MassData());

        //body.createFixture(fixtureDef);

        // if a bomb explodes, all breakable object are destroyed
        body.createFixture(fixtureDef).setUserData("shape");

        shape.dispose();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Player getIdPlayer() {
        return player;
    }

    public void setIdPlayer(Player player){
        this.player = player;
    }

    public void explode() {

    }


}
