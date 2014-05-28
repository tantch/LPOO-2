package com.tantch.Taurel.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tantch.Taurel.TaurelGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new TaurelGame(), config);
		config.title = "Taurel's Walk";
		config.height = 720;
		config.width = 1200;
		config.vSyncEnabled = true;
		config.useGL30 = true;

	}
}


