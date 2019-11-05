package io.github.karmishin.kursach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class MyGdxGame extends ApplicationAdapter {
	Background background;
	Player player;
	Animation<TextureRegion> runningAnimation;
	TextureAtlas atlas;
	OrthographicCamera camera;
	SpriteBatch batch;
	Music music;
	private float elapsedTime = 0;


	public MyGdxGame() {
		player = new Player();
		background = new Background();

	}
	
	@Override
	public void create () {
		atlas = new TextureAtlas(Gdx.files.internal("character/sprites/run.atlas"));
		runningAnimation = new Animation<TextureRegion>(0.033f, atlas.getRegions());

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

		player.texture = new Texture(Gdx.files.internal("character/sprites/stay/frame-01.png"));
		player.sprite = new Sprite();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		music = Gdx.audio.newMusic(Gdx.files.internal("music/golosovanie.mp3"));
		music.setVolume(1);
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		elapsedTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = runningAnimation.getKeyFrame(elapsedTime, true);

		batch.begin();
		batch.draw(background.sprite1, 0, 0, 800, 480);
		batch.draw(background.sprite2, 0, 0, 800, 480);
		batch.draw(background.sprite3, 0, 0, 800, 480);
		batch.draw(background.sprite4, 0, 0, 800, 480);
		batch.draw(background.sprite5, 0, 0, 800, 480);
		//batch.draw(player.sprite, player.rectangle.x, player.rectangle.y);

		batch.draw(currentFrame, player.rectangle.x, player.rectangle.y);
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
