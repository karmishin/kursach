package io.github.karmishin.kursach.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.karmishin.kursach.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Kursach: The Game");
		config.setWindowedMode(800, 480);
		config.setWindowIcon("logo.png");
		new Lwjgl3Application(new Game(), config);
	}
}
