package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

public class Gameplay extends Scene {
    private int WORLD_WIDTH;
    private int WORLD_HEIGHT;
    private OrthographicCamera camera;
    private List<Item> itemList;

    public int level;
    private static final int numberOfMap = 3;
    private Map map;
    private Bomber player;
    private List<Enemy> enemyList;

    private Font font;
    private Button button_pause;
    private Banner score_holder;
    private Banner heart_holder;
    private Banner[] heart;

    public Gameplay(BombermanGame game, int level) throws Exception {
        super(game, null);
        this.level = level;
        map = new Map("map" + this.level + ".txt");
        WORLD_WIDTH = map.getColumn() * ScreenRes.scale;
        WORLD_HEIGHT = map.getRow() * ScreenRes.scale;
        player = map.getPlayer();
        enemyList = map.getEnemies();
        itemList = map.getItems();
        camera = new OrthographicCamera(700 * ScreenRes.getRatio(), 700);
        GameSound.playLevel(level);

        font = new Font("fonts/whitrabt.ttf", 30);

        heart_holder = new Banner(
                new Texture(Gdx.files.internal("ui/heart_holder.png")),
                90,
                ScreenRes.getHeight() - 38
        );
        heart = new Banner[3];
        Texture heartTexture = new Texture(Gdx.files.internal("ui/heart.png"));
        for (int i = 0; i < 3; i++) {
            heart[i] = new Banner(heartTexture, 84 + i * 30, ScreenRes.getHeight() - 47);
        }
        score_holder = new Banner(
                new Texture(Gdx.files.internal("ui/score_holder.png")),
                ScreenRes.getWidth() - 80,
                ScreenRes.getHeight() - 38
        );
        button_pause = new Button(new Texture(Gdx.files.internal("ui/pause_button.png")), 30, 30);

        uiElements = new ArrayList<UIElement>();
        renderOrder = new LinkedList<UIElement>() {
            {
                add(heart_holder);
                for (int i = 0 ; i < 3; i++) {
                    add(heart[i]);
                }
                add(score_holder);
                add(button_pause);
            }
        };
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setColor(1, 1, 1, game.renderAlpha);
        sceneTransition();

        // Gameplay
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

        batch.begin();
        map.render(batch);
        for (Item a : itemList) {
            a.render(batch);
        }
        for (Enemy a : enemyList) {
            a.render(batch);
        }
        if (player.getLife() >= 0) {
            player.render(batch);
        } else {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                GameSound.stopLevel(level);
                game.setScreen(new Lose(game));
            }
        }
        batch.end();

        if (player.isCheckWin()) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                if (level + 1 > numberOfMap) {
                    GameSound.stopLevel(level);
                    game.setScreen(new Win(game));
                } else {
                    try {
                        GameSound.stopLevel(level);
                        Gameplay newLevel = new Gameplay(game, level + 1);
                        newLevel.setScore(this.map.getScore());
                        game.setScreen(newLevel);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // HUD
        drawUIElements();
        game.totalScore = map.getScore();
        font.setColor(1, 1, 1, score_holder.getAlpha());
        font.draw(
                Long.toString(game.totalScore),
                score_holder.getX(),
                score_holder.getCurrentY() + 11
        );

        if (0 < player.getLife() && player.getLife() <= 3) {
            for (int i = 0; i < player.getLife(); i++) {
                if (heart[i].getState() == State.DISAPPEARED) {
                    heart[i].setState(State.SLIDEIN);
                }
            }
            for (int i = player.getLife(); i < 3; i++) {
                if (heart[i].getState() == State.STATIC) {
                    heart[i].setState(State.FADEOUT);
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            button_pause.setState(State.SLIDEOUT);
        }
        if ((boolean) button_pause.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
            }
            if (this.state == State.DISAPPEARED) {
                Pause pauseScene = new Pause(game);
                pauseScene.setPrevScene(this);
                game.setScreen(pauseScene);
                GameSound.pauseLevel(level);
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

    public void setScore(long score) {
        map.setScore(score);
    }
}
