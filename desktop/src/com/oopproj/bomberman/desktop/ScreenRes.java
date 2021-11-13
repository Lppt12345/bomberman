package com.oopproj.bomberman.desktop;

public class ScreenRes {
    private static int width;
    private static int height;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setResolution(int w, int h) {
        width = w;
        height = h;
    }
}
