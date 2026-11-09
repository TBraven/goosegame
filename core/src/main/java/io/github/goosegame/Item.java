package io.github.goosegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Item {
    
    protected float x;
    protected float y;

    protected InventorySlot invSlot;

    private float size = 50;

    protected Texture texture;



    protected int state = 1;

    public Item(int n_x, int n_y) {
        x = n_x;
        y = n_y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setState(int n_state) {
        state = n_state;
    }

    public int getState() {
        return state;
    }



    public void setInventorySlot(InventorySlot n_invSlot) {
        invSlot = n_invSlot;
    }

    public void drawItem(SpriteBatch spriteBatch) {

        // If item is not dropped / placed, dont draw.
        if (state < 1) {
            return;
        }
        
        spriteBatch.draw(texture, x - size / 2, y - size / 2, size, size);

    }

    public double calculateDistanceFromPlayer(Player player) {

        float x_distance = player.getX() - x;
        float y_distance = player.getY() - y;

        double distance = Math.sqrt(x_distance * x_distance + y_distance * y_distance);

        return distance;
    }

    public void useItem(Player player) {
    };

}
