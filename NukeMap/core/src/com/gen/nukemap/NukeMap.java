package com.gen.nukemap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gen.nukemap.Screens.*;

<<<<<<< HEAD
=======

>>>>>>> b78082f983e1b173ace709c851cbf374355f36de
public class NukeMap extends Game {
    public static final int V_WIDTH = 960;
    public static final int V_HEIGHT = 704;
    public static final float PPM = 100;

    public SpriteBatch batch;
    public static AssetManager manager;

    @Override
    public void create () {
        batch = new SpriteBatch();
        //PlayScreen playScreen = new PlayScreen(this);
        MenuScreen menuScreen = new MenuScreen();
        // playScreen.resize(1000, 600);
        setScreen(menuScreen);
        /*manager.load("Jihad Trap - Drop The Bomb.mp3", Music.class);
        manager.load("explosion.wav", Sound.class);
        manager.load("explosion.wav2", Sound.class);
        manager.finishLoading();*/
    }

    @Override
    public void render () {
        super.render();
    }

}
