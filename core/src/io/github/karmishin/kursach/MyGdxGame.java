package io.github.karmishin.kursach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	Background background;
	Player player;

	OrthographicCamera camera;
	SpriteBatch batch;
	Music music;

	public MyGdxGame() {
		player = new Player();
		background = new Background();
	}
	
	@Override
	public void create () {
		background.texture1 = new Texture(Gdx.files.internal("background/plx-1.png"));
		background.texture2 = new Texture(Gdx.files.internal("background/plx-2.png"));
		background.texture3 = new Texture(Gdx.files.internal("background/plx-3.png"));
		background.texture4 = new Texture(Gdx.files.internal("background/plx-4.png"));
		background.texture5 = new Texture(Gdx.files.internal("background/plx-5.png"));

		background.sprite1 = new Sprite(background.texture1);
		background.sprite2 = new Sprite(background.texture2);
		background.sprite3 = new Sprite(background.texture3);
		background.sprite4 = new Sprite(background.texture4);
		background.sprite5 = new Sprite(background.texture5);

		player.texture = new Texture(Gdx.files.internal("character/sprites/idle outline.gif"));
		player.sprite = new Sprite(player.texture);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
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
		batch.draw(background.sprite1, 0, 0, 800, 480);
		batch.draw(background.sprite2, 0, 0, 800, 480);
		batch.draw(background.sprite3, 0, 0, 800, 480);
		batch.draw(background.sprite4, 0, 0, 800, 480);
		batch.draw(background.sprite5, 0, 0, 800, 480);
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
