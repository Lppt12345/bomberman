package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.*;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lose extends Scene {
    private Button main_menu;
    private Banner you_lose;
    private Banner score_holder;
    private Font font;

    public Lose(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        GameSound.playGameOver();
        main_menu = new Button(new Texture(Gdx.files.internal("ui/main_menu.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f - 100);
        you_lose = new Banner(new Texture(Gdx.files.internal("ui/you_lose.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f + 170);
        score_holder = new Banner(new Texture(Gdx.files.internal("ui/score_holder.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f);
        font = new Font("fonts/whitrabt.ttf", 30);
        uiElements = new ArrayList<UIElement>();
        renderOrder = new LinkedList<UIElement>() {
            {
                add(you_lose);
                add(score_holder);
                add(main_menu);
            }
        };
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        font.setColor(1, 1, 1, score_holder.getAlpha());
        font.draw(
                Long.toString(game.totalScore),
                score_holder.getX(),
                score_holder.getCurrentY() + 11
        );

        if ((boolean) main_menu.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                GameSound.stopGameOver();
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
