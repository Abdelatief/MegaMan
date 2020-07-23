package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.MegamanGame;
import com.megaman.game.Scenes.Hud;
import com.megaman.game.Screen.Playscreen;

public abstract class Enemy extends Entity
{
    private Vector2 velocity = new Vector2(-1.2f,-4);;
    private Array<TextureRegion> frames;
    private Animation<TextureRegion> walkAnimation;
    private float x;
    private int ValueToIncreaseScore;

    public Enemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y, int NumberOfAnimation,int XPositionInSpriteSheet,int YPositionInSpriteSheet,int width,int height,int ValueToIncreaseScore) {
        super(world, screen, spriteSheet, maxHealth,x,y);
        this.x=x;
        currentState = State.IDLE;
        previousState = State.IDLE;
        frames = new Array<TextureRegion>();
        runningRight = true;
        this.ValueToIncreaseScore=ValueToIncreaseScore;
        for(int i =NumberOfAnimation; i>0; i--) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion(spriteSheet), i*XPositionInSpriteSheet, YPositionInSpriteSheet, width, height));

        }walkAnimation = new Animation(0.2f, frames);
        stateTimer = 0;
        setB2body(define());
        if(this instanceof BlueBoss)
            setBounds(getX(), getY(), 100 / MegamanGame.PPM, 150 / MegamanGame.PPM);
        else
            setBounds(getX(), getY(), 70 / MegamanGame.PPM, 45 / MegamanGame.PPM);
    }

    public Enemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y,int ValueToIncreaseScore)
    {
        super(world, screen, spriteSheet, maxHealth, x, y);
        this.x=x;
        currentState = State.IDLE;
        previousState = State.IDLE;
        frames = new Array<TextureRegion>();
        runningRight = true;
        this.ValueToIncreaseScore=ValueToIncreaseScore;
        stateTimer = 0;
        setB2body(define());

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
            if(this instanceof BlueBoss)
            {
                if(this.getX() < x-2.4f) {
                    velocity.x = 1;
                    for(int i = 0;i < frames.size;i++)//Makes enemy looks to right
                        frames.get(i).flip(true,false);

                }
                else if(this.getX() > x-1) {
                    velocity.x = -1;

                    for(int i = 0;i < frames.size;i++)//Makes enemy looks to left
                    {
                        if(frames.get(i).isFlipX())
                            frames.get(i).flip(true,false);
                    }
                }
            }else
            {
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


            }

        }
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


    public void setWalkAnimation(Animation<TextureRegion> walkAnimation) {
        this.walkAnimation = walkAnimation;
    }

    public Array<TextureRegion> getFrames() {
        return frames;
    }

    /* public Array<TextureRegion> GetArrayFromSheet()
    {
        return null;
    }*/
}
