package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.Screen.Playscreen;

public abstract class Entity extends Sprite
{
    enum State{FALLING, JUMPING, STANDING, RUNNING, IDLE, INTRO, RUNANDSHOOT, SHOOTING};
    private int maxHealth;
    private int currentHealth;
    public State currentState;
    public State previousState;
    public World world;
    public Playscreen screen;
    public Body b2body;
    public float stateTimer;
    public boolean runningRight;
    private boolean setToDestroy;
    private boolean destroyed;

    public Entity(World world, Playscreen screen, String spriteSheet, int maxHealth)
    {
        super(screen.getAtlas().findRegion(spriteSheet));
        this.world = world;
        this.screen = screen;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.setToDestroy = false;
        this.destroyed = false;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public abstract void define();

    public abstract TextureRegion getFrame(float dt);

    public abstract State getState();

    protected abstract void animationSetup();

//    public Array<TextureRegion> GetArrayFromSheet()
//    {
//        return null;
//    }

    private Array<TextureRegion> GetArrayFromSheet(int x,
                                                   int y,
                                                   float width,
                                                   float height,
                                                   float spritesheetWidth,
                           
                                                   float spritesheetHeight,
                                                   int numberOfSprites, boolean continuous)
    {
        Array<TextureRegion> array = new Array<TextureRegion>();
        for (int i=0; i<numberOfSprites; i++)
        {
            if (x + width > spritesheetWidth)
            {
                x = 0;
                y += width;
            }
            array.add(new TextureRegion(getTexture(), x, y, width, height));
            x += width;
        }
        if (continuous) {
            for (int i = array.size - 2; i > 4; i--)
            {
                array.add(array.get(i));
            }
        }
        return array;
    }

    public void update(float dt)
    {
        if (setToDestroy) {
            world.destroyBody(b2body);
            destroyed = true;
        }
    }

    public void applyDamage(int damage)
    {
        currentHealth -= damage;
        if (currentHealth <= 0)
            die();
    }

    public void die()
    {
        setToDestroy = true;
    }

    public void draw(Batch batch)
    {
        if (!setToDestroy)
            super.draw(batch);
    }

    public boolean getSetToDestroy()
    {
        return setToDestroy;
    }

    public boolean getDestroyed()
    {
        return destroyed;
    }
}
