package com.megaman.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.MegamanGame;
import com.megaman.game.Sprites.forks;

public class B2WorldCreator {
public B2WorldCreator(World world, TiledMap map)
{
    //Before we create body we define what is the body consists of:
    BodyDef bdef=new BodyDef();
    PolygonShape shape=new PolygonShape();
    FixtureDef fdef=new FixtureDef();
    Body body;
    //To create body ,fixture to every corresponding object in Tiled map layers
    //getLayers().get(index in Tilemap start from 0 (down->up)
    //2 index of ground
    //.getObjects().getByType(RectangleMapObject.class) to get all objects in ground layer of rectangle type
    // create ground bodies,fixtures
    for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
    {
        //create rectangle object itself
        Rectangle rect =((RectangleMapObject)object).getRectangle();
        //define body type and position
        bdef.type=BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX()+rect.getWidth()/2)/ MegamanGame.PPM,(rect.getY()+rect.getHeight()/2)/ MegamanGame.PPM);
        body=world.createBody(bdef);
        shape.setAsBox((rect.getWidth()/2)/ MegamanGame.PPM,(rect.getHeight()/2)/ MegamanGame.PPM);
        fdef.shape=shape;
        body.createFixture(fdef);
    }
    // create forks bodies,fixtures
    for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
    {
        //create rectangle object itself
        Rectangle rect =((RectangleMapObject)object).getRectangle();
        new forks(world, map, rect);
    }

}
}
