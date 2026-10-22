
package io.github.goosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

public class Player extends Entity {

    public Player(float startX, float startY) {
        super(startX, startY);
        this.speed = 120;
    }
    public Player() {
    this(100, 100);
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.circle(x, y, 25, 8);
    }

    /*
    Updates the player character.
    
    Input:
    float - the time passed since last frame
    Array<WorldObject> - an array of all world objects that the player can collide with.
     */
    @Override
    public void update(float delta, Array<WorldObject> worldObjects) {

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
