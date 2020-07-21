package com.megaman.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.MegamanGame;
import com.megaman.game.Tools.GameObject;


public class Bullet extends GameObject
{
    public final float SPEED = 0.7f;
    public int damage = 10;
    private static Texture texture;
    private static TextureRegion textureRegion;
    private float x, y;
    private final float direction;
    private final float offset;
    private float timer;


    public Bullet(float x, float y, boolean rightDirection, World world)
    {
        super(new Vector2(x, y), world);
        setB2body(define());
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

        setBounds(x/MegamanGame.PPM, y/MegamanGame.PPM, 15/MegamanGame.PPM, 15/MegamanGame.PPM);
    }

    public Body define()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        Body b2body = getWorld().createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(3 / MegamanGame.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);
        b2body.setGravityScale(0);
        return b2body;
    }

    public void update(float dt)
    {
        super.update(dt);
        if (!isSetToDestroy()) {
            timer += dt;
            x += SPEED * dt * direction;
            if (timer > 3)
                setSetToDestroy(true);
            float transformX = x + offset + 0.02f * direction;
            float transformY = y + 0.04f;
            setPosition(transformX, transformY);
            setRegion(textureRegion);
            getB2body().setTransform(x + offset + 0.02f, y + 0.09f, getB2body().getAngle());    //added numbers to adjust the b2body to the sprite
        }
    }

    public int getDamage()
    {
        return damage;
    }
}