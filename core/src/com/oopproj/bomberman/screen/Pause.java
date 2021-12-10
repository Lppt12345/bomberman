package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.Button;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.ui.UIElement;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Pause extends Scene {
    private Button button_resume;
    private Button button_settings;
    private Button button_menu;

    public Pause(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        button_resume = new Button(new Texture(Gdx.files.internal("ui/resume.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f);
        button_settings = new Button(new Texture(Gdx.files.internal("ui/settings.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f - 100);
        button_menu = new Button(new Texture(Gdx.files.internal("ui/main_menu.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f - 200);

        uiElements = new ArrayList<>();
        renderOrder = new LinkedList<UIElement>() {
            {
                add(button_resume);
                add(button_settings);
                add(button_menu);
            }
        };
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            button_resume.setState(State.SLIDEOUT);
        }
        if ((boolean) button_resume.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                game.setScreen(prevScene);
            }
        }

        if ((boolean) button_settings.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                Settings nextScene = new Settings(game);
                nextScene.setPrevScene(this);
                game.setScreen(nextScene);
                this.state = State.FADEIN;
                for (UIElement e : uiElements) {
                    e.reset();
                }
            }
        }

        if ((boolean) button_menu.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                game.setScreen(new Menu(game));
            }
        }
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
