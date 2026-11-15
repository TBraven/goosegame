package io.github.goosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class Player extends Entity {

    Texture texture = new Texture("player.png");
    public Player(float startX, float startY) {
        super(startX, startY);
        this.speed = 120;
    }
    
    public Player() {
        this(0, 0);

    }
    
    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, x-50, y-50, 100, 100);
    }

    @Override
    public void drawHitbox(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.circle(x, y, 25, 8);
    }



    private void tryPickupItem(Array<Item> items, Array<InventorySlot> invSlots) {
        for (Item item : items) {
                if (item.calculateDistanceFromPlayer(this) < 30 && item.state == 1) {
                    
                    for (InventorySlot invSlot : invSlots) {
                        if (invSlot.getItem() == null) {
                            invSlot.setItem(item);
                            item.setState(0);
                            item.setInventorySlot(invSlot);
                            break;
                        }
                    }
                }
            }
    }
    /*
    Updates the player character.
    
    Input:
    float - the time passed since last frame
    Array<WorldObject> - an array of all world objects that the player can collide with.
     */
    public void update(float delta, Array<WorldObject> worldObjects, Array<Goose> geese, Array<Item> items, Array<InventorySlot> invSlots) {


        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            tryPickupItem(items, invSlots);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {

            if (invSlots.get(0).getItem() == null) {
                return;
            }

            invSlots.get(0).getItem().useItem(this, geese);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {

            if (invSlots.get(1).getItem() == null) {
                return;
            }

            invSlots.get(1).getItem().useItem(this, geese);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {

            if (invSlots.get(2).getItem() == null) {
                return;
            }

            invSlots.get(2).getItem().useItem(this, geese);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (checkForCollisions(x - (speed * delta), y, worldObjects)) {
                x -= speed * delta;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (checkForCollisions(x + (speed * delta), y, worldObjects)) {
                x += speed * delta;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (checkForCollisions(x, y + (speed * delta), worldObjects)) {
                y += speed * delta;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (checkForCollisions(x, y - (speed * delta), worldObjects)) {
                y -= speed * delta;
            }
        }
    }
}
