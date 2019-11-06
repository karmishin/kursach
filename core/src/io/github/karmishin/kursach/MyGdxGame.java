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
    private Background background;
	private Player player = new Player();
	private Animation<TextureRegion> runningAnimation, idleAnimation;
	private TextureAtlas runningAtlas, idleAtlas;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Music music;
	private float elapsedTime = 0;

	
	@Override
	public void create () {
		runningAtlas = new TextureAtlas(Gdx.files.internal("character/sprites/running.atlas"));
		runningAnimation = new Animation<TextureRegion>(0.033f, runningAtlas.getRegions());
		idleAtlas = new TextureAtlas(Gdx.files.internal("character/sprites/idle.atlas"));
		idleAnimation = new Animation<TextureRegion>(0.1f, idleAtlas.getRegions());

        background = new Background();

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
		TextureRegion currentFrame = idleAnimation.getKeyFrame(elapsedTime, true);
        new Background();
		batch.begin();
		batch.draw(background.sprite1, 0, 0, 800, 480);
		batch.draw(background.sprite2, 0, 0, 800, 480);
		batch.draw(background.sprite3, 0, 0, 800, 480);
		batch.draw(background.sprite4, 0, 0, 800, 480);
		batch.draw(background.sprite5, 0, 0, 800, 480);
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
		music.dispose();
	}
}
