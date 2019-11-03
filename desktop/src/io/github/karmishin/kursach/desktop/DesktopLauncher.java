package io.github.karmishin.kursach.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.karmishin.kursach.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Kursach: The Game";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
