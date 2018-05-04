package com.gen.nukemap.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gen.nukemap.NukeMap;
import com.gen.nukemap.Screens.MenuScreen;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new MenuScreen();
		new LwjglApplication(new NukeMap(), config);
	}
}
