package io.github.goosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Input;


public class WinScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private Main game;
    private String reason;
    public WinScreen(Main game, String n_reason) {
        this.game = game;
        reason = n_reason;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);
    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(145/255f, 217/255f, 134/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.getData().setScale(3);
        font.draw(batch, "YOU WIN", Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.6f);
        font.getData().setScale(1.5f);
        font.draw(batch, reason, Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.5f);
        font.draw(batch, "Press ENTER to play again", Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.4f);
        batch.end();

        // Restart game when Enter is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(game));
            dispose();
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
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}