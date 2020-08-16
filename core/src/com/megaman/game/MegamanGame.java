package com.megaman.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megaman.game.Screen.MainMenuScreen;
//Game class is an applicationlistener that delegates to a screen.This allow application to easily have multiple screens.
//Game class is actually game loop.
public class MegamanGame extends Game {
	public SpriteBatch batch;//Have only spritebatch in project

	public static final int V_WIDTH = 2000;
	public static final int V_HEIGHT = 1000;
	public static final float PPM = 150;//PPM->pixel per meter use for scale example:scale makes Megaman doesn't drop slowly.
	private Sound mp3Sound;

	//Called when the Application is first created.
	@Override
	public void create() {
		batch = new SpriteBatch();
		mp3Sound = Gdx.audio.newSound(Gdx.files.internal("audio/music/MM1.03 Boss Attack (PS).mp3"));
		mp3Sound.setLooping(mp3Sound.loop(), true);
		mp3Sound.play();
		setScreen(new MainMenuScreen(this));//First Screen appears when we run project.

	}

	//Called when the Application should render itself.
	@Override
	public void render() {
		super.render();
	}

}
