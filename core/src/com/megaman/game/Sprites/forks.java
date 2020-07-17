package com.megaman.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class forks extends interactiveTileobject {
    public forks(World world, TiledMap map, Rectangle bounds)
    {
        super(world,map,bounds);
        fixture.setUserData(this);
    }

    @Override
    public void onLegHit() {
        Gdx.app.log("Forks", "Collision");
    }
}
