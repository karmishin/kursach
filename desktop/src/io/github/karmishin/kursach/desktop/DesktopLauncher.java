package io.github.karmishin.kursach.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.karmishin.kursach.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Kursach: The Game");
		config.setWindowedMode(800, 480);
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
