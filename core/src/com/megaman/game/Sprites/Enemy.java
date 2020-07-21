package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.MegamanGame;
import com.megaman.game.Observer.Publisher;
import com.megaman.game.Screen.Playscreen;

public abstract class Enemy extends Entity
{
    Publisher publisher;
    public Vector2 velocity;

    public Enemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y) {
        super(world, screen, spriteSheet, maxHealth,x,y);
        setB2body(define());
        this.publisher = new Publisher();
        velocity = new Vector2(-1.2f,-4);
    }

    @Override
    public Body define()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getX());
        bdef.type = BodyDef.BodyType.DynamicBody;
        Body b2body = getWorld().createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / MegamanGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
        return b2body;
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


    public Array<TextureRegion> GetArrayFromSheet()
    {
        return null;
    }
}
