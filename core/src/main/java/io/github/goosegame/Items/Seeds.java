package io.github.goosegame.Items;

import io.github.goosegame.Item;
import io.github.goosegame.Player;
import io.github.goosegame.Goose;
import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Seeds extends Item {
    
    private BitmapFont font = new BitmapFont();

    private float health = 10;
    private float radius = 200;

    public Seeds(int n_x, int n_y) {
        super(n_x, n_y);

        texture = new Texture("seeds.png");
    }
    

    @Override
    public void useItem(Player player, Array<Goose> geese) {
        System.out.println("Seeds used!");

        state = 2;
        texture = new Texture("openseeds.png");

        x = player.getX();
        y = player.getY();

        invSlot.clearItem();
    }
    

    @Override
    public void doPersistent(Player player, Array<Goose> geese, ShapeRenderer shapeRenderer, SpriteBatch batch, float delta) {

        if (state == 2) {

            health = health - delta;

            shapeRenderer.setColor(1, 0, 0, 1);
            shapeRenderer.circle(x, y, radius, 32);

            for (Goose goose : geese) {
                if (calculateDistanceFromGoose(goose) < radius) {
                    goose.targetedItem = this;
                }
            }

            if (health < 0) {
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

        if (state == 2) {
            font.draw(spriteBatch, String.valueOf(Math.round(health)), x, y - 20);
        }

    }
}
