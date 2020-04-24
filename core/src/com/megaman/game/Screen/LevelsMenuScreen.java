package com.megaman.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.megaman.game.MegamanGame;

public class LevelsMenuScreen implements Screen {
    private MegamanGame game;
    private Texture inactive_Level1_button;
    private Texture active_Level1_button;
    private Texture background;
    private Texture active_Level2_button;
    private Texture inactive_Level2_button;
    private Texture active_Back_button;
    private Texture inactive_Back_button;
    private Texture Logo;
    private static final int Button_Width=500;
    private static final int Button_Height=100;
    public LevelsMenuScreen(MegamanGame game) {
        this.game = game;
        background= new Texture("bg.jpg");

        Logo= new Texture("Mega_Man_Logo.png");
        inactive_Level1_button = new Texture("inactive_Level1_button.jpg");
        active_Level1_button = new Texture("active_Level1_button.jpg");
        inactive_Level2_button= new Texture("inactive_Level2_button.jpg");
        active_Level2_button= new Texture("active_Level2_button.jpg");
        inactive_Back_button=new Texture("inactive_Back_button.jpg");
        active_Back_button= new Texture("active_Back_button.jpg");
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
        if(Gdx.input.getX()>=1200&&Gdx.input.getX()<=(1200+Button_Width)&&Gdx.input.getY()<=350&&Gdx.input.getY()>=350-Button_Height) {
            game.batch.draw(active_Level1_button,1200,700,Button_Width,Button_Height);
            if(Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new Playscreen(game));
            }
        }
        else
            game.batch.draw(inactive_Level1_button,1200,700,Button_Width,Button_Height);
        if(Gdx.input.getX()>=1200&&Gdx.input.getX()<=(1200+Button_Width)&&Gdx.input.getY()<=550&&Gdx.input.getY()>=550-Button_Height) {
            game.batch.draw(active_Level2_button,1200,500,Button_Width,Button_Height);
        }
        else
            game.batch.draw(inactive_Level2_button,1200,500,Button_Width,Button_Height);
        if(Gdx.input.getX()>=1200&&Gdx.input.getX()<=(1200+Button_Width)&&Gdx.input.getY()<=750&&Gdx.input.getY()>=750-Button_Height) {
            game.batch.draw(active_Back_button,1200,300,Button_Width,Button_Height);
            if(Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        else
            game.batch.draw(inactive_Back_button,1200,300,Button_Width,Button_Height);

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
