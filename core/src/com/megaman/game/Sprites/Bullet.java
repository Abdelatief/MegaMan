package com.megaman.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.MegamanGame;

public class Bullet
{
    public float SPEED = 0.7f;
    private static Texture texture;
    private static TextureRegion textureRegion;
    private float x, y;
    private World world;
    private Body b2body;
    private float direction;
    private float offset;
    private float timer;
    public boolean remove;


    public Bullet(float x, float y, boolean rightDirection, World world)
    {
        this.x = x;
        this.y = y;
        this.world = world;
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
        define();
    }

    public void define()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(3 / MegamanGame.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        b2body.createFixture(fdef);
    }

    public void update(float dt)
    {
        timer += dt;
        x += SPEED * dt * direction;
        if (timer > 3)
            remove = true;

        b2body.setTransform(x+offset+0.02f, y+0.09f, b2body.getAngle());    //added numbers to adjust the b2body to the sprite
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(textureRegion, x+offset, y+0.04f, 0.1f, 0.1f);
    }
}
