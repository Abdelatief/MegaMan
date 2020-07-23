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
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timecount;
    private static Integer score;
    private static Integer Energy;
    private Label countdownLabel;
    private static Label ScoreLabel;
    private Label levelabel;
    private Label worldLabel;
    private Label timeLabel;
    private Label MegamanLabel;
    private Label MegaManEnergyLabel;
    private static Label  EnergyLabel;
    private static MegaMan player;
    public  Hud(SpriteBatch sb,MegaMan player, String NumberOfLevel)
    {

        worldTimer=300;
        timecount=0;
        score=0;
        this.player=player;
        Energy = this.player.getCurrentHealth();
        viewport=new FitViewport(MegamanGame.V_WIDTH, MegamanGame.V_HEIGHT,new OrthographicCamera());

        stage =new Stage(viewport,sb);
        Table table=new Table();
        table.top();
        table.setFillParent(true);
        //countdownLabel= new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        ScoreLabel=new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        EnergyLabel=new Label(String.format("%04d",Energy),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelabel=new Label(NumberOfLevel,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel=new Label("LEVEL",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        MegamanLabel=new Label("MEGA MAN Score",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //timeLabel=new Label("Time",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
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
        table.add(MegaManEnergyLabel).expandX().padTop(10);
        table.add(MegamanLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.row();
        table.add(EnergyLabel).expandX();
        table.add(ScoreLabel).expandX();
        table.add(levelabel).expandX();
        stage.addActor(table);
    }
    public static void  decreaseMegaManEnergy(int value)
    {
        Energy-=value;
        EnergyLabel.setText((String.format("%04d",Energy)));
        player.setCurrentHealth(Energy);

    }
    public static void  IncreaseScore(int value)
    {
        score+=value;
        ScoreLabel.setText((String.format("%04d",score)));
    }
    public Integer getScore() {
        return score;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
