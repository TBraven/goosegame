
package io.github.goosegame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FirstScreen implements Screen {

    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        // Clear the screen to a dark blue colour
        Gdx.gl.glClearColor(0.05f, 0.08f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin drawing
        batch.begin();

        // Draw some text at position (50, 300)
        font.draw(batch, "Hello, World!", 50, 300);
        font.draw(batch, "Welcome to Goose Game", 50, 260);

        // Finish drawing
        batch.end();
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
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
