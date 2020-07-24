package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;

import java.util.ArrayList;

public class Bosses extends Enemy {
    private float animstart;
    private ArrayList<Bullet> Bbullets;

    public Bosses(World world, Playscreen screen, TextureRegion texture, int maxHealth, float x, float y, int NumberOfAnimation, int XPositionInSpriteSheet, int YPositionInSpriteSheet, int width, int height, int ValueToIncreaseScore) {
        super(world, screen, texture, maxHealth, x, y, NumberOfAnimation, XPositionInSpriteSheet, YPositionInSpriteSheet, width, height, ValueToIncreaseScore);
        Bbullets = new ArrayList<Bullet>();
        animstart = 1.8f;

    }
    public void update(float dt) {
        super.update(dt);
        if (stateTimer >= animstart) {

            Bbullets.add(new Bullet(getB2body().getPosition().x, getB2body().getPosition().y, this.runningRight, getWorld(), .4f,100,"Buster.gif", 70, 250, 25, 20,5,0,"Boss" ));
            animstart += 1.8f;

        }
        ArrayList<Bullet> removeBBullets = new ArrayList<Bullet>();
        for (Bullet bullet : Bbullets) {
            if (bullet.isSetToDestroy())
                removeBBullets.add(bullet);
            bullet.update(dt);
        }
        Bbullets.removeAll(removeBBullets);
    }

    public void BulletRender(MegamanGame game) {
        for (Bullet bullet : Bbullets)
            bullet.render(game.batch);
    }
}

