package io.github.goosegame.WorldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;

import io.github.goosegame.WorldObject;

public class Rock extends WorldObject {
    
    private int size;
    
    public Rock(float n_x, float n_y, int n_size) {
        super(n_x, n_y, true);

        hitbox = new Circle(n_x, n_y, n_size);

        size = n_size;
    }


    public Shape2D generateHitbox() {
        return new Circle(getX(), getY(), size);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0.3f, 0.3f, 0.3f, 1);
        shapeRenderer.circle(getX(), getY(), size, 6);
    }

    public void changeSize(int n_size) {
        size = n_size;
        ((Circle)hitbox).setRadius(n_size);
    }
}
