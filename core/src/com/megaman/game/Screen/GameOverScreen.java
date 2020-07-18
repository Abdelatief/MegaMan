package com.megaman.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.megaman.game.MegamanGame;

public class GameOverScreen extends screen {
    private Texture background;
    private Texture inactive_newgame_button;
    private Texture active_newgame_button;
    private Texture active_exit_button;
    private Texture inactive_exit_button;
    private int Button_Width = 600;
    private  int Button_Height = 100;


    public GameOverScreen(MegamanGame game) {
        this.game = game;
        background = new Texture("GameOver.jpg");
        inactive_newgame_button = new Texture("inactive_PlayAgain_button.jpg");
        active_newgame_button = new Texture("active_PlayAgain_button.jpg");
        inactive_exit_button = new Texture("inactive_Exit_button.jpg");
        active_exit_button = new Texture("active_Exit_button.jpg");

    }

    @Override
    public void render(float delta) {
        //clear screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0, MegamanGame.V_WIDTH, MegamanGame.V_HEIGHT);

        if (Gdx.input.getX() >= 650 && Gdx.input.getX() <= (650 + Button_Width) && Gdx.input.getY() <= 700 && Gdx.input.getY() >= 700 - Button_Height) {
            game.batch.draw(active_newgame_button, 650, 300, Button_Width, Button_Height);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new LevelsMenuScreen(game));
            }
        } else
            game.batch.draw(inactive_newgame_button, 650, 300, Button_Width, Button_Height);
        if (Gdx.input.getX() >= 650 && Gdx.input.getX() <= (650 + Button_Width) && Gdx.input.getY() <= 800 && Gdx.input.getY() >= 800 - Button_Height) {
            game.batch.draw(active_exit_button, 650, 200, Button_Width, Button_Height);
            if (Gdx.input.isTouched()) {

                Gdx.app.exit();
            }
        } else
            game.batch.draw(inactive_exit_button, 650, 200, Button_Width, Button_Height);
        game.batch.end();
    }


}
