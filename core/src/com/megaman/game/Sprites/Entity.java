package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
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
//        super(screen.getAtlas().findRegion(spriteSheet));
        super(new Vector2(x, y), world, screen.getAtlas().findRegion(spriteSheet));
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

//    public Array<TextureRegion> GetArrayFromSheet()
//    {
//        return null;
//    }

    private Array<TextureRegion> GetArrayFromSheet(int x, int y, float width, float height, float spritesheetWidth, float spritesheetHeight, int numberOfSprites, boolean continuous)
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

//    public void update(float dt)
//    {
//        if (setToDestroy) {
//            world.destroyBody(b2body);
//            destroyed = true;
//        }
//    }

//    public void draw(Batch batch)
//    {
//        if (!setToDestroy)
//            super.draw(batch);
//    }

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
