package com.megaman.game.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import  com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.megaman.game.Megaman;

import com.megaman.game.Scenes.Hud;
import com.megaman.game.Sprites.Mega_Man;
import com.megaman.game.Tools.B2WorldCreator;

public class Playscreen implements Screen{
    private TextureAtlas atlas;
    private Megaman game;
    private Hud hud;
    private OrthographicCamera gamecam;
    private Viewport gameport;

    //Tiled map variables
    private TmxMapLoader mapLoader;                 //this is going to load all maps into game
    private TiledMap map;                           //this is reference to map itself
    private OrthogonalTiledMapRenderer renderer;    //this is render map to screen

     //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;//Gives graphical representation of fixtures and body inside box2d world
    private Mega_Man player;

    public  Playscreen(Megaman game)
    {
        atlas=new TextureAtlas("mega_man.pack");
        this.game=game;

        gamecam=new OrthographicCamera();
        //create a Fitviewport to maintain virtual aspect ratio despite screen
        gameport=new FitViewport(400/ Megaman.PPM, 208/ Megaman.PPM,gamecam);
        //create our game HUD for scores /timers/level info
        hud=new Hud(game.batch);
        //Load our map and setup our map renderer
        mapLoader=new TmxMapLoader();
         map =mapLoader.load("Mega_Level1.tmx");
        renderer=new OrthogonalTiledMapRenderer(map,1/ Megaman.PPM);
        //initially set our gamcam to be centered correctly at the start of map
        gamecam.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/ 2,0);
         //vector2 this is for gravity(0,0) no gravity now
        //do sleep:true bec:box2d doesn't want to be calculated inside its  physics simulation "body rest"
        world =new World(new Vector2(0,-10),true);
        b2dr=new Box2DDebugRenderer();
        new B2WorldCreator(world,map);
        //create megaman in game
        player=new Mega_Man(world,this);
    }
    @Override
    public void show() {

    }
    public void handelInput(float dt)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)||Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            //impulse->media change
            player.b2body.applyLinearImpulse(new Vector2(0,4f),player.b2body.getWorldCenter(),true);
        }
       if((Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT))&&(player.b2body.getLinearVelocity() .x<= 2))
       {
           player.b2body.applyLinearImpulse(new Vector2(0.1f,0),player.b2body.getWorldCenter(),true);
       }
        if((Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT))&&(player.b2body.getLinearVelocity() .x>=- 2))
        {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2body.getWorldCenter(),true);
        }
    }
    public void update(float dt)
    {
        //first handel user input
        handelInput(dt);
        world.step(1/60f,6,2);
        player.update(dt);
        gamecam.position.x=player.b2body.getPosition().x;
        //update cam with correct coordinate after changes
        gamecam.update();
        //tell our render to draw what our camera sees
        renderer.setView(gamecam);
    }
    @Override
    public void render(float delta)
    {
        update(delta);
        //clear screen with black
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //render game map
        renderer.render();
        //render our box2dDebuglines
        b2dr.render(world,gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();
        //set batch to draw what hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public TextureAtlas getAtlas()
    {
        return atlas;
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
            map.dispose();
            renderer.dispose();
            world.dispose();
            b2dr.dispose();
            hud.dispose();
    }
}
