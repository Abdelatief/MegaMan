package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;

public class Boss extends Enemy {
    public Boss(World world, Playscreen screen, String spriteSheet, int maxHealth,int x, int y) {
        super(world, screen, spriteSheet, maxHealth,x,y);
    }
}
