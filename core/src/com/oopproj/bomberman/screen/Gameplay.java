package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
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
import java.util.List;

public class Gameplay implements Screen {
    private BombermanGame game;
    private Long score = (long) 1;
    private Bomber player;
    private Map map;
    private List<Enemy> enemyList;
    private int WORLD_WIDTH;
    private int WORLD_HEIGHT;
    private OrthographicCamera camera;
    private State state;
    private List<Item> itemList;


    private SpriteBatch hudBatch;
    private Font font;
    private ArrayList<UIElement> uiElements;
    private Button pause;

    private Texture score_title;
    private Texture score_holder;
    private Texture heart_holder;
    private Texture heart;

    public Gameplay(BombermanGame game) throws Exception {
        this.game = game;
        state = State.FADEIN;
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
        pause = new Button(new Texture(Gdx.files.internal("ui/pause.png")), 30, 30);
        uiElements = new ArrayList<UIElement>() {{
            add(pause);
        }};

        score_holder = new Texture(Gdx.files.internal("ui/score_holder.png"));
        heart_holder = new Texture(Gdx.files.internal("ui/heart_holder.png"));
        heart = new Texture(Gdx.files.internal("ui/heart.png"));
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
        if (state == State.FADEIN) {
            game.renderAlpha = MathUtils.clamp(game.renderAlpha + Gdx.graphics.getDeltaTime(), 0, 1);
            if (game.renderAlpha == 1) {
                state = State.STATIC;
            }
        }
        if (state == State.FADEOUT) {
            game.renderAlpha = MathUtils.clamp(game.renderAlpha - Gdx.graphics.getDeltaTime(), 0, 1);
            if (game.renderAlpha == 0) {
                state = State.DISAPPEARED;
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.setColor(1, 1, 1, game.renderAlpha);

        camera.position.set(
                MathUtils.clamp(player.getPos().x, camera.viewportWidth / 2f, WORLD_WIDTH - camera.viewportWidth / 2f),
                MathUtils.clamp(player.getPos().y, camera.viewportHeight / 2f, WORLD_HEIGHT - camera.viewportHeight / 2f),
                0
        );
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        player.move(map);
        for (Enemy a : enemyList) {
            a.move(map);
        }
        map.updateMap();

        game.batch.begin();
        map.render(game.batch);
        for (Item a : itemList) {
            a.render(game.batch);
        }
        for (Enemy a : enemyList) {
            a.render(game.batch);
        }
        player.render(game.batch);
        game.batch.end();

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
        pause.render();
        pause.process(uiElements);

        hudBatch.end();
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
