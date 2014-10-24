package com.onequest.dungeon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.onequest.dungeon.MainClass;
/**
 * Desktop launcher wrapper for project
 * @author Rob Vogel
 * @version 0.0.0001
 *
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new MainClass(), config);
	}
}
