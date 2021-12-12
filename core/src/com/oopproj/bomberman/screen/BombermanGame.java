package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.ui.Font;
import com.oopproj.bomberman.ui.GameSound;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.utils.Assets;
import com.oopproj.bomberman.utils.LeaderboardLoader;

public class BombermanGame extends Game {
    public Assets assets;
    public float renderAlpha;
    public LeaderboardLoader lboard = LeaderboardLoader.getInstance();
    public long totalScore;
    public boolean showFps = false;
    private Font font;

    @Override
    public void create() {
        renderAlpha = 0;
        assets = Assets.getInstance();
        (new Settings(this)).readSettings();
        this.setScreen(new Menu(this));
        lboard.readLeaderboard();
        font = new Font("fonts/whitrabt.ttf", 15);
    }

    @Override
    public void render() {
        super.render();
        if (showFps) {
            font.draw(Integer.toString(Math.round(1 / Gdx.graphics.getDeltaTime())), 30, ScreenRes.getHeight() - 10);
        }
    }

    @Override
    public void dispose() {}
}
