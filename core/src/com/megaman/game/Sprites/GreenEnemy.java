package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.Screen.Playscreen;

public class GreenEnemy extends NormalEnemy {
    public GreenEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y, int ValueToIncreaseScore) {
        super(world, screen, spriteSheet, maxHealth, x, y, 6, 50, 500, 40, 45,ValueToIncreaseScore);
    }
}
