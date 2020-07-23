package com.megaman.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.MegamanGame;
import com.megaman.game.Sprites.*;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof Bullet || fixB.getUserData() instanceof Bullet) //manage the bullet collision
        {
            Fixture bullet = fixA.getUserData() instanceof Bullet ? fixA : fixB;
            Fixture object = bullet == fixA ? fixB : fixA;
            Gdx.app.log("collision", "Bullet collision detected");
            Bullet bulletObject = (Bullet) bullet.getUserData();
            if (object.getUserData() instanceof Enemy) {
                Gdx.app.log("collision", "enemy is hit");
                Enemy enemyObject = (Enemy) object.getUserData();
                enemyObject.applyDamage(bulletObject.getDamage());
                Gdx.app.log("Enemy Health", String.valueOf(enemyObject.getCurrentHealth()));
                bulletObject.setSetToDestroy(true);
            }
        }

        if (fixA.getUserData() instanceof MegaMan || fixB.getUserData() instanceof MegaMan) // Manage Megaman and enemies collision
        {
            Fixture megaman = fixA.getUserData() instanceof MegaMan ? fixA : fixB;
            Fixture object = megaman == fixA ? fixB : fixA;
            Gdx.app.log("Megaman", "Megaman Collision");
            MegaMan player = (MegaMan)megaman.getUserData();
            if (object.getUserData() instanceof Enemy)
            {
                Gdx.app.log("Collision", "Megaman - Enemy");
                player.applyDamage(100);
            }
            else if (object.getUserData() instanceof forks)
            {
                player.applyDamage(100);
            }
            else if (object.getUserData() instanceof BossBullet)
            {
                BossBullet bullet = (BossBullet)object.getUserData();
                player.applyDamage(bullet.getDamage());
                bullet.remove = true;
            }
            else if (object.getUserData() instanceof EnemyBullet)
            {
                EnemyBullet bullet = (EnemyBullet)object.getUserData();
                player.applyDamage(bullet.getDamage());
                bullet.remove = true;
            }
            Gdx.app.log("Megaman Helath:", String.valueOf(player.getCurrentHealth()));
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
