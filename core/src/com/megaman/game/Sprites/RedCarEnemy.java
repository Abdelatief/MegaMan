package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;

public class RedCarEnemy extends Enemy {
    TextureRegion enemyTextureRegion;
    public RedCarEnemy(World world, Playscreen screen, String spriteSheet, int maxHealth) {
        super(world, screen, spriteSheet, maxHealth);
        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        runningRight = true;
        define();
        enemyTextureRegion = new TextureRegion(getTexture(), 1024, 650, 70, 45);
        //animationSetup();

        setBounds(0,0,32/MegamanGame.PPM,40/MegamanGame.PPM);
        setRegion(enemyTextureRegion);
    }

    @Override
    public void define()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(40 / MegamanGame.PPM, 100 / MegamanGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / MegamanGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        // Edge shape will be defined here for collision
    }


    public void update(float dt)
    {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(enemyTextureRegion);
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
