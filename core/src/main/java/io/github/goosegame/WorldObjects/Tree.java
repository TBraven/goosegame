package io.github.goosegame.WorldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Circle;

import io.github.goosegame.WorldObject;

public class Tree extends WorldObject {
    
    private int size;
    
    public Tree(float n_x, float n_y, int n_size) {
        super(n_x, n_y, true);

        hitbox = new Circle(n_x, n_y, n_size);

        size = n_size;
    }


    public Shape2D generateHitbox() {
        return new Circle(getX(), getY(), size);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0, 0.6f, 0, 1);
        shapeRenderer.circle(getX(), getY(), size);
    }

    public void changeSize(int n_size) {
        size = n_size;
        hitbox = new Circle(getX(), getY(), n_size);
    }
}
