package com.megaman.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.MegamanGame;
import com.megaman.game.Scenes.Hud;

public class forks {
//    protected World world;
//    protected TiledMap map;
//    protected TiledMapTile tile;
//    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    public forks(World world, TiledMap map, Rectangle bounds)
    {
//        this.world = world;
//        this.map =  map;
//        this.bounds = bounds;
        BodyDef bdef=new BodyDef();
        FixtureDef fdef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        //define body type and position
        bdef.type=BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / MegamanGame.PPM,(bounds.getY() + bounds.getHeight() / 2) / MegamanGame.PPM);
        body=world.createBody(bdef);
        shape.setAsBox((bounds.getWidth()/2)/ MegamanGame.PPM,(bounds.getHeight()/2)/ MegamanGame.PPM);
        fdef.shape=shape;
        fixture = body.createFixture(fdef);
        fixture.setUserData(this);
    }


}
