package com.megaman.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.Sprites.interactiveTileobject;

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
