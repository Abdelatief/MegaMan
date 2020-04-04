package com.megaman.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.megaman.game.Screen.Playscreen;
import com.megaman.game.Megaman;

public class Mega_Man extends Sprite {
    public World world;
    public Body b2body;
   private TextureRegion ManStand;
    public Mega_Man(World  world , Playscreen screen)
    {
        super(screen.getAtlas().findRegion("megaman7_megaman_sheet"));
        this.world=world;
        defineMegaMan();
        ManStand=new TextureRegion(getTexture(),58,200,30,45);
        setBounds(0,0,32/ Megaman.PPM,40/ Megaman.PPM);
        setRegion(ManStand);
    }
    public void defineMegaMan()
    {
        BodyDef bdef=new BodyDef();
        bdef.position.set(32/ Megaman.PPM,50/ Megaman.PPM);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(bdef);
        FixtureDef fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(1/ Megaman.PPM );
        fdef.shape=shape;
        b2body.createFixture(fdef);
    }
    public void update(float dt)
    {
     setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/8);
    }
}
