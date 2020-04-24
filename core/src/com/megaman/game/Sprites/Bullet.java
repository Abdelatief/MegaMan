package com.megaman.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megaman.game.MegamanGame;

public class Bullet
{
    public float SPEED = 2f;
    private static Texture texture;
    private static TextureRegion textureRegion;
    private float x, y;
    private float direction;
    private float offset;
    private float timer;
    public boolean remove;


    public Bullet(float x, float y, boolean rightDirection)
    {
        this.x = x;
        this.y = y;
        timer = 0;
        if (texture == null)
            texture = new Texture("Buster.gif");
        if (textureRegion == null)
            textureRegion = new TextureRegion(texture, 410, 10, 20, 20);
        if (rightDirection)
        {
            direction = 1;
            offset = 0.02f;
            if (textureRegion.isFlipX())
                textureRegion.flip(true, false);
        }
        else
        {
            direction = -1;
            offset = -0.18f;
            if (!textureRegion.isFlipX())
                textureRegion.flip(true, false);
        }
    }

    public void update(float dt)
    {
        timer += dt;
        x += SPEED * dt * direction;
        if (timer > 3)
            remove = true;
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(textureRegion, x+offset, y+0.04f, 0.1f, 0.1f);
    }
}
