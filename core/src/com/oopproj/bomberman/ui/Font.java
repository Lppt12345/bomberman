package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;

public class Font implements Disposable {
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public Font(String font, int size) {
        generator = new FreeTypeFontGenerator(Gdx.files.internal(font));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        this.font = generator.generateFont(parameter);
    }

    public void chageSize(int size) {
        parameter.size = size;
        this.font = generator.generateFont(parameter);
    }

    public void setColor(float r, float g, float b, float a) {
        this.font.setColor(r, b, g, a);
    }

    public void draw(SpriteBatch batch, String text, float x, float y) {
        GlyphLayout gl = new GlyphLayout(this.font, text);
        font.draw(batch, text, x - gl.width / 2, y - gl.height / 2);
    }

    @Override
    public void dispose() {
        this.font.dispose();
        generator.dispose();
    }
}
