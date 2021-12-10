package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.*;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Settings extends Scene {
    private Slider musicSlider;
    private Slider soundSlider;
    private Font font;
    private Button back;

    public Settings(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        Texture slider = new Texture(Gdx.files.internal("ui/slider.png"));
        Texture slideBar = new Texture(Gdx.files.internal("ui/slidebar.png"));
        musicSlider = new Slider(slideBar, slider, (float) ScreenRes.getWidth() / 2, 300, GameSound.getMusicVolume());
        soundSlider = new Slider(slideBar, slider, (float) ScreenRes.getWidth() / 2, 200, GameSound.getSoundVolume());
        font = new Font("fonts/whitrabt.ttf", 24);
        back = new Button(new Texture(Gdx.files.internal("ui/back.png")), ScreenRes.getWidth() / 2f, 50);
        uiElements = new ArrayList<UIElement>();
        renderOrder = new LinkedList<UIElement>() {
            {
                add(musicSlider);
                add(soundSlider);
                add(back);
            }
        };
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        font.setColor(1, 1, 1, musicSlider.getAlpha());
        font.draw("Music Volume", musicSlider.getX(), musicSlider.getCurrentY() - 10);

        font.setColor(1, 1, 1, soundSlider.getAlpha());
        font.draw("Sound Volume", soundSlider.getX(), soundSlider.getCurrentY() - 10);

        batch.end();

        GameSound.setMusicVolume((float) musicSlider.process(null));
        GameSound.setSoundVolume((float) soundSlider.process(null));

        if ((boolean) back.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
                GameSound.writeSettings();
            }
            if (this.state == State.DISAPPEARED) {
                game.setScreen(prevScene);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
