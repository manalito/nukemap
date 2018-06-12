package com.gen.nukemap.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class ScoreScreen implements Screen{

    private MenuScreen menuScreen = null;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    private Texture background = new Texture("landscape-background.png");
    protected Skin skin;

    protected int[] scores;

    public ScoreScreen(MenuScreen menuScreen)
    {
        //atlas = new TextureAtlas("skin.atlas");
        // skin = new Skin(Gdx.files.internal("skin.json"), atlas);
        scores = new int[4];

        this.menuScreen = menuScreen;
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

        Texture background = new Texture("landscape-background.png");
        Batch batch = new SpriteBatch();

        batch.begin();
        batch.draw(background,0,0);
        batch.end();

        //Create Table
        Table menuTable = new Table();

        //Set table to fill stage
        menuTable.setFillParent(true);
        //Set alignment of contents in the table.
        menuTable.center();



        ImageButton backToMenu = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("quitbtn.jpg"))));

        Label  scoresLabel = new Label("Scores", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        Label  scoreClient1Label = new Label("Score player 1", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label scoreClient1Value  = new Label(String.format("%04d",scores[0]), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreClient1Label.setScale(100,100);

        Label  scoreClient2Label = new Label("Score player 2", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label scoreClient2Value  = new Label(String.format("%04d",scores[1]), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label  scoreClient3Label = new Label("Score player 3", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label scoreClient3Value  = new Label(String.format("%04d",scores[2]), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        Label  scoreClient4Label = new Label("Score player 4", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label scoreClient4Value  = new Label(String.format("%04d",scores[3]), new Label.LabelStyle(new BitmapFont(), Color.BLACK));


        backToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(menuScreen);
            }
        });

        scoresLabel.setFontScale(4,4);
        menuTable.add(scoresLabel).padTop(10);
        menuTable.row();

        scoreClient1Label.setFontScale(2,2);
        scoreClient1Value.setFontScale(2,2);
        menuTable.add(scoreClient1Label).padTop(10);
        menuTable.add(scoreClient1Value).padTop(10);
        menuTable.row();

        scoreClient2Label.setFontScale(2,2);
        scoreClient2Value.setFontScale(2,2);
        menuTable.add(scoreClient2Label).padTop(10);
        menuTable.add(scoreClient2Value).padTop(10);
        menuTable.row();

        scoreClient3Label.setFontScale(2,2);
        scoreClient3Value.setFontScale(2,2);
        menuTable.add(scoreClient3Label).padTop(10);
        menuTable.add(scoreClient3Value).padTop(10);
        menuTable.row();

        scoreClient4Label.setFontScale(2,2);
        scoreClient4Value.setFontScale(2,2);
        menuTable.add(scoreClient4Label).padTop(10);
        menuTable.add(scoreClient4Value).padTop(10);
        menuTable.row();

        menuTable.add(backToMenu);
        menuTable.row();
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