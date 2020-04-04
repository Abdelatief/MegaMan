package com.megaman.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megaman.game.Screen.Playscreen;

public class Megaman extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH=400;
	public static final int V_HEIGHT=208;
	public static final float PPM=100;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Playscreen(this));

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
