package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.Button;
import com.oopproj.bomberman.ui.GameSound;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.ui.UIElement;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Menu extends Scene {
    private Button button_play;
    private Button button_exit;
    private Button button_settings;
    private List<UIElement> buttons;

    public Menu(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        this.button_play = new Button(new Texture(Gdx.files.internal("ui/play.png")), ScreenRes.getWidth() / 2, 225);
        this.button_settings = new Button(new Texture(Gdx.files.internal("ui/settings.png")), (ScreenRes.getWidth() / 2) - 10 - 75, 100);
        this.button_exit = new Button(new Texture(Gdx.files.internal("ui/exit.png")), (ScreenRes.getWidth() / 2) + 10 + 75, 100);
        this.buttons = new ArrayList<UIElement>() {
            {
                add(button_play);
                add(button_exit);
                add(button_settings);
            }
        };
        GameSound.playMainMenu();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        button_play.render();
        if ((boolean) button_play.process(buttons)) {
            try {
                if (this.state == State.STATIC) {
                    this.state = State.FADEOUT;
                }
                if (this.state == State.DISAPPEARED) {
                    GameSound.stopMainMenu();
                    game.setScreen(new Gameplay(game));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (button_play.isDoneRendering()) {
            button_settings.render();
            if ((boolean) button_settings.process(buttons)) {
                if (this.state == State.STATIC) {
                    this.state = State.FADEOUT;
                }
                if (this.state == State.DISAPPEARED) {
                    Settings nextScene = new Settings(game);
                    nextScene.setPrevScene(this);
                    game.setScreen(nextScene);
                    state = State.FADEIN;
                }
            }
        }

        if (button_settings.isDoneRendering()) {
            button_exit.render();
            if ((boolean) button_exit.process(buttons)) {
                if (this.state == State.STATIC) {
                    this.state = State.FADEOUT;
                }
                if (this.state == State.DISAPPEARED) {
                    Gdx.app.exit();
                }
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
