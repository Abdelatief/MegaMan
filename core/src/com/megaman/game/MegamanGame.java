package com.megaman.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megaman.game.Screen.MainMenuScreen;
import com.megaman.game.Screen.Playscreen;

public class MegamanGame extends Game {
	public SpriteBatch batch;

	public static final int V_WIDTH=2000;
	public static final int V_HEIGHT=1000;
	public static final float PPM=150;
	private Sound mp3Sound;
	@Override
	public void create () {
		batch = new SpriteBatch();
		/*mp3Sound = Gdx.audio.newSound(Gdx.files.internal("audio/music/MM1.03 Boss Attack (PS).mp3"));
		mp3Sound.setLooping(mp3Sound.loop(),true);
		mp3Sound.play();*/
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
