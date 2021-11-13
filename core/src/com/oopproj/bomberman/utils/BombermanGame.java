package com.oopproj.bomberman.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.data.Assets;
import com.oopproj.bomberman.data.Map;

import java.io.FileNotFoundException;

public class BombermanGame extends Game {
	public SpriteBatch batch;
	public Assets assets;
	public Map map;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assets = new Assets();
		try {
			map = new Map("maptest.txt", assets);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.setScreen(new Gameplay(this));
	}

	@Override
	public void render () {
		if (assets.update()) {
			super.render();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
