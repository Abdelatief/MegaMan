package com.megaman.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;

import java.util.ArrayList;

public class Bosses extends Enemy {
    private float animstart;
    private ArrayList<BossBullet> Bbullets;

    public Bosses(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y, int NumberOfAnimation, int XPositionInSpriteSheet, int YPositionInSpriteSheet, int width, int height, int ValueToIncreaseScore) {
        super(world, screen, spriteSheet, maxHealth, x, y, NumberOfAnimation, XPositionInSpriteSheet, YPositionInSpriteSheet, width, height, ValueToIncreaseScore);
        Bbullets = new ArrayList<BossBullet>();
        animstart = 1.8f;
    }

    public Bosses(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y, int ValueToIncreaseScore) {
        super(world, screen, spriteSheet, maxHealth, x, y, ValueToIncreaseScore);
        Bbullets = new ArrayList<BossBullet>();
        animstart = 1.8f;
    }

    public void update(float dt) {
        super.update(dt);
        if (stateTimer >= animstart) {
            Bbullets.add(new BossBullet(getB2body().getPosition().x, getB2body().getPosition().y, this.runningRight, getWorld(), screen));
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

    public void BulletRender(MegamanGame game) {
        for (BossBullet bullet : Bbullets)
            bullet.render(game.batch);
    }
}

