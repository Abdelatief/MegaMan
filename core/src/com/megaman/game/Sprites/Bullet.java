package com.megaman.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.MegamanGame;


public class Bullet extends Sprite
{
    public float SPEED = 0.7f;
    public int damage = 10;
    private static Texture texture;
    private static TextureRegion textureRegion;
    private float x, y;
    private final World world;
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
        setBounds(x/MegamanGame.PPM, y/MegamanGame.PPM, 15/MegamanGame.PPM, 15/MegamanGame.PPM);
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
        b2body.createFixture(fdef).setUserData(this);
        b2body.setGravityScale(0);
    }

    public void update(float dt)
    {
        timer += dt;
        x += SPEED * dt * direction;
        if (timer > 3)
            remove = true;
        float transformX = x + offset + 0.02f * direction;
        float transformY = y + 0.04f;
        setPosition(transformX, transformY);
        setRegion(textureRegion);
        b2body.setTransform(x+offset+0.02f, y+0.09f, b2body.getAngle());    //added numbers to adjust the b2body to the sprite
    }

    public void draw(Batch batch)
    {
        super.draw(batch);
    }

    public int getDamage()
    {
        return damage;
    }

    @Override
    public float getX() {
        return b2body.getPosition().x;
    }
}