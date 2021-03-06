package com.megaman.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.Scenes.Hud;
import com.megaman.game.Screen.Playscreen;
import com.megaman.game.MegamanGame;

public class MegaMan extends Entity {
    private Animation run;
    private Animation jump;
    private Animation fall;
    private Animation idle;
    private Animation shoot;
    private Animation intro;
    private Animation runAndShoot;
    private float timer;
    private boolean introAnimationPlayed;
    private boolean isAnimationPlaying;
    private float animationStart;
    private Sound shootSound;//shoot sound
    private int jumps = 0;
    private static int Score;

    public MegaMan(World  world , Playscreen screen)
    {
        super(world, screen, "mega_man", 1000, 32/ MegamanGame.PPM,100/ MegamanGame.PPM);
        // register it to the publisher later, when the classes are implemented properly
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        timer = 0;
        runningRight = true;
        introAnimationPlayed = true;
        animationSetup();

        setBounds(0,0,32/ MegamanGame.PPM,40/ MegamanGame.PPM);     //Sets the position and size of the sprite when drawn, before scaling and rotation are applied.
        setPosition(getB2body().getPosition().x - getWidth() / 2, getB2body().getPosition().y - getHeight()/3 );        // Sets the position where the sprite will be drawn.
        getB2body().setTransform(32/MegamanGame.PPM, 100/MegamanGame.PPM, getB2body().getAngle());          // Set the position of the body's origin and rotation.
        shootSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/bullet_whizzing_by-Mike_Koenig-2005433595.wav"));
    }

    @Override
    public Body define()
    {
        BodyDef bdef=new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type=BodyDef.BodyType.DynamicBody;
        Body b2body = getWorld().createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(getX(), getY()+5/MegamanGame.PPM));
        shape.setRadius(8 / MegamanGame.PPM );
        fdef.shape=shape;
        b2body.createFixture(fdef).setUserData(this);
        EdgeShape leg = new EdgeShape();
        leg.set(new Vector2(-2 / MegamanGame.PPM,-2/ MegamanGame.PPM),new Vector2(2 / MegamanGame.PPM,-2/ MegamanGame.PPM));//line between 2 different points from -2 to 2 of y = -2
        fdef.shape = leg;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("leg");
        return b2body;
    }

    public void update(float dt)
    {
        timer += dt;
        if (timer > 0.64)
            introAnimationPlayed = false;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            shootSound.play();
            if (getB2body().getLinearVelocity().x != 0)
            {
                animationStart = timer;
                isAnimationPlaying = true;
                currentState = State.RUNANDSHOOT;
            }
            else
            {
                animationStart = timer;
                isAnimationPlaying = true;
                currentState = State.SHOOTING;
            }

        }
        if (isAnimationPlaying)
        {
            float animationTime = 0;
            if (currentState == State.SHOOTING)
                animationTime = 0.15f;
            else if (currentState == State.RUNANDSHOOT)
                animationTime = 0.75f;

            if (timer - animationStart >= animationTime)
            {
                isAnimationPlaying = false;
            }
        }


        setPosition(getB2body().getPosition().x-getWidth()/2,getB2body().getPosition().y-getHeight()/8);
        setRegion(getFrame(dt));

        if (getState() != State.JUMPING && getState() != State.FALLING)
            jumps = 0;
    }

    public TextureRegion getFrame(float dt)
    {
        currentState = getState();
        TextureRegion region;
        switch (currentState){
            case RUNNING:
                region = (TextureRegion) run.getKeyFrame(stateTimer, true);
                break;

            case JUMPING:
                region = (TextureRegion) jump.getKeyFrame(stateTimer);
                break;

            case FALLING:
                region = (TextureRegion) fall.getKeyFrame(stateTimer);
                break;

            case INTRO:
                region = (TextureRegion) intro.getKeyFrame(stateTimer);
                break;

            case SHOOTING:
                region = (TextureRegion) shoot.getKeyFrame(stateTimer);
                break;

            case RUNANDSHOOT:
                region = (TextureRegion) runAndShoot.getKeyFrame(stateTimer);
                break;

            default:
                region = (TextureRegion) idle.getKeyFrame(stateTimer);
                break;
        }

        if ((getB2body().getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX())
        {
            region.flip(true, false);
            runningRight = false;
        }
        else if ((getB2body().getLinearVelocity().x > 0 || runningRight) && region.isFlipX())
        {
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState()
    {
        if (isAnimationPlaying)
            return currentState;
        if (introAnimationPlayed)
            return State.INTRO;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            return State.SHOOTING;
        if (getB2body().getLinearVelocity().y > 0)
            return State.JUMPING;
        else if (getB2body().getLinearVelocity().y < 0)
            return State.FALLING;
        else if (getB2body().getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.IDLE;
    }

    @Override
    protected void animationSetup()
    {
        int width = 35;
        int height = 45;
        run = new Animation(0.05f, GetArrayFromSheet(8, 200, 11, true));
        jump = new Animation(0.25f, GetArrayFromSheet(8, 400, 3, false));
        fall = new Animation(0.25f, GetArrayFromSheet(158, 400, 4, false));
        idle = new Animation(0.25f, GetArrayFromSheet(8, 50, 8, false));
        intro = new Animation(0.05f, GetArrayFromSheet(8, 0, 8, false));
        runAndShoot = new Animation(0.05f, GetArrayFromSheet(258,250, 11, true));
        shoot = new Animation(0.05f, GetArrayFromSheet(8, 100, 3, false));
    }

    private Array<TextureRegion> GetArrayFromSheet(int x, int y, int numberOfSprites, boolean continuous)
    {
        Array<TextureRegion> array = new Array<TextureRegion>();
        for (int i=0; i<numberOfSprites; i++)
        {
            if (x > 358)
            {
                x = 8;
                y += 50;
            }
            array.add(new TextureRegion(getTexture(), x, y, 40, 45));
            x += 50;
        }
        if (continuous) {
            for (int i = array.size - 2; i > 4; i--)
            {
                array.add(array.get(i));
            }
        }
        return array;
    }

    @Override
    public void applyDamage(int damage) {
        super.applyDamage(damage);
        Hud.decreaseMegaManEnergy(damage);
    }

    public boolean canJump()
    {
        return jumps <2;
    }

    public void incrementJumps()
    {
        jumps += 1;
    }

    public static int getScore() {
        return Score;
    }

    public static void setScore(int score) {
        Score = score;
    }
}
