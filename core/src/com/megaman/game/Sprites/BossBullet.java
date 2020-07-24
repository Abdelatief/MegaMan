package com.megaman.game.Sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;

public class BossBullet extends Sprite {
    public float SPEED = .4f;
    public int damage = 100;
    private static Texture texture;
    private static TextureRegion textureRegion;
    private float x, y;
    private World world;
    private Body b2body;
    private float direction;
    private float offset;
    private float timer;
    public boolean remove;
    private boolean rightDirection;
    static float xx;
    private Array<TextureRegion> frames;
    private Animation<TextureRegion> walkAnimation;

    public BossBullet(float x, float y, boolean rightDirection, World world, Playscreen screen)
    {
        this.x = x;
        this.y = y;
        this.rightDirection=rightDirection;
        this.world = world;
        timer = 0;

        if (texture == null)
            texture = new Texture("Buster.gif");
        if (textureRegion == null)
            textureRegion = new TextureRegion(texture, 70, 250, 25, 20);
           // textureRegion = new TextureRegion(texture, 204, 330, 30, 30);

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
        xx=.4f;
    }

    public void define()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MegamanGame.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void update(float dt)
    {

        timer += dt;

        x += SPEED * dt * direction;

        if (timer > 5)
            remove = true;
        else
        b2body.setTransform(x+offset+0.02f, y+xx,b2body.getAngle());
        xx-=.002f;

        //b2body.setTransform(x+offset+0.02f, y+0.09f, b2body.getAngle());    //added numbers to adjust the b2body to the sprite
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(textureRegion, x+offset, y+xx, 0.1f, 0.1f);
    }

    public int getDamage()
    {
        return damage;
    }
}
