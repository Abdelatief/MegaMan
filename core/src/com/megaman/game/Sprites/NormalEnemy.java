package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;

public abstract class NormalEnemy extends Enemy
{
    public NormalEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, int x, int y) {
        super(world, screen, spriteSheet, maxHealth,x,y);
    }
}
