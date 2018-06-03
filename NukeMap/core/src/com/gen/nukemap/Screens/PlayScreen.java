package com.gen.nukemap.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.gen.nukemap.Client.ClientController;
import com.gen.nukemap.Tools.WorldContactListener;
import com.gen.nukemap.NukeMap;
import com.gen.nukemap.Client.Client;
import com.gen.nukemap.Tools.B2drWorldCreator;

public class PlayScreen implements Screen {

    private NukeMap game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    // Tiled map variables
    private TmxMapLoader mapLoader;
    private static TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private ClientController clientController;
    private Client client;

    // Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayScreen(NukeMap game){
        this.game = game;
        texture = new Texture("map.png");
        gamecam = new OrthographicCamera(Gdx.graphics.getWidth()  / NukeMap.PPM, Gdx.graphics.getHeight() / NukeMap.PPM);
        gamePort = new FitViewport(NukeMap.V_WIDTH / NukeMap.PPM, NukeMap.V_HEIGHT / NukeMap.PPM, gamecam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / NukeMap.PPM);

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2,0);

        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        world.setContactListener(new WorldContactListener());


        new B2drWorldCreator(world, map);

        clientController = new ClientController(world);
        client = new Client(clientController);

        clientController.setClient(client);
        clientController.initiateConnection(client);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        /*if(Gdx.input.isTouched()){
            gamecam.position.x += 100 * dt;
        }*/
    }

    public void update(float dt){
        handleInput(dt);
        gamecam.update();

        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // gamePort.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.setProjectionMatrix(gamecam.combined);
        renderer.render();

        b2dr.render(world, gamecam.combined);
        game.batch.begin();

        if(client !=null){
            clientController.drawBomb(game.batch);
            clientController.drawBomberman(game.batch);
            clientController.handleInput(Gdx.graphics.getDeltaTime());
            clientController.drawOthersBomberman(game.batch);
            clientController.drawEnnemies(game.batch);
            clientController.updateClientServer(client);
        }
        //resize(texture.getWidth(), texture.getHeight());
        game.batch.end();

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        //gamecam.update();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
    }

    public static Map getMap(){
        return map;
    }

}
