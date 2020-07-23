package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;

import java.util.ArrayList;

public class VioletBoss extends Enemy{
    private float animstart;
    private ArrayList<BossBullet> Bbullets;

    public VioletBoss(World world, Playscreen screen, String spriteSheet, int maxHealth, float x, float y, int ValueToIncreaseScore) {
        super(world, screen, spriteSheet, maxHealth, x, y, ValueToIncreaseScore);
        for (int i = 0; i < 3; i++) {
            getFrames().add(new TextureRegion(screen.getAtlas().findRegion("SNES - Mega Man X - Enemies 1"), i*150, 190, 140, 110));
        }
        setWalkAnimation(new Animation(0.2f,  getFrames()));
        setBounds(getX(), getY(), 68 / MegamanGame.PPM, 110 / MegamanGame.PPM);
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