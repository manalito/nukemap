package com.gen.nukemap.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.*;
import com.gen.nukemap.NukeMap;
import com.gen.nukemap.Server.Client;

public class PlayScreen implements Screen {

    private NukeMap game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Client client;

    public PlayScreen(NukeMap game){
        this.game = game;
        texture = new Texture("map.png");
        gamecam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gamePort = new FitViewport(640,480, gamecam);
        client = new Client();
        client.create();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // gamePort.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.begin();
        game.batch.draw(texture,0,0,640 , 480);


        if(client !=null){
            client.drawBomberman(game.batch);
            client.handleInput(Gdx.graphics.getDeltaTime());
            client.drawOthersBomberman(game.batch);
            client.updateClientToServer(Gdx.graphics.getDeltaTime());
        }
        //resize(texture.getWidth(), texture.getHeight());
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //gamePort.update(width, height);
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

    }
}
