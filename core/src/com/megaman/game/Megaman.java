package com.megaman.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megaman.game.Screen.MainMenuScreen;
import com.megaman.game.Screen.Playscreen;

public class Megaman extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH=2000;
	public static final int V_HEIGHT=1000;
	public static final float PPM=150;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
		super.render();
	}
	
	/*@Override
	public void dispose () {
		batch.dispose();

	}*/
}
