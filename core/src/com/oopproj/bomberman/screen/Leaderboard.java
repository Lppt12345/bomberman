package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.Banner;
import com.oopproj.bomberman.ui.Button;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.ui.UIElement;
import com.oopproj.bomberman.utils.State;

import java.util.*;

public class Leaderboard extends Scene {
    private Button back;
    private Banner[] leaderboard_banner;

    private List<UIElement> uiElements;
    private Queue<UIElement> renderOrder;

    public Leaderboard(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        back = new Button(new Texture(Gdx.files.internal("ui/back.png")), ScreenRes.getWidth() / 2f, 50);
        leaderboard_banner = new Banner[10];
        uiElements = new ArrayList<>();
        renderOrder = new LinkedList<UIElement>() {
            {
                Texture leaderboardHolder = new Texture(Gdx.files.internal("ui/leaderboard_holder.png"));
                for (int i = 0; i < 10; i++) {
                    add(leaderboard_banner[i] = new Banner(leaderboardHolder, ScreenRes.getWidth() / 2f, 525 - i * 40));
                }
                add(back);
            }
        };
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

        if ((boolean) back.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                game.setScreen(prevScene);
            }
        }

        for (int i = 0; i < 10; i++) {
            leaderboard_banner[i].process(null);
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
