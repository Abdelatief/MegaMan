package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;

public class BirdEnemy extends NormalEnemy {
    public BirdEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y,int ValueToIncreaseScore) {
        super(world, screen, spriteSheet, maxHealth,x,y,2,100,100,50,90,ValueToIncreaseScore);
    }

}