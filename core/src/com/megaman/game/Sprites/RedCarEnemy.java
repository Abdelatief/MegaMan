package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;

public class RedCarEnemy extends StrongEnemy {
    public RedCarEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y) {
        super(world, screen, spriteSheet, maxHealth,x,y,4,90,650,80,45,200);
    }
}
