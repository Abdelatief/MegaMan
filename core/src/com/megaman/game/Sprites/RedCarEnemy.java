package com.megaman.game.Sprites;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.MegamanGame;
import com.megaman.game.Scenes.Hud;
import com.megaman.game.Screen.Playscreen;


public class RedCarEnemy extends Enemy {
    TextureRegion enemyTextureRegion;
    private Array<TextureRegion> frames;
    private Animation<TextureRegion> walkAnimation;

    public RedCarEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y) {
        super(world, screen, spriteSheet, maxHealth,x,y);
        currentState = State.IDLE;
        previousState = State.IDLE;
        frames = new Array<TextureRegion>();
        runningRight = true;
        for(int i = 5; i>0; i--)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("SNES - Mega Man X - Enemies 1"), i*70+50 , 650, 60, 45));
        walkAnimation = new Animation(0.4f, frames);
        stateTimer = 0;
        setBounds(getX(), getY(), 55 / MegamanGame.PPM, 45 / MegamanGame.PPM);

    }

    @Override
    public void define()
    {

        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / MegamanGame.PPM, 100 / MegamanGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / MegamanGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void update(float dt) {

        super.update(dt);
        stateTimer += dt;
        if (!getSetToDestroy() && !getDestroyed()) {
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight()/3 );
            setRegion(walkAnimation.getKeyFrame(stateTimer, true));
        }
    }

    @Override
    public void die() {
        super.die();
        Hud.IncreaseScore(500);
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
}
