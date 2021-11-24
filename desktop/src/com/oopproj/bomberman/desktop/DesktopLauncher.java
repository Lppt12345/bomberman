package com.oopproj.bomberman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.oopproj.bomberman.utils.BombermanGame;
import com.oopproj.bomberman.utils.ScreenRes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		ScreenRes.setResolution(1024, 600);
		config.width = ScreenRes.getWidth();
		config.height = ScreenRes.getHeight();
		config.resizable = false;
		new LwjglApplication(new BombermanGame(), config);
	}
}
