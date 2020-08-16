package com.megaman.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.megaman.game.MegamanGame;
import com.megaman.game.Sprites.MegaMan;


public class Hud implements Disposable {
    private Stage stage;
    private Viewport viewport;//when game world moves we want the head to stay the same so we use new camera and new viewport specifically for Hud so it stays locked there and only renders that part of the screen and the world can move around independently on its own
   /* private Integer worldTimer;
    private float timecount;*/
    private static Integer Energy;//static variable because it is used in static function
    private static Label ScoreLabel;//static variable because it is used in static function
    private Label levelabel;
    private Label worldLabel;
    private Label MegamanLabel;
    private Label MegaManEnergyLabel;
    private static Label  EnergyLabel;//static variable because it is used in static function
    private static MegaMan player;
    public  Hud(SpriteBatch sb,MegaMan player, String NumberOfLevel)
    {

       /* worldTimer=300;
        timecount=0;*/
        this.player=player;
        Energy = this.player.getCurrentHealth();
        viewport=new FitViewport(MegamanGame.V_WIDTH, MegamanGame.V_HEIGHT,new OrthographicCamera());

        stage =new Stage(viewport,sb);
        /*Stage is basically an empty box if just tried to put widgets in there they would fall and they wouldn't have any kind of organization so
       create a table inside of our stage then we can layout that table in a way to organize our labels in a certain position inside of our stage */
        Table table=new Table();
        table.top();//it's going to put it at the top of our stage
        table.setFillParent(true);//table size = stage size
        //String.format convert Energy to string and make 4 numbers appear 0000 and return string
        ScoreLabel=new Label(String.format("%06d",player.getScore()),new Label.LabelStyle(new BitmapFont(), Color.WHITE));//%06d means 6 numbers while appear
        EnergyLabel=new Label(String.format("%04d",Energy),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelabel=new Label(NumberOfLevel,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel=new Label("LEVEL",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        MegamanLabel=new Label("MEGA MAN Score",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        MegaManEnergyLabel = new Label(" Mega Man Energy",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //change Font Size
       MegaManEnergyLabel.setFontScaleX(1.5f);
        MegaManEnergyLabel.setFontScaleY(1.5f);
        EnergyLabel.setFontScaleX(1.5f);
        EnergyLabel.setFontScaleY(1.5f);
        MegamanLabel.setFontScaleX(1.5f);
        MegamanLabel.setFontScaleY(1.5f);
        ScoreLabel.setFontScaleX(1.5f);
        ScoreLabel.setFontScaleY(1.5f);
        worldLabel.setFontScaleX(1.5f);
        worldLabel.setFontScaleY(1.5f);
        levelabel.setFontScaleX(1.5f);
        levelabel.setFontScaleY(1.5f);
        //Add Labels to Table
        table.add(MegaManEnergyLabel).expandX().padTop(10);
        table.add(MegamanLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.row();//Create new row
        table.add(EnergyLabel).expandX();
        table.add(ScoreLabel).expandX();
        table.add(levelabel).expandX();
        stage.addActor(table);//add table to stage
    }
    public Stage getStage() {
        return stage;
    }
    public static void  decreaseMegaManEnergy(int value)
    {
        Energy-=value;
        EnergyLabel.setText((String.format("%04d",Energy)));
        player.setCurrentHealth(Energy);

    }
    public static void  IncreaseScore(int value)
    {
        player.setScore(player.getScore()+value);
        ScoreLabel.setText((String.format("%04d",player.getScore())));
    }

    /** Releases all resources of this object. */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
