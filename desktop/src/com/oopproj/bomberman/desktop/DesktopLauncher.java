package com.oopproj.bomberman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.oopproj.bomberman.utils.BombermanGame;
import com.oopproj.bomberman.ui.ScreenRes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		ScreenRes.setResolution(1000, 600);
		config.title = "Bomberman";
		config.undecorated = false;
		config.width = ScreenRes.getWidth();
		config.height = ScreenRes.getHeight();
		config.title = "Bomberman";
		config.undecorated = false;
		config.resizable = false;
		new LwjglApplication(new BombermanGame(), config);
	}
}
