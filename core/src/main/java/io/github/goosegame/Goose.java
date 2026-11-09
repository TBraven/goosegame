package io.github.goosegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Texture;

public class Goose extends Entity {
    
    Texture texture = new Texture("goose.png");

    public Item targetedItem;

    public Goose(float startX, float startY) {
        super(startX, startY);
        this.speed = 70;
    }

    public void update(float delta, Array<WorldObject> worldObjects, Array<Goose> geese, Player player) {

        float targetX = player.getX();
        float targetY = player.getY();

        if (targetedItem != null) {
            targetX = targetedItem.getX();
            targetY = targetedItem.getY();
        }

        float dx = targetX - this.x;
        float dy = targetY - this.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        System.out.println("Made it here 1");

        if (distance > 0) {
            float targetAngle = (float) Math.atan2(dy, dx);
            float moveAngle = targetAngle;

            float[] angleOffsets = { 0, 15, -15, 30, -30, 45, -45, 60, -60, 75, -75, 90, -90 };
            float lookAheadDist = 100f;

            for (float offset : angleOffsets) {
                float testAngle = targetAngle + (float) Math.toRadians(offset);

                if (isPathClear(testAngle, lookAheadDist, worldObjects, geese)) {
                    moveAngle = testAngle;
                    break;
                }
            }

            System.out.println("Made it here 2");

            float dirX = (float) Math.cos(moveAngle);
            float dirY = (float) Math.sin(moveAngle);

            float newX = this.x + (dirX * speed * delta);
            float newY = this.y + (dirY * speed * delta);

            if (checkForCollisions(newX, this.y, worldObjects)) {
                this.x = newX;
            }

            if (checkForCollisions(this.x, newY, worldObjects)) {
                this.y = newY;
            }

            

            this.hitbox.setPosition(this.x, this.y);
        }
    }
    
    public Shape2D getHitbox() {
        return hitbox;
    }

    private boolean isPathClear(float angle, float rayLength, Array<WorldObject> worldObjects, Array<Goose> geese) {
        float dirX = (float) Math.cos(angle);
        float dirY = (float) Math.sin(angle);
        float stepSize = this.size / 2f; 

        for (float currentDistance = stepSize; currentDistance <= rayLength; currentDistance += stepSize) {
            float testX = this.x + (dirX * currentDistance);
            float testY = this.y + (dirY * currentDistance);

            if (!checkForCollisions(testX, testY, worldObjects)) {
                return false;
            }

            if (!checkForCollisionsWithGeese(testX, testY, geese)) {
                return false;
            }
        }
        

        return true; 
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, x - size, y - size, size*2, size*2);
    }

    @Override
    public void drawHitbox(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.circle(x, y, size, 8);
    }
}