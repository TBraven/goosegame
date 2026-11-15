package io.github.goosegame;

import io.github.goosegame.WorldObjects.Rock;
import io.github.goosegame.WorldObjects.Tree;

import io.github.goosegame.Items.Seeds;

import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;


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

    private FitViewport viewport;

    // Camera type to follow the player instead of staying still. Meets requirement.
    private OrthographicCamera camera;
    private OrthographicCamera uiCamera;

    // Contains all the world object classes.
    private Array<WorldObject> worldObjects = new Array<>();
    private Array<Item> items = new Array<>();
    private Array<InventorySlot> invSlots = new Array<>();
    private Array<Goose> geese = new Array<>();

    private Player player;
    
    private boolean drawHitboxes = true;
    /* Called when the game first opens.
    Loads required tools from gdx, and creates player and world objects.
    Also readies camera to a predefined 1280x720. */
    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();

        player = new Player();
        geese.add(new Goose(200, 300));
        geese.add(new Goose(300, 300));
        geese.add(new Goose(400, 300));
        geese.add(new Goose(500, 300));
        geese.add(new Goose(600, 300));

        // Creates world objects
        worldObjects.add(new Tree(300, 200, 70));
        worldObjects.add(new Tree(50, 600, 55));
        worldObjects.add(new Tree(450, 350, 80));
        worldObjects.add(new Rock(150, 100, 100));

        // Create items
        items.add(new Seeds(-50, -50));

        // Create inventory slots
        invSlots.add(new InventorySlot(1));
        invSlots.add(new InventorySlot(2));
        invSlots.add(new InventorySlot(3));


        // Readies camera
        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        viewport.apply();
        
        // Readies UI camera
        uiCamera = new OrthographicCamera();
        uiCamera.setToOrtho(false, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
                          
    }


    /* Called once a frame.
    Clears the screen, updates player position and draws all world objects.
    */
    @Override
    public void render(float delta) {

        // Reset screen
        Gdx.gl.glClearColor(39/255f, 110/255f, 58/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



    
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        

        // Iterate through each world object and draw it.
        for (Goose goose : geese) {
            goose.targetedItem = null;
        }
        
        for (Item item : items) {

            item.doPersistent(player, geese, shapeRenderer, batch, delta);
            
        }
        
        // Closes the batch
        shapeRenderer.end();
        batch.end();

        // Update player and camera position.
        player.update(delta, worldObjects, items, invSlots);

        for (Goose goose : geese) {
            goose.update(delta, worldObjects, geese, player);
        }

        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();


        // Draws some text in a batch
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.draw(batch, "Goose Game", 50, 300);
        font.draw(batch, "Use arrow keys to move!", 50, 260);

        batch.end();



        // Using shape renderer to draw world objects
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        player.drawHitbox(shapeRenderer);
        //goose.drawHitbox(shapeRenderer);

        
        shapeRenderer.end();
        
        
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
        
        // Iterate through each world object and draw it.
        for (WorldObject obj : worldObjects) {
            obj.draw(batch);
        }

        for (Item item : items) {
            item.drawItem(batch);
        }
        
        player.draw(batch);

        for (Goose goose : geese) {
            goose.draw(batch);
        }
        
        
        // Closes the batch
        shapeRenderer.end();
        batch.end();
        


        if (drawHitboxes) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            // Iterate through each world object and draw it.
            for (WorldObject obj : worldObjects) {
                obj.drawHitbox(shapeRenderer);
            }

            shapeRenderer.end();
        }

        // Draw UI
        batch.setProjectionMatrix(uiCamera.combined);
        shapeRenderer.setProjectionMatrix(uiCamera.combined);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (InventorySlot invSlot : invSlots) {
            invSlot.draw(batch, shapeRenderer);
        }

        batch.end();
        shapeRenderer.end();

    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

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