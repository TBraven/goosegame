package io.github.goosegame.Items;

import io.github.goosegame.Item;
import io.github.goosegame.Player;

import com.badlogic.gdx.graphics.Texture;

public class Seeds extends Item {
    
    
    public Seeds(int n_x, int n_y) {
        super(n_x, n_y);

        texture = new Texture("seeds.png");
    }
    

    @Override
    public void useItem(Player player) {
        System.out.println("Seeds used!");
        
        state = 2;
        texture = new Texture("openseeds.png");

        x = player.getX();
        y = player.getY();

        invSlot.clearItem();
    }
}
