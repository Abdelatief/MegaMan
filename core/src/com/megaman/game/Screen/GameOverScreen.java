package com.megaman.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.megaman.game.MegamanGame;

public class GameOverScreen extends screen {
    private Texture background;
    private Texture inactive_newgame_button;
    private Texture active_newgame_button;
    private Texture active_exit_button;
    private Texture inactive_exit_button;
    private int Button_Width = 600;
    private  int Button_Height = 100;
    private int score;
    private int highscore;
    private int Highscore_Level1;
    private int Highscore_Level2;
    private int Highscore_Level3;
    private BitmapFont  scoreFont;

    public GameOverScreen(MegamanGame game,int score,String Level) {
        this.score=score;
        this.game = game;
        background = new Texture("GameOver.jpg");
        inactive_newgame_button = new Texture("inactive_PlayAgain_button.jpg");
        active_newgame_button = new Texture("active_PlayAgain_button.jpg");
        inactive_exit_button = new Texture("inactive_Exit_button.jpg");
        active_exit_button = new Texture("active_Exit_button.jpg");
        scoreFont = new BitmapFont();
        //Get highscore from save file
        Preferences prefs = Gdx.app.getPreferences("megamangame");

        this.Highscore_Level1 = prefs.getInteger("High score Level1", 0);
        this.Highscore_Level2 = prefs.getInteger("High score Level2", 0);
        this.Highscore_Level3 = prefs.getInteger("High score Level3", 0);
        		//Check if score beats highscore
        if(Level=="1") {
            if (this.score > Highscore_Level1) {

                prefs.putInteger("High score Level1", this.score);
                prefs.flush();
                this.Highscore_Level1 = this.score;
            }
            this.highscore=this.Highscore_Level1;
        }
        else if (Level=="2")
        {
            if (this.score > Highscore_Level2) {

                prefs.putInteger("High score Level2", this.score);
                prefs.flush();
                this.Highscore_Level2 = this.score;
            }
            this.highscore=this.Highscore_Level2;
        }
        else {
            if (this.score > Highscore_Level3) {

                prefs.putInteger("High score Level3", this.score);
                prefs.flush();
                this.Highscore_Level3 = this.score;
            }
            this.highscore=this.Highscore_Level3;
        }

    }

    @Override
    public void render(float delta) {
        //clear screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 600, 2000, 500);
        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score: " + score, Color.WHITE, 0, Align.left, false);
        GlyphLayout highscoreLayout = new GlyphLayout(scoreFont, "Highscore: " + highscore, Color.WHITE, 0, Align.left, false);
        scoreFont.getData().setScale(4,4);
      scoreFont.draw(game.batch, scoreLayout, MegamanGame.V_WIDTH / 2- scoreLayout.width / 2 , MegamanGame.V_HEIGHT - 500 - 15 * 2);
        scoreFont.draw(game.batch, highscoreLayout, MegamanGame.V_WIDTH / 2 - highscoreLayout.width / 2 , MegamanGame.V_HEIGHT - 500 - scoreLayout.height - 15 * 3);


        if (Gdx.input.getX() >= 650 && Gdx.input.getX() <= (650 + Button_Width) && Gdx.input.getY() <= 800 && Gdx.input.getY() >= 800 - Button_Height) {
            game.batch.draw(active_newgame_button, 650, 200, Button_Width, Button_Height);

            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new LevelsMenuScreen(game));
            }
        } else
            game.batch.draw(inactive_newgame_button, 650, 200, Button_Width, Button_Height);
        if (Gdx.input.getX() >= 650 && Gdx.input.getX() <= (650 + Button_Width) && Gdx.input.getY() <= 900 && Gdx.input.getY() >= 900 - Button_Height) {
            game.batch.draw(active_exit_button, 650, 100, Button_Width, Button_Height);
            if (Gdx.input.isTouched()) {

                Gdx.app.exit();
            }
        } else
            game.batch.draw(inactive_exit_button, 650, 100, Button_Width, Button_Height);
       game.batch.end();
    }


}
