package com.gen.nukemap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gen.nukemap.Screens.PlayScreen;

public class NukeMap extends Game {
    SpriteBatch batch;
    public static AssetManager manager;

    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
        manager.load("assets/music/Jihad Trap - Drop The Bomb.mp3", Music.class);
        manager.load("assets/sound/explosion.wav", Sound.class);
        manager.load("assets/sound/explosion.wav2", Sound.class);
        manager.finishLoading();
    }

    @Override
    public void render () {
        super.render();
    }

}
