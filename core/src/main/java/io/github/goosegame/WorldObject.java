package io.github.goosegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class WorldObject {
    
    private float x;
    private float y;
    private boolean isCollidable;

    protected Shape2D hitbox;


    public WorldObject(float n_x, float n_y, boolean n_isCollidable) {
        x = n_x;
        y = n_y;

        isCollidable = n_isCollidable;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public Shape2D getHitbox() {
        return hitbox;
    }

    public void changePos(float n_x, float n_y) {
        x = n_x;
        y = n_y;
        hitbox = generateHitbox();
    }

    public abstract void draw(SpriteBatch spriteBatch);

    public abstract void drawHitbox(ShapeRenderer shapeRenderer);
    public abstract void changeSize(int new_size);
    public abstract Shape2D generateHitbox();
}
