package io.github.goosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

public abstract class Entity {

    protected float x;
    protected float y;

    protected int speed = 120;

    protected Circle hitbox = new Circle(0, 0, 25);

    public Entity(float startX, float startY) {
        this.x = startX;
        this.y = startY;
        this.hitbox.setPosition(startX, startY);
    }
    public abstract void update(float delta, Array<WorldObject> worldObjects);
    
    public abstract void draw(ShapeRenderer shapeRenderer);

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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
