package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.Button;
import com.oopproj.bomberman.ui.GameSound;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.ui.UIElement;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Menu extends Scene {
    private Button button_play;
    private Button button_exit;
    private Button button_settings;
    private Button button_leaderboard;
    private List<UIElement> uiElements;
    private Queue<UIElement> renderOrder;


    public Menu(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        this.button_play = new Button(new Texture(Gdx.files.internal("ui/play.png")), ScreenRes.getWidth() / 2f, 250);
        this.button_settings = new Button(new Texture(Gdx.files.internal("ui/settings.png")), (ScreenRes.getWidth() / 2f) - 10 - 75, 50);
        this.button_exit = new Button(new Texture(Gdx.files.internal("ui/exit.png")), (ScreenRes.getWidth() / 2f) + 10 + 75, 50);
        this.button_leaderboard = new Button(new Texture(Gdx.files.internal("ui/leaderboard.png")), ScreenRes.getWidth() / 2f, 140);
        renderOrder = new LinkedList<UIElement>() {
            {
                add(button_play);
                add(button_leaderboard);
                add(button_settings);
                add(button_exit);
            }
        };
        this.uiElements = new ArrayList<UIElement>();
        GameSound.playMainMenu();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        boolean isDoneRendering = true;
        for (UIElement e : uiElements) {
            e.render();
            if (!e.isDoneRendering()) {
                isDoneRendering = false;
                break;
            }
        }
        if (isDoneRendering && !renderOrder.isEmpty()) {
            uiElements.add(renderOrder.poll());
        }

        if ((boolean) button_leaderboard.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                Leaderboard nextScene = new Leaderboard(game);
                nextScene.setPrevScene(this);
                game.setScreen(nextScene);
                state = State.FADEIN;
                for (UIElement e : uiElements) {
                    e.reset();
                }
            }
        }

        if ((boolean) button_play.process(uiElements)) {
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

        if ((boolean) button_exit.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                Gdx.app.exit();
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
                state = State.FADEIN;
                for (UIElement e : uiElements) {
                    e.reset();
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
