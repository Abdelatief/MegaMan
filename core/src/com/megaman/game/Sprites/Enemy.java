package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.Observer.Publisher;
import com.megaman.game.Screen.Playscreen;

public class Enemy extends Entity
{
    Publisher publisher;
    public Vector2 velocity;

    public Enemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y) {
        super(world, screen, spriteSheet, maxHealth,x,y);
        this.publisher = new Publisher();
        define();
        velocity = new Vector2(-1.2f,-4);
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

    public void reverseVelovity(boolean x ,boolean y)
    {
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
    public Array<TextureRegion> GetArrayFromSheet()
    {
        return null;
    }
}
