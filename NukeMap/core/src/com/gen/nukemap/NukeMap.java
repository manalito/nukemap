package com.gen.nukemap;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gen.nukemap.Screens.*;

import javax.management.modelmbean.ModelMBeanNotificationBroadcaster;
import java.awt.*;

public class NukeMap extends Game {
    public static final int V_WIDTH = 960;
    public static final int V_HEIGHT = 704;
    public static final float PPM = 100;

    public MenuScreen menuScreen;

    public SpriteBatch batch;
    public static AssetManager manager; // manager for music

    public static final short DEFAULT_BIT = 1;
    public static final short PERSO_BIT = 2;
    public static final short BREAK_BIT = 4;
    public static final short UNBREAK_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short BOMB_BIT = 32;


    @Override
    public void create () {
        batch = new SpriteBatch();
        //PlayScreen playScreen = new PlayScreen(this);
        // playScreen.resize(1000, 600);

        //manager= new AssetManager();
        //manager.load("music/Jihad Trap - Drop The Bomb.mp3", Music.class);
        //manager.load("sound/explosion.mp3", Sound.class);
        //manager.load("sound/explosion.mp3", Sound.class);
        //manager.finishLoading();

        menuScreen = new MenuScreen();
        setScreen(menuScreen);
    }

    @Override
    public void render () {
        super.render();
    }

    public MenuScreen getMenuScreen(){
        return menuScreen;
    }
}
