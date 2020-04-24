package com.megaman.game.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import  com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.megaman.game.MegamanGame;

import com.megaman.game.Scenes.Hud;
import com.megaman.game.Sprites.MegaMan;
import com.megaman.game.Tools.B2WorldCreator;

public class Playscreen extends screen{
    private TextureAtlas atlas;
    private Hud hud;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Texture inactive_pause_button;
    private Texture active_pause_button;
    private Texture inactive_continue_button;
    private Texture active_continue_button;
    private Texture inactive_Main_Menu;
    private Texture active_Main_Menu;
    private int Button_Width=100;
    private int Button_Height=100;
    private boolean pause;
    //Tiled map variables
    private TmxMapLoader mapLoader;                 //this is going to load all maps into game
    private TiledMap map;                           //this is reference to map itself
    private OrthogonalTiledMapRenderer renderer;    //this is render map to screen

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;                //Gives graphical representation of fixtures and body inside box2d world
    private MegaMan player;

    public  Playscreen(MegamanGame game)
    {
        atlas=new TextureAtlas("mega_man.pack");
        this.game=game;
        pause=false;
        //pause,continue button
        inactive_pause_button=new Texture("inactive_pause_button.jpg");
        active_pause_button=new Texture("active_pause_button.jpg");
        active_continue_button=new Texture("active_continue_button.jpg");
        inactive_continue_button=new Texture("inactive_continue_button.jpg");
        //Main Menu button
        active_Main_Menu=new Texture("active_Main_Menu.jpg");
        inactive_Main_Menu=new Texture("inactive_Main_Menu.jpg");
        gamecam=new OrthographicCamera();
        //create a Fitviewport to maintain virtual aspect ratio despite screen
        gameport=new FitViewport(400/ MegamanGame.PPM, 208/ MegamanGame.PPM,gamecam);
        //create our game HUD for scores /timers/level info
        hud=new Hud(game.batch);
        //Load our map and setup our map renderer
        mapLoader=new TmxMapLoader();
         map =mapLoader.load("Mega_Level1.tmx");
        renderer=new OrthogonalTiledMapRenderer(map,1/ MegamanGame.PPM);
        //initially set our gamcam to be centered correctly at the start of map
        gamecam.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/ 2,0);
         //vector2 this is for gravity(0,0) no gravity now
        //do sleep:true bec:box2d doesn't want to be calculated inside its  physics simulation "body rest"
        world =new World(new Vector2(0,-10),true);
        b2dr=new Box2DDebugRenderer();
        new B2WorldCreator(world,map);
        //create megaman in game
        player=new MegaMan(world,this);
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
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            pause = true;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
    public void render(float delta) {
        if (pause) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                pause = false;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else {
            update(delta);

        }
        //clear screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //render game map
        renderer.render();
        //render our box2dDebuglines
        b2dr.render(world, gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();
        //set batch to draw what hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        //draw pause,continue button
        game.batch.begin();
        if(!pause)
        {
            if (Gdx.input.getX() >= 1825 && Gdx.input.getX() <= (1825 + Button_Width) && Gdx.input.getY() <= 100 && Gdx.input.getY() >= 100 - Button_Height) {
                game.batch.draw(active_pause_button, 1890, 900, Button_Width, Button_Height);
                if (Gdx.input.isTouched()) {
                    pause = true;
                   try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                game.batch.draw(inactive_pause_button, 1890, 900, Button_Width, Button_Height);
            }
        }else {
            if (Gdx.input.getX() >= 1825 && Gdx.input.getX() <= (1825 + Button_Width) && Gdx.input.getY() <= 100 && Gdx.input.getY() >= 100 - Button_Height) {
                game.batch.draw(active_continue_button, 1890, 900, Button_Width, Button_Height);
                if (Gdx.input.isTouched()) {
                    pause = false;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else
            game.batch.draw(inactive_continue_button, 1890, 900, Button_Width, Button_Height);
        }
    //Draw MainMenu button
      if(  Gdx.input.getX() >= 1825 && Gdx.input.getX() <= (1825 + Button_Width) && Gdx.input.getY() <= 200 && Gdx.input.getY() >= 200 - Button_Height) {
            game.batch.draw(active_Main_Menu, 1890, 800, Button_Width, Button_Height);
            if(Gdx.input.isTouched())
            {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        else
            game.batch.draw(inactive_Main_Menu,1890,800,Button_Width,Button_Height);
            game.batch.end();
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
    public void dispose() {
            map.dispose();
            renderer.dispose();
            world.dispose();
            b2dr.dispose();
            hud.dispose();
    }
}
