package com.megaman.game.Sprites;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.Screen.Playscreen;
import com.megaman.game.Tools.GameObject;

public abstract class Entity extends GameObject
{
    enum State{FALLING, JUMPING, STANDING, RUNNING, IDLE, INTRO, RUNANDSHOOT, SHOOTING};
    private int maxHealth;
    private int currentHealth;
    public State currentState;
    public State previousState;
    public Playscreen screen;
    public float stateTimer;
    public boolean runningRight;

    public Entity(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y)
    {
        super(new Vector2(x, y), world, screen.getAtlas().findRegion(spriteSheet));
        setB2body(define());
        this.screen = screen;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        setPosition(x, y);
    }
    public Entity(World world, Playscreen screen, TextureRegion texture, int maxHealth, float x, float y)
    {
        super(new Vector2(x, y), world, texture);
        setB2body(define());
        this.screen = screen;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        setPosition(x, y);
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public abstract Body define();

    public abstract TextureRegion getFrame(float dt);

    public abstract State getState();

    protected abstract void animationSetup();


    public void applyDamage(int damage)
    {
        currentHealth -= damage;
        if (currentHealth <= 0)
            die();
    }

    public void die()
    {
        setSetToDestroy(true);
    }

}
