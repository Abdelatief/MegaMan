package com.megaman.game.Tools;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


public class GameObject extends Sprite
{
    private Vector2 position;
    private Body b2body;
    private final World world;
    private boolean setToDestroy;
    private boolean destroyed;


    public GameObject(Vector2 position,  Body b2body, World world, boolean setToDestroy, boolean destroyed)
    {
        this.position = position;
        this.b2body = b2body;
        this.world = world;
        this.setToDestroy = setToDestroy;
        this.destroyed = destroyed;
    }

    public GameObject(Vector2 position, Body b2body, World world)
    {
        this(position, b2body, world, false, false);
    }

    public GameObject(Vector2 position, World world)
    {
        this(position, null, world, false, false);
    }

    public GameObject(Vector2 position, World world, TextureRegion textureRegion)
    {
        super(textureRegion);
        this.position = position;
        this.world = world;
    }

    public void update(float dt)
    {
        if (setToDestroy && b2body != null)
        {
            world.destroyBody(b2body);
            destroyed = true;
        }
    }

    public void draw(Batch batch)
    {
        if (!setToDestroy)
            super.draw(batch);
    }

    // Getters and setters

    public World getWorld() {
        return world;
    }

    public boolean isSetToDestroy() {
        return setToDestroy;
    }

    public void setSetToDestroy(boolean setToDestroy) {
        this.setToDestroy = setToDestroy;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public Body getB2body()
    {
        return b2body;
    }

    public void setB2body(Body b2body)
    {
        this.b2body = b2body;
    }
}
