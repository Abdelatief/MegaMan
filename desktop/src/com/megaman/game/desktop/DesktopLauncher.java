package com.megaman.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.megaman.game.MegamanGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=MegamanGame.V_WIDTH;
		config.height=MegamanGame.V_HEIGHT;
		config.resizable=false;
		new LwjglApplication(new MegamanGame(), config);
	}
}
