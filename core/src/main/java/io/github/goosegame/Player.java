
package io.github.goosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

public class Player {

    private float x;
    private float y;

    private int speed = 120;

    private Circle hitbox = new Circle(0, 0, 25);

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.circle(x, y, 25, 8);
    }


    /*
    Takes an x,y coordinate and checks if the player's hitbox at this location intercepts with the hitbox of any world object.
    The player is defined as a circle with radius 25.
    
    Input:
    int - the x coordinate of a point
    int - the y coordinate of a point
    Array<WorldObject> - an array of world objects to check collision against.
    
    Returns:
    true - if there is no collisions
    false - if there is a collision
     */
    public boolean checkForCollisions(float x, float y, Array<WorldObject> worldObjects) {

        // Move the player's hitbox to the point
        hitbox.setPosition(x, y);
        

        // Iterate through every world object and get the hitbox.
        // If it intercepts with the player's hitbox, return false
        // If reaches the end of the loop, no collisions, so return true.
        for (WorldObject obj : worldObjects) {

            if (obj.isCollidable()) {
                Shape2D other = obj.getHitbox();

                if (other instanceof Circle) {
                    if (Intersector.overlaps((Circle) other, hitbox)) {
                        return false;
                    }
                } else if (other instanceof Rectangle) {
                    if (Intersector.overlaps(hitbox, (Rectangle) other)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    /*
    Updates the player character.
    
    Input:
    float - the time passed since last frame
    Array<WorldObject> - an array of all world objects that the player can collide with.
     */
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
    

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
