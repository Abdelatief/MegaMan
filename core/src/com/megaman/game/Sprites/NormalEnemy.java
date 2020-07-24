package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;

public  class NormalEnemy extends Enemy {
    public NormalEnemy(World world, Playscreen screen, TextureRegion texture, int maxHealth, float x, float y, int NumberOfAnimation, int XPositionInSpriteSheet, int YPositionInSpriteSheet, int width, int height, int ValueToIncreaseScore) {
        super(world, screen, texture, maxHealth, x, y, NumberOfAnimation, XPositionInSpriteSheet, YPositionInSpriteSheet, width, height, ValueToIncreaseScore);
    }
}
