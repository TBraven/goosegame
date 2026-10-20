
package io.github.goosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {

    private float x;
    private float y;

    private int speed = 120;

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(x, y, 50, 50);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += speed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= speed * delta;
        }
    }
    

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
