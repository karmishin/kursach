package io.github.karmishin.kursach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite sprite;
	Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("character/sprites/idle outline.gif");
		sprite = new Sprite(img);

		music = Gdx.audio.newMusic(Gdx.files.internal("music/golosovanie.mp3"));
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(sprite, 0, 0);
		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
