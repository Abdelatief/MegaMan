package com.megaman.game.Sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.MegamanGame;
import com.megaman.game.Tools.GameObject;


public class Bullet extends GameObject
{
    public  float speed ;
    public int damage;
    private  Texture texture;
    private  TextureRegion textureRegion;
    private float x, y;
    private final float direction;
    private final float offset;
    private float timer;
    private  int r;
    private float yinc;
    private String User;
    static float xx;

    public Bullet(float x, float y, boolean rightDirection, World world, float speed, int damage, String photo, int xpos, int ypos, int width, int height, int r, float yinc, String User)
    {
        super(new Vector2(x, y), world);
        xx=.4f;
        this.User=User;
        this.speed=speed;
        this.damage=damage;
        this.r=r;
        this.yinc=yinc;
        this.x = x;
        this.y = y;
        timer = 0;
        if (texture == null)
            texture = new Texture(photo);
        if (textureRegion == null)
            textureRegion = new TextureRegion(texture, xpos, ypos, width, height);
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
        setB2body(define());

    }

    public Body define()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        Body b2body = getWorld().createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(r / MegamanGame.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);
        if(User.equals("MegaMan"))
        b2body.setGravityScale(0);
        return b2body;
    }

    public void update(float dt)
    {
        super.update(dt);

        if (!isSetToDestroy()) {
            timer += dt;
            x += speed * dt * direction;

            if (User.equals("Enemy") || User.equals("MegaMan")) {
                if (timer > 3)
                    setSetToDestroy(true);
                float transformX = x + offset + 0.02f * direction;
                float transformY = y + 0.02f;
                setPosition(transformX, transformY);
                setRegion(textureRegion);
                getB2body().setTransform(x + offset + 0.02f, y + yinc, getB2body().getAngle());//added numbers to adjust the b2body to the sprite
            }
            else
            {
                if (timer >8)
                    setSetToDestroy(true);
                else
                    getB2body().setTransform(x+offset+0.02f, y+xx,getB2body().getAngle());
                xx-=.002f;
            }
        }


    }
    public void render(SpriteBatch batch)
    {
        batch.draw(textureRegion, x+offset, y+xx, 0.1f, 0.1f);
    }
    public int getDamage()
    {
        return damage;
    }
    public String getUser() {
        return User;
    }
}