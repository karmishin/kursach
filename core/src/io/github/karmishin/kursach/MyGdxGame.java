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
	Player player;
	SpriteBatch batch;
	Music music;

	public MyGdxGame() {
		player = new Player();
	}
	
	@Override
	public void create () {
		player.texture = new Texture(Gdx.files.internal("character/sprites/idle outline.gif"));
		player.sprite = new Sprite(player.texture);
		batch = new SpriteBatch();

		music = Gdx.audio.newMusic(Gdx.files.internal("music/golosovanie.mp3"));
		music.setVolume(0);
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(player.sprite, player.rectangle.x, player.rectangle.y);
		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.rectangle.x -= 100 * Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.rectangle.x += 100 * Gdx.graphics.getDeltaTime();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.texture.dispose();
	}
}
