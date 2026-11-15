package io.github.goosegame;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class InventorySlot {
    
    private Item myItem;
    private int index;

    public InventorySlot(int n_index) {
        index = n_index;
    }

    public void setItem(Item n_item) {
        myItem = n_item;
    }

    public Item getItem() {
        return myItem;
    }


    public void clearItem() {
        myItem = null;
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        
        float width = Gdx.graphics.getWidth() / 13;

        float x = 0;

        if (index == 1) {
            x = width * 4;
        }
        else if (index == 2) {
            x = width * 6;
        }
        else if (index == 3) {
            x = width * 8;
        }


        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(x, 0, width, width);

        if (myItem != null) {
            spriteBatch.draw(myItem.getTexture(), x, 0, width, width);
        }
        




    }
}
