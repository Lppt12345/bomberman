package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.ui.TextInput;
import com.oopproj.bomberman.ui.UIElement;
import com.oopproj.bomberman.utils.LeaderboardLoader;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Win extends Scene {
    private String name;

    private TextInput nameInput;

    public Win(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        nameInput = new TextInput(new Texture(Gdx.files.internal("ui/text_input.png")), "Enter your name", ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f - 100);
        uiElements = new ArrayList<UIElement>();
        renderOrder = new LinkedList<UIElement>() {
            {
                add(nameInput);
            }
        };
    }

    @Override
    public void render(float delta) {
        super.render(delta);

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
