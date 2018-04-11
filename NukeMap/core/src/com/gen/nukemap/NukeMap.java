package com.gen.nukemap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NukeMap extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		manager.load("assets/music/Jihad Trap - Drop The Bomb.mp3", Music.class);
        manager.load("assets/sound/explosion.wav", Sound.class);
        manager.load("assets/sound/explosion.wav2", Sound.class);
        manager.finishLoading();


    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
