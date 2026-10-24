package io.github.goosegame.WorldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;

import io.github.goosegame.WorldObject;

public class Rock extends WorldObject {
    
    private int size;

    Texture texture = new Texture("rock.png");
    
    public Rock(float n_x, float n_y, int n_size) {
        super(n_x, n_y, true);

        hitbox = new Circle(n_x, n_y, n_size);

        size = n_size;
    }


    public Shape2D generateHitbox() {
        return new Circle(getX(), getY(), size);
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, getX() - size, getY() - size, size * 2, size * 2);
    }
    public void drawHitbox(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(getX(), getY(), size);
    }
    

    public void changeSize(int n_size) {
        size = n_size;
        ((Circle) hitbox).setRadius(n_size);
    }
    
}
