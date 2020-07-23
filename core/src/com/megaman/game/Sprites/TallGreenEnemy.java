package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.Screen.Playscreen;

public class TallGreenEnemy extends StrongEnemy {
    public TallGreenEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y,int ValueToIncreaseScore) {
        super(world, screen, spriteSheet, maxHealth,x,y,5,50,348,50,50,ValueToIncreaseScore);
    }
}
