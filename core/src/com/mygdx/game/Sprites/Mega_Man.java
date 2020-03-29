package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.Playscreen;

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
        setBounds(0,0,32/MyGdxGame.PPM,40/MyGdxGame.PPM);
        setRegion(ManStand);
    }
    public void defineMegaMan()
    {
        BodyDef bdef=new BodyDef();
        bdef.position.set(32/ MyGdxGame.PPM,50/ MyGdxGame.PPM);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(bdef);
        FixtureDef fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(1/ MyGdxGame.PPM );
        fdef.shape=shape;
        b2body.createFixture(fdef);
    }
    public void update(float dt)
    {
     setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/8);
    }
}
