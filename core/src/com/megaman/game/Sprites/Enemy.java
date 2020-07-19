package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.Observer.Publisher;
import com.megaman.game.Screen.Playscreen;

public class Enemy extends Entity
{
    Publisher publisher;

    public Enemy(World world, Playscreen screen, String spriteSheet, int maxHealth) {
        super(world, screen, spriteSheet, maxHealth);
        this.publisher = new Publisher();
    }

    @Override
    public void define() {

    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public TextureRegion getFrame(float dt) {
        return null;
    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    protected void animationSetup() {

    }


    public Array<TextureRegion> GetArrayFromSheet()
    {
        return null;
    }
}
