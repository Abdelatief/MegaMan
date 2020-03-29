package com.mygdx.game.Scenes;

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
import com.mygdx.game.MyGdxGame;



public class Hud implements Disposable {
    public Stage stage;
   private Viewport viewport;

    private Integer worldTimer;
    private float timecount;
    private  Integer score;
    Label countdownLabel;
    Label ScoreLabel;
    Label levelabel;
    Label worldLabel;
    Label timeLabel;
    Label MegamanLabel;
    public  Hud(SpriteBatch sb)
    {
        worldTimer=300;
        timecount=0;
        score=0;
        viewport=new FitViewport(MyGdxGame.V_WIDTH,MyGdxGame.V_HEIGHT,new OrthographicCamera());

       stage =new Stage(viewport,sb);
        Table table=new Table();
        table.top();
        table.setFillParent(true);
        countdownLabel= new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        ScoreLabel=new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelabel=new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel=new Label("WORLD",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        MegamanLabel=new Label("MEGA MAN ",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel=new Label("Time",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(MegamanLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(ScoreLabel).expandX();
        table.add(levelabel).expandX();
        table.add(countdownLabel).expandX();
        stage.addActor(table);
    }

 @Override
 public void dispose() {
  stage.dispose();
 }
}
