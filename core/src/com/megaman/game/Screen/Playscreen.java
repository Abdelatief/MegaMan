package com.megaman.game.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
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
import com.megaman.game.Levels.Level1;
import com.megaman.game.Levels.Level2;
import com.megaman.game.Levels.Level3;
import com.megaman.game.MegamanGame;
import com.megaman.game.Scenes.Hud;
import com.megaman.game.Sprites.*;
import com.megaman.game.Tools.B2WorldCreator;
import com.megaman.game.Tools.WorldContactListener;


import java.util.ArrayList;

public abstract class Playscreen extends screen{

    // Screen and camera
    private final TextureAtlas atlas;
    private final TextureAtlas atlas2;
    private final Hud hud;
    private final OrthographicCamera gamecam;

    private Viewport gameport;

    // Button textures
    private final Texture inactive_pause_button;
    private final Texture active_pause_button;
    private final Texture inactive_continue_button;
    private final Texture active_continue_button;
    private final Texture inactive_Main_Menu;
    private final Texture active_Main_Menu;
    private final int Button_Width=100;                     // * can be local variable
    private final int Button_Height=100;                    // * can be local variable

    // Tiled map variables
    private TmxMapLoader mapLoader;                         // this is going to load all maps into game
    private TiledMap map;                                   // this is reference to map itself
    private OrthogonalTiledMapRenderer renderer;            // this is render map to screen

    // Box2d variables
    private final World world;
    private final Box2DDebugRenderer b2dr;                  // Gives graphical representation of fixtures and body inside box2d world
    private final MegaMan player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private ArrayList<Bullet> bBullets = new ArrayList<Bullet>();

    // Utility booleans
    private boolean AtBossPosition;
    private boolean pause;
    private Sound jumpSound;
    private String Level_Number;

    /**
     * Create a new Playscreen instance, setup attributes and load buttons textures and enemies addition
     * @param game MegamanGame instance that will be rendered on the Playscreen
     */
    public  Playscreen(MegamanGame game,String Map) {
        this.game = game;
        atlas = new TextureAtlas("MegaMan_Enemies.pack");
        atlas2=new TextureAtlas("BM.pack");

        pause = false;
        AtBossPosition=false;

        if(this instanceof Level1)
           Level_Number="1";
        else  if(this instanceof Level2)
            Level_Number="2";
        else
            Level_Number="3";
        // Buttons textures => pause,continue button
        inactive_pause_button = new Texture("inactive_pause_button.jpg");
        active_pause_button = new Texture("active_pause_button.jpg");
        active_continue_button = new Texture("active_continue_button.jpg");
        inactive_continue_button = new Texture("inactive_continue_button.jpg");
        // Main Menu button
        active_Main_Menu = new Texture("active_Main_Menu.jpg");
        inactive_Main_Menu = new Texture("inactive_Main_Menu.jpg");

        // Create a Fitviewport to maintain virtual aspect ratio despite screen
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(400 / MegamanGame.PPM, 208 / MegamanGame.PPM, gamecam);

        // Load our map and setup our map renderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(Map);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MegamanGame.PPM);

        // Initially set our gamcam to be centered correctly at the start of map
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);

        // Vector2 this is for gravity(0,0) no gravity now
        // Do sleep:true bec:box2d doesn't want to be calculated inside its  physics simulation "body rest"
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
        new B2WorldCreator(world, map);

        // Create megaman in game and adding enemies
        player = new MegaMan(world, this);


        //create our game HUD for scores /timers/level info

            hud = new Hud(game.batch,player,Level_Number);

        //Music
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/Jump-SoundBible.com-1007297584.mp3"));
        world.setContactListener(new WorldContactListener());
        setEnemies();
    }
    public void handelInput(float dt)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)||Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {

                jumpSound.play();
            if (player.canJump()) {
                player.getB2body().applyLinearImpulse(new Vector2(0, 3.5f), player.getB2body().getWorldCenter(), true);
                player.incrementJumps();
            }

        }

        if((Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT))&&(player.getB2body().getLinearVelocity().x<= 2))
        {
            //If condition to make mega man can't go out from boss position
            if(AtBossPosition) {
                if (player.getX() >= (map.getProperties().get("width", Integer.class) / 10f) + 0.8f && player.getX() <= (map.getProperties().get("width", Integer.class) / 10f) + 1.5f)
                    player.getB2body().applyLinearImpulse(new Vector2(-0.1f, 0), player.getB2body().getWorldCenter(), true);
                else
                    player.getB2body().applyLinearImpulse(new Vector2(0.05f, 0), player.getB2body().getWorldCenter(), true);
            }
            else
                player.getB2body().applyLinearImpulse(new Vector2(0.055f, 0), player.getB2body().getWorldCenter(), true);
        }

        if((Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT))&&(player.getB2body().getLinearVelocity() .x>= -2))
        {
            //To make mega man can't go out from boss position and start game
            if(AtBossPosition)
            {
                if(player.getX()<=(map.getProperties().get("width", Integer.class) / 10f)-1&&player.getX()>=(map.getProperties().get("width", Integer.class) / 10f)-1.5f)
                {
                    player.getB2body().applyLinearImpulse(new Vector2(0.1f,0),player.getB2body().getWorldCenter(),true);
                }
                else
                    player.getB2body().applyLinearImpulse(new Vector2(-0.05f,0),player.getB2body().getWorldCenter(),true);
            }

            else if(player.getX() <=0.5&&player.getX() >=-0.3)
                player.getB2body().applyLinearImpulse(new Vector2(0.1f, 0), player.getB2body().getWorldCenter(), true);
            else
                player.getB2body().applyLinearImpulse(new Vector2(-0.055f, 0), player.getB2body().getWorldCenter(), true);


        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))        //Shooting
        {
            bullets.add(new Bullet(player.getB2body().getPosition().x, player.getB2body().getPosition().y, player.runningRight, world,.7f,10,"Buster.gif",410, 10, 20, 20,3,0.09f,"MegaMan"));
        }
    }

    public void update(float dt)
    {
        // Pause
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            pause = true;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // First handel user input
        handelInput(dt);
        world.step(1/60f,6,2);
        player.update(dt);

        // Update enemies in the playscreen
        ArrayList<Enemy> removeEnemies = new ArrayList<Enemy>();
        for (Enemy enemy: enemies)
        {
            if (enemy.isDestroyed())
                removeEnemies.add(enemy);
            else
                enemy.update(dt);
        }
        // remove destroyed enemies
        for (Enemy enemy: removeEnemies)
        {
            enemies.remove(enemy);
        }
        removeEnemies.clear();

        //GAME OVER
        if(player.getCurrentHealth()==0||player.getCurrentHealth()<0||player.getY()<-50/MegamanGame.PPM) {
            game.setScreen(new EndLevelScreen(game,player.getScore(),"GameOver.jpg"));
        }

        for (Enemy enemy: enemies)
        {
            if (enemy instanceof Bosses)
                if ( enemy.getCurrentHealth() == 0) {
                    if(Level_Number=="1")
                        game.setScreen(new Level2(game));
                    else if (Level_Number=="2")
                        game.setScreen(new Level3(game));
                    else
                        game.setScreen(new EndLevelScreen(game, player.getScore(),"Win.jpg"));
                }

        }



        //To make Game Camera stop at boss position

        if(map.getProperties().get("width",Integer.class)/10f-1.5f<player.getB2body().getPosition().x) {
           gamecam.position.x = map.getProperties().get("width", Integer.class) / 10f;
           AtBossPosition=true;
        }
        else if(!AtBossPosition)    // If the mega man isn't at the boss position the game camera is moving with it.
            gamecam.position.x=player.getB2body().getPosition().x;
        //update cam with correct coordinate after changes
        gamecam.update();
        //tell our render to draw what our camera sees
        renderer.setView(gamecam);
        // update the bullets and remove them after 3 seconds
        ArrayList<Bullet> removeBullets = new ArrayList<Bullet>();
        for (Bullet bullet: bullets) {
            if (bullet.isSetToDestroy())
                removeBullets.add(bullet);
            bullet.update(dt);
        }
        bullets.removeAll(removeBullets);
    }

    @Override
    public void render(float delta) {
        if (pause) {
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
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
        for (Bullet bullet: bullets)
            bullet.draw(game.batch);
        for (Enemy enemy: enemies) {
            enemy.draw(game.batch);
        }


        for (Enemy enemy: enemies)
        {
            if (enemy instanceof Bosses )
                ((Bosses)enemy).BulletRender(game);
            else
                enemy.BulletRender(game);
        }

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
        //  Draw MainMenu button
        if(  Gdx.input.getX() >= 1825 && Gdx.input.getX() <= (1825 + Button_Width) && Gdx.input.getY() <= 200 && Gdx.input.getY() >= 200 - Button_Height)
        {
            game.batch.draw(active_Main_Menu, 1890, 800, Button_Width, Button_Height);
            if(Gdx.input.isTouched())
            {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        // * The curly braces in this if condition
        else
            game.batch.draw(inactive_Main_Menu,1890,800,Button_Width,Button_Height);
            game.batch.end();
        }
    public TextureAtlas getAtlas()
    {
        return atlas;
    }

    public TextureAtlas getAtlas2()
    {
        return atlas2;
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
    public World getWorld() {
        return world;
    }

    public abstract void setEnemies() ;

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
