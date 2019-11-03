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
	Background background;
	Player player;
	SpriteBatch batch;
	Music music;


	public MyGdxGame() {
		player = new Player();
		background = new Background();
	}
	
	@Override
	public void create () {
		background.texture1 = new Texture(Gdx.files.internal("background/plx-1.png"));
		background.sprite1 = new Sprite(background.texture1);
		background.sprite1.setSize(800, 600);

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
		batch.draw(background.sprite1, 0,0);
		batch.draw(player.sprite, player.rectangle.x, player.rectangle.y);
		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.rectangle.x -= 100 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
				player.sprite.flip(true,false);
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.rectangle.x += 100 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
				player.sprite.flip(true,false);
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.texture.dispose();
	}
}
