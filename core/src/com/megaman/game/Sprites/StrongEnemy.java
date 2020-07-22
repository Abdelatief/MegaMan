package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;

public abstract class StrongEnemy extends Enemy {
//    public StrongEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, int x, int y) {
//        super(world, screen, spriteSheet, maxHealth, x, y,0,0,0,0,0,0);
//    }
//
//    public StrongEnemy(World world, Playscreen screen, float x, float y, int NumberOfAnimation,
//                       int xPositionInSpriteSheet, int YPositionInSpriteSheet, float)

    public StrongEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y, int NumberOfAnimation,int XPositionInSpriteSheet,int YPositionInSpriteSheet,int width,int height,int ValueToIncreaseScore)
    {
        super(world, screen, spriteSheet, maxHealth, x, y, NumberOfAnimation, XPositionInSpriteSheet, YPositionInSpriteSheet, width, height, ValueToIncreaseScore);
    }

    @Override
    public void applyDamage(int damage) {
        setCurrentHealth(getCurrentHealth() - (int)(0.70f * damage));
        if (getCurrentHealth() <= 0)
            die();
    }
}
