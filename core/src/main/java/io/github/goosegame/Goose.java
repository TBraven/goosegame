package io.github.goosegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class Goose extends Entity {
    public Goose(float startX, float startY) {
        super(startX, startY);
        this.speed = 100;
    }


    public void update(float delta, Array<WorldObject> worldObjects, Player player) {
        float dx = player.getX() - this.x;
        float dy = player.getY() - this.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance>0) {
            float dirX = dx / distance;
            float dirY = dy / distance;
            float newX = this.x + (dirX * speed * delta);
            float newY = this.y + (dirY * speed * delta);

            if (checkForCollisions(newX, this.y,worldObjects)) {
                this.x = newX;
            }

            if (checkForCollisions(this.x, newY, worldObjects)) {
                this.y = newY;
            }

            this.hitbox.setPosition(this.x, this.y);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
    }

    @Override
    public void update(float delta, Array<WorldObject> worldObjects) {
    }

    @Override
    public void drawHitbox(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.circle(x, y, size, 8);
    }
}