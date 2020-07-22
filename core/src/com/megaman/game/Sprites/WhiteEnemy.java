package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.Screen.Playscreen;

public class WhiteEnemy  extends Enemy {
    public WhiteEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y) {
        super(world, screen, spriteSheet, maxHealth,x,y,4,50,1050,50,60,600);
    }
}
