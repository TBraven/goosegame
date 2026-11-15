package io.github.goosegame.Items;

import io.github.goosegame.Item;
import io.github.goosegame.Player;
import io.github.goosegame.Goose;
import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Megaphone extends Item {

    private float radius = 200;
    private float displayRadius = 0;

    public Megaphone(int n_x, int n_y) {
        super(n_x, n_y);

        texture = new Texture("megaphone.png");
    }
    
    @Override
    public void useItem(Player player, Array<Goose> geese) {
        System.out.println("Megaphone used!");

        state = 2;

        x = player.getX();
        y = player.getY();

        invSlot.clearItem();

        for (Goose goose : geese) {
            if (goose.distanceFromPlayer(player) < radius) {
                goose.scare(5);
            }
        }
    }

    public void doPersistent(Player player, Array<Goose> geese, ShapeRenderer shapeRenderer, SpriteBatch batch,
            float delta) {
        
        if (state == 2) {
            shapeRenderer.setColor(1, 1, 1, 1);

            displayRadius += (radius * delta);

            shapeRenderer.circle(x, y, displayRadius);

            shapeRenderer.setColor(1, 0, 0, 1);
            shapeRenderer.circle(x, y, radius);

            if (displayRadius > radius) {
                state = -1;
            }
        }
    }
    
    
    @Override 
    public void drawItem(SpriteBatch spriteBatch) {

        // If item is not dropped / placed, dont draw.
        if (state < 1) {
            return;
        }
        
        System.out.println("Drawing sprite!");
        spriteBatch.draw(texture, x - size / 2, y - size / 2, size, size);

    }
}
