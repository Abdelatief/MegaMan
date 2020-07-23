package com.megaman.game.Sprites;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;

import java.util.ArrayList;

public class Boss extends Bosses {


    public Boss(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y,int ValueToIncreaseScore) {
        super(world, screen, spriteSheet, maxHealth, x, y, ValueToIncreaseScore);
        for (int i = 0; i < 3; i++) {
            getFrames().add(new TextureRegion(screen.getAtlas2().findRegion("pngkit_megaman-sprite-png"), i * 68, 285, 68, 109));
        }
        setWalkAnimation(new Animation(0.2f,  getFrames()));
        setBounds(getX(), getY(), 68 / MegamanGame.PPM, 110 / MegamanGame.PPM);

    }


}