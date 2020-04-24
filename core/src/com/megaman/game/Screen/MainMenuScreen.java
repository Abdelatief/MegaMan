package com.megaman.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.megaman.game.MegamanGame;

public class MainMenuScreen implements Screen {
    private MegamanGame game;
    private Texture inactive_newgame_button;
    private Texture active_newgame_button;
    private Texture background;
    private Texture Logo;
    private Texture active_exit_button;
    private Texture inactive_exit_button;
    private static final int Button_Width=600;
    private static final int Button_Height=100;
    public MainMenuScreen(MegamanGame game) {
        this.game = game;
        background= new Texture("bg.jpg");
        Logo= new Texture("Mega_Man_Logo.png");
        inactive_newgame_button = new Texture("inactive_newgame_button.jpg");
        active_newgame_button = new Texture("active_newgame_button.jpg");
        inactive_exit_button= new Texture("inactive_Exit_button.jpg");
        active_exit_button= new Texture("active_Exit_button.jpg");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //clear screen with black
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background,0,0,MegamanGame.V_WIDTH,MegamanGame.V_HEIGHT);
        game.batch.draw(Logo,MegamanGame.V_WIDTH-1095,MegamanGame.V_HEIGHT-200,1000,150);
        if(Gdx.input.getX()>=1200&&Gdx.input.getX()<=(1200+Button_Width)&&Gdx.input.getY()<=400&&Gdx.input.getY()>=400-Button_Height) {
            game.batch.draw(active_newgame_button,1200,600,Button_Width,Button_Height);
            if(Gdx.input.isTouched())
            {
                this.dispose();
                game.setScreen(new LevelsMenuScreen(game));
            }
        }
        else
            game.batch.draw(inactive_newgame_button,1200,600,Button_Width,Button_Height);
        if(Gdx.input.getX()>=1200&&Gdx.input.getX()<=(1200+Button_Width)&&Gdx.input.getY()<=600&&Gdx.input.getY()>=600-Button_Height) {
            game.batch.draw(active_exit_button,1200,400,Button_Width,Button_Height);
            if(Gdx.input.isTouched())
            {

                Gdx.app.exit();
            }
        }
        else
            game.batch.draw(inactive_exit_button,1200,400,Button_Width,Button_Height);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}

