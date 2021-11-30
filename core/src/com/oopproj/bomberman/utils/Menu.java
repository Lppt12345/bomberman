package com.oopproj.bomberman.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oopproj.bomberman.data.State;
import com.oopproj.bomberman.ui.Button;
import com.oopproj.bomberman.ui.ScreenRes;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Screen {
    private BombermanGame game;
    private Button button_play;
    private Button button_exit;
    private List<Button> buttons;
    private Texture background;
    private State state;

    public Menu(BombermanGame game) {
        this.state = State.FADEIN;
        this.game = game;
        this.background = new Texture(Gdx.files.internal("ui/background.png"));
        this.button_play = new Button(new Texture(Gdx.files.internal("ui/play.png")), ScreenRes.getWidth() / 2, 200);
        this.button_exit = new Button(new Texture(Gdx.files.internal("ui/exit.png")), ScreenRes.getWidth() / 2, 50);
        this.buttons = new ArrayList<Button>() {
            {
                add(button_play);
                add(button_exit);
            }
        };
        game.sound.playMainMenu();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        if (this.state == State.FADEIN) {
            game.renderAlpha = MathUtils.clamp(game.renderAlpha + Gdx.graphics.getDeltaTime(), 0, 1);
            if (game.renderAlpha == 1) {this.state = State.STATIC;}
        }
        if (this.state == State.FADEOUT) {
            game.renderAlpha = MathUtils.clamp(game.renderAlpha - Gdx.graphics.getDeltaTime(), 0, 1);
            if (game.renderAlpha == 0) {this.state = State.DISAPPEARED;}
        }

        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.setColor(1, 1, 1, game.renderAlpha);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();

        button_play.render();
        if (button_play.process(buttons)) {
            try {
                if (this.state == State.STATIC) {
                    this.state = State.FADEOUT;
                }
                if (this.state == State.DISAPPEARED) {
                    game.sound.stopMainMenu();
                    game.setScreen(new Gameplay(game));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (button_play.isDoneRendering()) {
            button_exit.render();
            if (button_exit.process(buttons)) {
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
    public void resize(int width, int height) {}

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
