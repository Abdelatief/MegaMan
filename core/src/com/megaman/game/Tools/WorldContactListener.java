package com.megaman.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.Sprites.*;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData()=="leg"||fixB.getUserData()=="leg")
        {
            Fixture leg = fixA.getUserData()=="leg" ? fixA : fixB ;
            Fixture object = leg == fixA ? fixB : fixA;

          if(object.getUserData() != null && interactiveTileobject.class.isAssignableFrom(object.getUserData().getClass()))
                ((interactiveTileobject)object.getUserData()).onLegHit();
        }

        if (fixA.getUserData() instanceof Bullet || fixB.getUserData() instanceof Bullet) //manage the bullet collision
        {
            Fixture bullet = fixA.getUserData() instanceof Bullet ? fixA : fixB;
            Fixture object = bullet == fixA ? fixB : fixA;
            Gdx.app.log("collision", "Bullet collision detected");
            Bullet bulletObject = (Bullet)bullet.getUserData();
            if (object.getUserData() instanceof Enemy)
            {
                Gdx.app.log("collision", "enemy is hit");
                Enemy enemyObject = (Enemy)object.getUserData();
                enemyObject.applyDamage(bulletObject.getDamage());
                Gdx.app.log("Enemy Health", String.valueOf(enemyObject.getCurrentHealth()));
                bulletObject.remove = true;
            }
        }

        if (fixA.getUserData() instanceof BossBullet || fixB.getUserData() instanceof BossBullet) // Manage Boss bullets collision
        {
            Fixture bullet = fixA.getUserData() instanceof BossBullet ? fixA : fixB;
            Fixture object = bullet == fixA ? fixB : fixA;
            Gdx.app.log("collision", "Bullet collision detected");
            BossBullet bulletObject = (BossBullet)bullet.getUserData();
            if (object.getUserData() instanceof MegaMan ||object.getUserData() instanceof Entity)
            {
                Gdx.app.log("collision", "Mega is hit");
                MegaMan MegaObject = (MegaMan) object.getUserData();
                MegaObject.applyDamage(bulletObject.getDamage());
                Gdx.app.log("Mega Health", String.valueOf(MegaObject.getCurrentHealth()));
//                MegaObject.decreaseEn();
                MegaObject.applyDamage(100);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
