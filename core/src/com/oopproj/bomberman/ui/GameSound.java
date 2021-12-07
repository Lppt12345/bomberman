package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.io.*;
import java.util.Scanner;

public class GameSound {
    private static final int MUSIC = 0;
    private static final int SOUND = 1;

    private static Music mainMenu = Gdx.audio.newMusic(Gdx.files.internal("sounds/01_music.wav"));
    private static Music level1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/02_music.wav"));
    private static Music level2 = Gdx.audio.newMusic(Gdx.files.internal("sounds/03_music.wav"));
    private static Music level3 = Gdx.audio.newMusic(Gdx.files.internal("sounds/04_music.wav"));

    private static Music gameOver = Gdx.audio.newMusic(Gdx.files.internal("sounds/game_over.wav"));
    private static Music gameWin = Gdx.audio.newMusic(Gdx.files.internal("sounds/game_win.wav"));
    private static Music subMenu = Gdx.audio.newMusic(Gdx.files.internal("sounds/01_music.wav"));


    private static Sound bombTick = Gdx.audio.newSound(Gdx.files.internal("sounds/bomb_tick.wav"));
    private static Sound enemyDeath = Gdx.audio.newSound(Gdx.files.internal("sounds/enemy_death.wav"));
    private static Sound explosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
    private static Sound placeBomb = Gdx.audio.newSound(Gdx.files.internal("sounds/place_bomb.wav"));
    private static Sound playerDeath = Gdx.audio.newSound(Gdx.files.internal("sounds/player_death.wav"));
    private static Sound powerUp = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));

    private static float[] volume = new float[]{1f, 1f};

    public static void readSettings() {
        File file = new File("settings.ini");
        try {
            Scanner scan = new Scanner(file);
            String[] line = new String[2];
            for (int i = 0; i < 2; i++) {
                if (scan.hasNextLine()) {
                    line[i] = scan.nextLine();
                    line[i] = line[i].replace("\n", "");
                    if (line[i].isEmpty()) {
                        switch (i) {
                            case MUSIC: {
                                line[i] = "music_volume = " + volume[MUSIC];
                                break;
                            }
                            case SOUND: {
                                line[i] = "sound_volume = " + volume[SOUND];
                                break;
                            }
                        }
                    }
                    volume[i] = Float.parseFloat(line[i].split(" ")[2]);
                }
            }
        } catch (FileNotFoundException e) {
            writeSettings();
        }
    }

    public static void writeSettings() {
        try {
            FileWriter file = new FileWriter("settings.ini");
            BufferedWriter bw = new BufferedWriter(file);
            bw.write("music_volume = " + volume[MUSIC] + "\n");
            bw.write("sound_volume = " + volume[SOUND] + "\n");
            bw.close();
        } catch (IOException ex) {
            System.out.println("cannot create settings file");
        }
    }

    public static void playMainMenu() {
        mainMenu.setLooping(true);
        mainMenu.setVolume(volume[MUSIC]);
        mainMenu.play();
    }

    public static void stopMainMenu() {
        mainMenu.stop();
    }

    public static void playLevel1() {
        level1.setLooping(true);
        level1.setVolume(volume[MUSIC]);
        level1.play();
    }

    public static void stopLevel1() {
        level1.stop();
    }

    public static void playLevel2() {
        level2.setLooping(true);
        level2.setVolume(volume[MUSIC]);
        level2.play();
    }

    public static void stopLevel2() {
        level2.stop();
    }

    public static void playLevel3() {
        level3.setLooping(true);
        level3.setVolume(volume[MUSIC]);
        level3.play();
    }

    public static void stopLevel3() {
        level3.stop();
    }

    public static void playGameOver() {
        gameOver.setVolume(volume[SOUND]);
        gameOver.play();
    }

    public static void stopGameOver() {
        gameOver.stop();
    }

    public static void playGameWin() {
        gameWin.setVolume(volume[SOUND]);
        gameWin.play();
    }

    public static void stopGameWin() {
        gameWin.stop();
    }

    public static void playSubMenu() {
        subMenu.setLooping(true);
        subMenu.setVolume(volume[MUSIC]);
        subMenu.play();
    }

    public static void stopSubMenu() {
        subMenu.stop();
    }

    public static void playBombTick() {
        bombTick.play(volume[SOUND]);
    }

    public static void playEnemyDeath() {
        enemyDeath.play(volume[SOUND]);
    }

    public static void playExplosion() {
        explosion.play(volume[SOUND]);
    }

    public static void playPlaceBomb() {
        placeBomb.play(volume[SOUND]);
    }

    public static void playPlayerDeath() {
        playerDeath.play(volume[SOUND]);
    }

    public static void playPowerUp() {
        powerUp.play(volume[SOUND]);
    }

    public static float getSoundVolume() {
        return volume[SOUND];
    }

    public static void setSoundVolume(float v) {
        volume[SOUND] = v;
    }

    public static float getMusicVolume() {
        return volume[MUSIC];
    }

    public static void setMusicVolume(float v) {
        volume[MUSIC] = v;
        mainMenu.setVolume(volume[MUSIC]);
        level1.setVolume(volume[MUSIC]);
        level2.setVolume(volume[MUSIC]);
        level3.setVolume(volume[MUSIC]);
        gameOver.setVolume(volume[SOUND]);
        gameWin.setVolume(volume[SOUND]);
        subMenu.setVolume(volume[MUSIC]);
    }
}
