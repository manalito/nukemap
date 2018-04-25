package com.gen.nukemap.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gen.nukemap.NukeMap;
import javafx.application.Platform;

public class MenuScreen implements Screen{

    MenuScreen menuScreen = this;
    private NukeMap game = null;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    private Texture background = new Texture("landscape-background.png");
    protected Skin skin;

    public MenuScreen()
    {
        //atlas = new TextureAtlas("skin.atlas");
        // skin = new Skin(Gdx.files.internal("skin.json"), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(900, 704, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }


    @Override
    public void show() {
        //Stage should controll input:
        Gdx.input.setInputProcessor(stage);

        /*
        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.top();

        atlas = new TextureAtlas()
        skin = new Skin(atlas);

        //Create buttons
        TextButton playButton = new TextButton("Play", skin);
        TextButton optionsButton = new TextButton("Options", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        //Add listeners to buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game = new NukeMap();
                game.create();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
        mainTable.add(playButton);
        mainTable.row();
        mainTable.add(optionsButton);
        mainTable.row();
        mainTable.add(exitButton);


        //Add table to stage
        stage.addActor(mainTable);

        */
/*
        Texture background = new Texture("landscape-background.png");
        Batch batch = new SpriteBatch();

        game.batch.begin();
        batch.draw(background,0,0);
        game.batch.end();*/

        //Create Table
        Table menuTable = new Table();
        //Set table to fill stage
        menuTable.setFillParent(true);
        //Set alignment of contents in the table.
        menuTable.center();


        ImageButton playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("playbtn.jpg"))));
        ImageButton scoreButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("scorebtn.jpg"))));
        ImageButton quitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("quitbtn.jpg"))));
        //playButton.setX(Gdx.graphics.getWidth()/2);
        //playButton.setY(Gdx.graphics.getHeight()/2 + playButton.getHeight()/2);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game = new NukeMap();
                game.create();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
            }
        });

        scoreButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new ScoreScreen(menuScreen));
            }
        });


        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Platform.exit();
                System.exit(255);
            }
        });

        menuTable.add(playButton).pad(10);;
        menuTable.row();

        menuTable.add(scoreButton).pad(60);;
        menuTable.row();
        menuTable.add(quitButton);
        stage.addActor(menuTable);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background,0,0,stage.getWidth() , stage.getWidth());

        batch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
        skin.dispose();
        atlas.dispose();
    }
}