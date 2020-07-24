package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.MegamanGame;
import com.megaman.game.Scenes.Hud;
import com.megaman.game.Screen.Playscreen;

import java.util.ArrayList;

public abstract class Enemy extends Entity
{
    private Vector2 velocity = new Vector2(-1.2f,-4);;
    private Array<TextureRegion> frames;
    private Animation<TextureRegion> walkAnimation;
    private float x;
    private int ValueToIncreaseScore;
    private float animstart = 1;
    private ArrayList<Bullet> Bbullets = new ArrayList<Bullet>();

    public Enemy(World world, Playscreen screen, TextureRegion texture, int maxHealth, float x, float y, int NumberOfAnimation,int XPositionInSpriteSheet,int YPositionInSpriteSheet,int width,int height,int ValueToIncreaseScore) {
        super(world, screen, texture, maxHealth,x,y);
        this.x=x;
        currentState = State.IDLE;
        previousState = State.IDLE;
        frames = new Array<TextureRegion>();
        runningRight = true;
        this.ValueToIncreaseScore=ValueToIncreaseScore;
        for(int i =NumberOfAnimation; i>0; i--) {
            frames.add(new TextureRegion(texture, i*XPositionInSpriteSheet, YPositionInSpriteSheet, width, height));

        }walkAnimation = new Animation(0.2f, frames);
        stateTimer = 0;
        setB2body(define());
        if(this instanceof Bosses)
            setBounds(getX(), getY(), 100 / MegamanGame.PPM, 150 / MegamanGame.PPM);
        else
            setBounds(getX(), getY(), 70 / MegamanGame.PPM, 45 / MegamanGame.PPM);
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
        stateTimer += dt;

        if (!isSetToDestroy() && !isDestroyed()) {
            getB2body().setLinearVelocity(velocity);
            setPosition(getB2body().getPosition().x - getWidth() / 2, getB2body().getPosition().y - getHeight()/3 );
            setRegion(walkAnimation.getKeyFrame(stateTimer, true));
            //To make enemy reverses its movement direction.

            if(this.getX() < x-2) {
                velocity.x = 1;
                for(int i = 0;i < frames.size;i++)//Makes enemy looks to right
                    frames.get(i).flip(true,false);
                runningRight = true;

            }
            else if(this.getX() > x-1) {
                velocity.x = -1;

                for(int i = 0;i < frames.size;i++)//Makes enemy looks to left
                {
                    if(frames.get(i).isFlipX())
                        frames.get(i).flip(true,false);
                }
                runningRight = false;
            }
            if (!(this instanceof Bosses))
            {
                if (stateTimer >= animstart) {
                    Bbullets.add(new Bullet(this.getB2body().getPosition().x, this.getB2body().getPosition().y, this.runningRight, getWorld(), .4f,100, "BM.png",204, 320, 20, 20, 5,0.01f,"Enemy"));
                    animstart += 1;
                }
                ArrayList<Bullet> removeBBullets = new ArrayList<Bullet>();
                for (Bullet bullet : Bbullets) {
                    if (bullet.isSetToDestroy())
                        removeBBullets.add(bullet);
                    bullet.update(dt);
                }
                Bbullets.removeAll(removeBBullets);
            }



        }
    }

    public void BulletRender(MegamanGame game) {
        for (Bullet bullet : Bbullets)
            bullet.draw(game.batch);
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
    @Override
    public void die() {
        super.die();
        Hud.IncreaseScore(ValueToIncreaseScore);
    }
}
