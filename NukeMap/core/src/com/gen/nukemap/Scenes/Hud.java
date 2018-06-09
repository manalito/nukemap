package com.gen.nukemap.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gen.nukemap.NukeMap;

public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private Integer numberOfLives;
    private Integer score;

    private Label scoreLabel;
    private Label lifeLabel;

    public Hud(SpriteBatch batch){
        score = 0;

        numberOfLives = 3;

        viewport = new FitViewport(NukeMap.V_WIDTH, NukeMap.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label("Score: " + String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        lifeLabel = new Label("Lives: " + String.format("%01d", numberOfLives), new Label.LabelStyle(new BitmapFont(), Color.GREEN));

        scoreLabel.setFontScale(5/2,5/2);
        lifeLabel.setFontScale(5/2,5/2);
        table.add(scoreLabel).expandX().padTop(7.f);
        table.add(lifeLabel).expandX().padTop(7.f);

        stage.addActor(table);
    }



    @Override
    public void dispose() {
        stage.dispose();
    }
}
