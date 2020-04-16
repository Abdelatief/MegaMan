package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.Screen.Playscreen;
import com.megaman.game.Megaman;
import org.w3c.dom.Text;

public class Mega_Man extends Sprite {
    public enum State {FALLING, JUMPING, STANDING, RUNNING, IDLE, INTRO, RUNANDSHOOT}
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion ManStand;
    private Animation run;
    private Animation jump;
    private Animation fall;
    private Animation idle;
    private Animation intro;
    private Animation runAndShoot;
    private float stateTimer;
    private float timer;
    private boolean runningRight;
    private boolean introAnimationPlayed;

    public Mega_Man(World  world , Playscreen screen)
    {
        super(screen.getAtlas().findRegion("megaman7_megaman_sheet"));
        this.world=world;

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        timer = 0;
        runningRight = true;
        introAnimationPlayed = true;

        TextureRegionArraysSetup();
        defineMegaMan();
        setBounds(0,0,32/ Megaman.PPM,40/ Megaman.PPM);
    }

    public void defineMegaMan()
    {
        BodyDef bdef=new BodyDef();
        bdef.position.set(32/ Megaman.PPM,100/ Megaman.PPM);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(bdef);
        FixtureDef fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(1/ Megaman.PPM );
        fdef.shape=shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt)
    {
        timer += dt;
        if (timer > 0.64)
            introAnimationPlayed = false;
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/8);
        setRegion(getFrame(dt));
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

            default:
                region = (TextureRegion) idle.getKeyFrame(stateTimer);
        }

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX())
        {
            region.flip(true, false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX())
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
        if (introAnimationPlayed)
            return State.INTRO;
        if (b2body.getLinearVelocity().y > 0)
            return State.JUMPING;
        else if (b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.IDLE;
    }

    private void TextureRegionArraysSetup()
    {
        int width = 35;
        int height = 45;
        run = new Animation(0.05f, GetArrayFromSheet(8, 200, 11, true));
        jump = new Animation(0.25f, GetArrayFromSheet(8, 400, 3, false));
        fall = new Animation(0.25f, GetArrayFromSheet(158, 400, 4, false));
        idle = new Animation(0.25f, GetArrayFromSheet(8, 50, 8, false));
        intro = new Animation(0.05f, GetArrayFromSheet(8, 0, 8, false));
        runAndShoot = new Animation(0.05f, GetArrayFromSheet(258,300, 11, true));
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
            array.add(new TextureRegion(getTexture(), x, y, 34, 45));
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
}
