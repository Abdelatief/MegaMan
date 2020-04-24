package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;

public class StrongEnemy extends Enemy {
    public StrongEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth) {
        super(world, screen, spriteSheet, maxHealth);
    }
}
