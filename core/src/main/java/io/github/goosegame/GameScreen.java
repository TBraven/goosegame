
package io.github.goosegame;

import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;



// Screen requires show, render, resize, pause, resume, hide, dispose
public class GameScreen implements Screen {

    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;

    // Camera type to follow the player instead of staying still at 0,0
    private OrthographicCamera camera;

    private int score = 0;

    // Contains all the world objects that are added like a tree.
    private Array<WorldObject> worldObjects = new Array<>();

    private Player player;

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();

        player = new Player();

        // Creates one world object.
        worldObjects.add(new WorldObject(300, 200));


        camera = new OrthographicCamera();
        camera.setToOrtho(
            false,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );
    }

    @Override
    public void render(float delta) {

        // Reset screen
        Gdx.gl.glClearColor(0.05f, 0.08f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update player and camera position.
        player.update(delta);
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();


        // Begin drawing
        batch.begin();

        font.draw(batch, "Hello, World!", 50, 300);
        font.draw(batch, "Welcome to Goose Game", 50, 260);
        font.draw(batch, "Score: " + score, 50, 220);

        batch.end();



        // Use shape renderer to draw objects
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        player.draw(shapeRenderer);

        // Iterate through each world object and draw it.
        for (WorldObject obj : worldObjects) {
            obj.draw(shapeRenderer);
        }

        // Finishes everything
        shapeRenderer.end();

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
        shapeRenderer.dispose();
    }
}
