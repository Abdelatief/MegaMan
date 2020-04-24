package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.Screen.Playscreen;

public abstract class Entity extends Sprite
{
    enum State{FALLING, JUMPING, STANDING, RUNNING, IDLE, INTRO, RUNANDSHOOT};
    private int maxHealth;
    private int currentHealth;
    public State currentState;
    public State previousState;
    public World world;
    public Playscreen screen;
    public Body b2body;
    public float stateTimer;
    boolean runningRight;

    public Entity(World world, Playscreen screen, String spriteSheet, int maxHealth)
    {
        super(screen.getAtlas().findRegion(spriteSheet));
        this.world = world;
        this.screen = screen;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public abstract void define();

    public abstract void update(float dt);

    public abstract TextureRegion getFrame(float dt);

    public abstract State getState();

    protected abstract void animationSetup();

    public Array<TextureRegion> GetArrayFromSheet()
    {
        return null;
    }

    public void applyDamage()
    {

    }

    public void die()
    {

    }
}
