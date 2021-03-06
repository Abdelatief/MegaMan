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
import com.megaman.game.Sprites.MegaMan;

public class EndLevelScreen extends screen {
    private Texture background;
    private Texture inactive_newgame_button;
    private Texture active_newgame_button;
    private Texture active_exit_button;
    private Texture inactive_exit_button;
    private int Button_Width = 600;
    private  int Button_Height = 100;
    private int score;
    private int highscore;
    private BitmapFont scoreFont;

    public EndLevelScreen(MegamanGame game, int score, String PhotoName) {
        this.score=score;
        this.game = game;
        MegaMan.setScore(0);
        background = new Texture(PhotoName);
        inactive_newgame_button = new Texture("inactive_PlayAgain_button.jpg");
        active_newgame_button = new Texture("active_PlayAgain_button.jpg");
        inactive_exit_button = new Texture("inactive_Exit_button.jpg");
        active_exit_button = new Texture("active_Exit_button.jpg");
        scoreFont = new BitmapFont();
        //Get highscore from save file
        Preferences prefs = Gdx.app.getPreferences("megamangame");//Open the file or create it if it doesn't exist.

        this.highscore = prefs.getInteger("High score", 0);

        //Check if score beats highscore
        if (this.score > highscore) {

            prefs.putInteger("High score", this.score);
            prefs.flush();//Save to file
            this.highscore = this.score;
        }

    }


    @Override
    public void render(float delta) {
        //clear screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);//set screen to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//clear screen
        game.batch.begin();//Open the box
        game.batch.draw(background, 0, 600, 2000, 500);
        /*GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score: " + score);//, Color.WHITE, 0, Align.left, false);
        GlyphLayout highscoreLayout = new GlyphLayout(scoreFont, "Highscore: " + highscore);//, Color.WHITE, 0, Align.left, false);*/
        scoreFont.getData().setScale(4,4);
        scoreFont.draw(game.batch, "Score: " + score, (MegamanGame.V_WIDTH / 2)-160, MegamanGame.V_HEIGHT - 500);
        scoreFont.draw(game.batch, "Highscore: " + highscore, (MegamanGame.V_WIDTH / 2)-160, MegamanGame.V_HEIGHT - 600 );

        if (Gdx.input.getX() >= 700 && Gdx.input.getX() <= (700 + Button_Width) && Gdx.input.getY() <= 800 && Gdx.input.getY() >= 800 - Button_Height) {
            game.batch.draw(active_newgame_button, 700, 200, Button_Width, Button_Height);

            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new LevelsMenuScreen(game));
            }
        } else
            game.batch.draw(inactive_newgame_button, 700, 200, Button_Width, Button_Height);
        if (Gdx.input.getX() >= 700 && Gdx.input.getX() <= (700 + Button_Width) && Gdx.input.getY() <= 900 && Gdx.input.getY() >= 900 - Button_Height) {
            game.batch.draw(active_exit_button, 700, 100, Button_Width, Button_Height);
            if (Gdx.input.isTouched()) {

                Gdx.app.exit();
            }
        } else
            game.batch.draw(inactive_exit_button, 700, 100, Button_Width, Button_Height);
        game.batch.end();//Close the box
    }


}
