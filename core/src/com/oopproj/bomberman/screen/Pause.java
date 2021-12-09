package com.oopproj.bomberman.screen;

import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.Button;

public class Pause extends Scene {
    private Button button_resume;
    private Button button_settings;
    private Button button_menu;

    public Pause(BombermanGame game, Texture background) {
        super(game, background);
        button_resume = new Button();
        button_settings = new Button();
        button_menu = new Button();
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
