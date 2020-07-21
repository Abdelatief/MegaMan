package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;
public abstract class StrongEnemy extends Enemy {
    public StrongEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, int x, int y) {
        super(world, screen, spriteSheet, maxHealth, x, y,0,0,0,0,0,0);
    }
}
