package io.github.goosegame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WorldObject {
    
    private float x = 100;
    private float y = 100;


    public WorldObject(int n_x, int n_y) {
        x = n_x;
        y = n_y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.circle(x, y, 20, 10);
    }
}
