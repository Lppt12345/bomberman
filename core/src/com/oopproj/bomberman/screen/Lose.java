package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.Button;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.ui.UIElement;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lose extends Scene {
    private Button main_menu;

    public Lose(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        main_menu = new Button(new Texture(Gdx.files.internal("ui/main_menu.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f - 100);
        uiElements = new ArrayList<UIElement>();
        renderOrder = new LinkedList<UIElement>() {
            {
                add(main_menu);
            }
        };
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if ((boolean) main_menu.process(uiElements)) {
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
