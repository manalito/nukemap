package com.gen.nukemap.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.gen.nukemap.NukeMap;
import com.gen.nukemap.Screens.PlayScreen;
import com.gen.nukemap.Tools.B2drWorldCreator;
import sun.plugin.javascript.navig4.Layer;

public class Bomb extends GameObject {

    private int radius;
    private Player player;
    //private static Texture bombTexture = new Texture("bomb.png");
    private Texture currentTexture;
    private static int height = 32;
    private static int width = 32;
    private Body body;

    private static int countBombs = 0;

    private int idBomb;

    private Fixture fixture;

    public Bomb() {
        super();
    }

    public Bomb(int idBomb, World world, Vector2 position, Texture texture, Player player, int radius) {
        super(world, position,  texture, 0, 0, width, height);

        this.idBomb = idBomb;

        this.radius = radius;
        this.player = player;
        setPosition(position.x+0.2f, position.y+0.2f);

        createBody();
        /*Filter filter = new Filter();
        filter.categoryBits = NukeMap.BOMB_BIT;
        fixture.setFilterData(filter);*/

        fixture.setUserData(this);


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
        fixtureDef.filter.maskBits = NukeMap.DEFAULT_BIT | NukeMap.UNBREAK_BIT | NukeMap.BREAK_BIT;

        fixtureDef.shape = shape;
        fixtureDef.density = 10f;

        body.setMassData(new MassData());

        // if a bomb explodes, all breakable object are destroyed
        fixture = body.createFixture(fixtureDef);


        shape.dispose();
    }

    // To get the position of the tile in the map where the bomb was dropped
    public Vector2 getLayerPosition(){
        return getBoundingRectangle().getPosition(body.getPosition());
    }

    public void destroyBody(){
        world.destroyBody(this.body);
    }

    public void destroyBricks(){
        float bombXposition = body.getPosition().x * NukeMap.PPM / 64;
        float bombYposition = body.getPosition().y * NukeMap.PPM / 64;
        TiledMapTileLayer layer = (TiledMapTileLayer) PlayScreen.getMap().getLayers().get(1);

        TiledMapTileLayer.Cell cell1 = layer.getCell((int)bombXposition + 1,(int)bombYposition);
        //setCategoryFilter(NukeMap.DESTROYED_BIT);
        //cell1.setTile(null);

        //B2drWorldCreator.breakables.get(2).onBombExplode();

        Breakable breakable1 = B2drWorldCreator.refToBreakables.get(cell1);
        if(breakable1 != null){
            breakable1.onBombExplode();
        }

        TiledMapTileLayer.Cell cell2 = layer.getCell((int)bombXposition - 1, (int)bombYposition);

        Breakable breakable2 = B2drWorldCreator.refToBreakables.get(cell2);
        if(breakable2 != null){
            breakable2.onBombExplode();
        }
        //setCategoryFilter(NukeMap.DESTROYED_BIT);
        //cell2.setTile(null);



    }

    public int getIdBomb(){
        return idBomb;
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
