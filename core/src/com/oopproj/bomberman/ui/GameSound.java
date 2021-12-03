package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameSound {
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

    private static float musicVolume = 0;
    private static float soundVolume = 0;

    public static void playMainMenu() {
        mainMenu.setLooping(true);
        mainMenu.setVolume(musicVolume);
        mainMenu.play();
    }

    public static void stopMainMenu() {
        mainMenu.stop();
    }

    public static void playLevel1() {
        level1.setLooping(true);
        level1.setVolume(musicVolume);
        level1.play();
    }

    public static void stopLevel1() {
        level1.stop();
    }

    public static void playLevel2() {
        level2.setLooping(true);
        level2.setVolume(musicVolume);
        level2.play();
    }

    public static void stopLevel2() {
        level2.stop();
    }

    public static void playLevel3() {
        level3.setLooping(true);
        level3.setVolume(musicVolume);
        level3.play();
    }

    public static void stopLevel3() {
        level3.stop();
    }

    public static void playGameOver() {
        gameOver.setVolume(soundVolume);
        gameOver.play();
    }

    public static void stopGameOver() {
        gameOver.stop();
    }

    public static void playGameWin() {
        gameWin.setVolume(soundVolume);
        gameWin.play();
    }

    public static void stopGameWin() {
        gameWin.stop();
    }

    public static void playSubMenu() {
        subMenu.setLooping(true);
        subMenu.setVolume(musicVolume);
        subMenu.play();
    }

    public static void stopSubMenu() {
        subMenu.stop();
    }

    public static void playBombTick() {
        bombTick.play(soundVolume);
    }

    public static void playEnemyDeath() {
        enemyDeath.play(soundVolume);
    }

    public static void playExplosion() {
        explosion.play(soundVolume);
    }

    public static void playPlaceBomb() {
        placeBomb.play(soundVolume);
    }

    public static void playPlayerDeath() {
        playerDeath.play(soundVolume);
    }

    public static void playPowerUp() {
        powerUp.play(soundVolume);
    }

    public static void setSoundVolume(float volume) {
        soundVolume = volume;
    }

    public static void setMusicVolume(float volume) {
        musicVolume = volume;
    }
}
