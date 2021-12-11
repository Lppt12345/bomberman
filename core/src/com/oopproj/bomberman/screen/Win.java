package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.*;
import com.oopproj.bomberman.utils.LeaderboardLoader;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.LinkedList;

public class Win extends Scene {
    private String name;
    private TextInput nameInput;
    private Banner you_win;
    private Banner score_holder;
    private Font font;

    public Win(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        nameInput = new TextInput(new Texture(Gdx.files.internal("ui/text_input.png")), "Enter your name", ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f - 150);
        you_win = new Banner(new Texture(Gdx.files.internal("ui/you_win.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f + 170);
        score_holder = new Banner(new Texture(Gdx.files.internal("ui/score_holder.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f);
        font = new Font("fonts/whitrabt.ttf", 30);
        uiElements = new ArrayList<UIElement>();
        renderOrder = new LinkedList<UIElement>() {
            {
                add(you_win);
                add(score_holder);
                add(nameInput);
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

        if ((name = (String) nameInput.process(uiElements)) != null) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                game.lboard.addNewRecord(name, 5000000);
                game.lboard.writeLeaderboard();
                game.setScreen(new Menu(game));
            }
        }
        System.out.println(this.state);
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
