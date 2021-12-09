package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oopproj.bomberman.object.entity.Bomber;
import com.oopproj.bomberman.object.entity.enemy.Enemy;
import com.oopproj.bomberman.object.item.Item;
import com.oopproj.bomberman.ui.*;
import com.oopproj.bomberman.utils.Map;
import com.oopproj.bomberman.utils.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Gameplay extends Scene {
    private int WORLD_WIDTH;
    private int WORLD_HEIGHT;
    private OrthographicCamera camera;
    private List<Item> itemList;

    private Long score = (long) 1;
    private Bomber player;
    private Map map;
    private List<Enemy> enemyList;

    private SpriteBatch hudBatch;
    private Font font;
    private ArrayList<UIElement> uiElements;
    private Queue<UIElement> renderOrder;
    private Button button_pause;

    private Texture score_title;
    private Texture score_holder;
    private Texture heart_holder;
    private Texture heart;

    private Banner pause_background;

    public Gameplay(BombermanGame game) throws Exception {
        super(game, null);
        map = new Map("maptest.txt");
        WORLD_WIDTH = map.getColumn() * ScreenRes.scale;
        WORLD_HEIGHT = map.getRow() * ScreenRes.scale;
        player = map.getPlayer();
        enemyList = map.getEnemies();
        itemList = map.getItems();
        camera = new OrthographicCamera(700 * ScreenRes.getRatio(), 700);
        GameSound.playLevel1();

        hudBatch = new SpriteBatch();
        font = new Font("fonts/whitrabt.ttf", 30);
        button_pause = new Button(new Texture(Gdx.files.internal("ui/pause.png")), 30, 30);
        uiElements = new ArrayList<UIElement>();
        renderOrder = new LinkedList<UIElement>() {
            {
                add(button_pause);
            }
        };

        score_holder = new Texture(Gdx.files.internal("ui/score_holder.png"));
        heart_holder = new Texture(Gdx.files.internal("ui/heart_holder.png"));
        heart = new Texture(Gdx.files.internal("ui/heart.png"));

        pause_background = new Banner(new Texture(Gdx.files.internal("ui/pause_background.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f);
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // game
        camera.position.set(
                MathUtils.clamp(player.getPos().x, camera.viewportWidth / 2f, WORLD_WIDTH - camera.viewportWidth / 2f),
                MathUtils.clamp(player.getPos().y, camera.viewportHeight / 2f, WORLD_HEIGHT - camera.viewportHeight / 2f),
                0
        );
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        player.move(map);
        for (Enemy a : enemyList) {
            a.move(map);
        }
        map.updateMap();

        // hud
        batch.begin();
        map.render(batch);
        for (Item a : itemList) {
            a.render(batch);
        }
        for (Enemy a : enemyList) {
            a.render(batch);
        }
        player.render(batch);
        batch.end();

        hudBatch.setColor(1, 1, 1, game.renderAlpha);
        hudBatch.begin();

        hudBatch.draw(heart_holder, 5, ScreenRes.getHeight() - 80);
        hudBatch.draw(score_holder, ScreenRes.getWidth() - 160, ScreenRes.getHeight() - 80);
        font.draw(hudBatch, Long.toString(map.getScore()),
                ScreenRes.getWidth() - 160 + score_holder.getWidth() / 2f,
                ScreenRes.getHeight() - 27);
        for (int i = 0; i < player.getLife(); i++) {
            hudBatch.draw(heart, 70 + 30 * i, ScreenRes.getHeight() - 65);
        }
        hudBatch.end();

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            button_pause.setState(State.SLIDEOUT);
        }
        if ((boolean) button_pause.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                Pause pauseScene = new Pause(game, new Texture(Gdx.files.internal("ui/background.png")));
                pauseScene.setPrevScene(this);
                game.setScreen(pauseScene);
                this.state = State.FADEIN;
                for (UIElement e : uiElements) {
                    e.reset();
                }
            }
        }
    }

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
