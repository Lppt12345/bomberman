package com.oopproj.bomberman.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.data.Assets;

public class BombermanGame extends Game {
	public SpriteBatch batch;
	public Assets assets;

	@Override
	public void create () {
		batch = new SpriteBatch();
		assets = new Assets();
		this.setScreen(new Menu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
