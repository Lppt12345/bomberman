package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.ui.*;
import com.oopproj.bomberman.utils.State;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Settings extends Scene {
    private Slider musicSlider;
    private Slider soundSlider;
    private Font font;
    private Button back;
    private Banner settings_title;
    private Checkbox showFPS;

    public Settings(BombermanGame game) {
        super(game, new Texture(Gdx.files.internal("ui/background.png")));
        Texture slider = new Texture(Gdx.files.internal("ui/slider.png"));
        Texture slideBar = new Texture(Gdx.files.internal("ui/slidebar.png"));
        settings_title = new Banner(new Texture(Gdx.files.internal("ui/settings_title.png")), ScreenRes.getWidth() / 2f, ScreenRes.getHeight() / 2f + 170);
        musicSlider = new Slider(slideBar, slider, (float) ScreenRes.getWidth() / 2, 320, GameSound.getMusicVolume());
        soundSlider = new Slider(slideBar, slider, (float) ScreenRes.getWidth() / 2, 220, GameSound.getSoundVolume());
        font = new Font("fonts/whitrabt.ttf", 24);
        showFPS = new Checkbox(new Texture(Gdx.files.internal("ui/checkbox.png")), "Show fps", game.showFps, ScreenRes.getWidth() / 2f, 140);
        back = new Button(new Texture(Gdx.files.internal("ui/back.png")), ScreenRes.getWidth() / 2f, 50);
        uiElements = new ArrayList<UIElement>();
        renderOrder = new LinkedList<UIElement>() {
            {
                add(settings_title);
                add(musicSlider);
                add(soundSlider);
                add(showFPS);
                add(back);
            }
        };
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        font.setColor(1, 1, 1, musicSlider.getAlpha());
        font.draw("Music Volume", musicSlider.getX(), musicSlider.getCurrentY() - 10);

        font.setColor(1, 1, 1, soundSlider.getAlpha());
        font.draw("Sound Volume", soundSlider.getX(), soundSlider.getCurrentY() - 10);

        batch.end();

        GameSound.setMusicVolume((float) musicSlider.process(null));
        GameSound.setSoundVolume((float) soundSlider.process(null));
        game.showFps = (boolean) showFPS.process(uiElements);

        if ((boolean) back.process(uiElements)) {
            if (this.state == State.STATIC) {
                this.state = State.FADEOUT;
                writeSettings();
            }
            if (this.state == State.DISAPPEARED) {
                game.setScreen(prevScene);
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

    public void readSettings() {
        File file = new File("settings.ini");
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.trim().split("=");
                switch (parts[0]) {
                    case "music_volume": {
                        GameSound.setMusicVolume(Float.parseFloat(parts[1]));
                        break;
                    }
                    case "sound_volume": {
                        GameSound.setSoundVolume(Float.parseFloat(parts[1]));
                        break;
                    }
                    case "show_fps": {
                        game.showFps = Boolean.parseBoolean(parts[1]);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            writeSettings();
        }
    }

    public void writeSettings() {
        try {
            FileWriter file = new FileWriter("settings.ini");
            BufferedWriter bw = new BufferedWriter(file);
            bw.write("music_volume=" + GameSound.getMusicVolume() + "\n");
            bw.write("sound_volume=" + GameSound.getSoundVolume() + "\n");
            bw.write("show_fps=" + game.showFps + "\n");
            bw.close();
        } catch (IOException ex) {
            System.out.println("cannot create settings file");
        }
    }
}
