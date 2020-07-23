package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;

public class RobotEnemy extends StrongEnemy {
    public RobotEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y, int ValueToIncreaseScore) {
        super(world, screen, spriteSheet, maxHealth,x,y,5,50,56,45,50,ValueToIncreaseScore);
    }

}
