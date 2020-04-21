package com.megaman.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.megaman.game.Megaman;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=Megaman.V_WIDTH;
		config.height=Megaman.V_HEIGHT;
		config.resizable=false;
		new LwjglApplication(new Megaman(), config);
	}
}
