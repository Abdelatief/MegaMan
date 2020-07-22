//package com.megaman.game.Sprites;
//
//import com.badlogic.gdx.physics.box2d.World;
//import com.megaman.game.Screen.Playscreen;
//
//public class Boss extends Enemy {
//    public Boss(World world, Playscreen screen, String spriteSheet, int maxHealth,int x, int y) {
//        super(world, screen, spriteSheet, maxHealth,x,y);
//    }
//}



package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.megaman.game.MegamanGame;
import com.megaman.game.Scenes.Hud;
import com.megaman.game.Screen.Playscreen;

import java.util.ArrayList;

public class Boss extends Enemy {
    private Array<TextureRegion> frames;
    private Animation<TextureRegion> walkAnimation;
    private float x, y;
    private float Timer;
    private float animstart;
    private ArrayList<BossBullet> Bbullets;

    public Boss(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y) {
        super(world, screen, spriteSheet, maxHealth, x, y);
        this.x = x;
        this.y = y;
        currentState = Entity.State.IDLE;
        previousState = Entity.State.IDLE;
        frames = new Array<TextureRegion>();
        runningRight = true;
        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(screen.getAtlas2().findRegion("pngkit_megaman-sprite-png"), i * 68, 285, 68, 109));
        }
        walkAnimation = new Animation(0.2f, frames);
        stateTimer = 0;
        setBounds(getX(), getY(), 68 / MegamanGame.PPM, 110 / MegamanGame.PPM);
        Bbullets = new ArrayList<BossBullet>();
        Timer = 0;
        animstart = 1.8f;
    }

    public Boss(World world, Playscreen screen, String spriteSheet, int maxHealth, int x, int y) {
        super(world, screen, spriteSheet, maxHealth, x, y);
    }

    @Override
    public void define() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getX());
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
        //System.out.println("StatTimer  "+stateTimer);
        //System.out.println("DT  "+dt);
        if (!getSetToDestroy() && !getDestroyed()) {
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 3);
            setRegion(walkAnimation.getKeyFrame(stateTimer, true));
            if (this.getX() < x - 2) {
                velocity.x = 1;
                for (int i = 0; i < frames.size; i++)//Makes enemy looks to right
                    frames.get(i).flip(true, false);
                runningRight = true;
            } else if (this.getX() > x - 1) {
                velocity.x = -1;
                for (int i = 0; i < frames.size; i++)//Makes enemy looks to left
                {
                    if (frames.get(i).isFlipX()) {
                        frames.get(i).flip(true, false);
                    }
                }
                runningRight = false;
            }
            if (stateTimer >= animstart) {
                Bbullets.add(new BossBullet(this.b2body.getPosition().x, this.b2body.getPosition().y, this.runningRight, world, screen));
                animstart += 1.8f;
            }
            ArrayList<BossBullet> removeBBullets = new ArrayList<BossBullet>();
            for (BossBullet bullet : Bbullets) {
                if (bullet.remove)
                    removeBBullets.add(bullet);
                bullet.update(dt);
            }
            Bbullets.removeAll(removeBBullets);
        }
    }

    @Override
    public void die() {
        super.die();
        Hud.IncreaseScore(500);
    }

    public void BulletRender(MegamanGame game) {
        for (BossBullet bullet : Bbullets)
            bullet.render(game.batch);
    }
}