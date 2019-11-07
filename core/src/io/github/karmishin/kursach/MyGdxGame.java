package io.github.karmishin.kursach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    public void create() {
        runningAtlas = new TextureAtlas(Gdx.files.internal("character/sprites/running.atlas"));
        runningAnimation = new Animation<TextureRegion>(0.05f, runningAtlas.getRegions());
        idleAtlas = new TextureAtlas(Gdx.files.internal("character/sprites/idle.atlas"));
        idleAnimation = new Animation<TextureRegion>(0.1f, idleAtlas.getRegions());

        background = new Background();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        music = Gdx.audio.newMusic(Gdx.files.internal("music/golosovanie.mp3"));
        music.setVolume(0);
        music.play();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        TextureRegion idleFrame = idleAnimation.getKeyFrame(elapsedTime, true);
        TextureRegion runningFrame = runningAnimation.getKeyFrame(elapsedTime, true);

        player.state = Player.PlayerState.IDLE;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.rectangle.x -= 100 * Gdx.graphics.getDeltaTime();
            player.state = Player.PlayerState.RUN;
            player.direction = Player.Direction.LEFT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.rectangle.x += 100 * Gdx.graphics.getDeltaTime();
            player.state = Player.PlayerState.RUN;
            player.direction = Player.Direction.RIGHT;
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background.sprite1, 0, 0, 800, 480);
        batch.draw(background.sprite2, 0, 0, 800, 480);
        batch.draw(background.sprite3, 0, 0, 800, 480);
        batch.draw(background.sprite4, 0, 0, 800, 480);
        batch.draw(background.sprite5, 0, 0, 800, 480);

        switch (player.state) {
            case IDLE:
                    boolean flip = (player.direction == Player.Direction.LEFT);
                    // todo
                break;
            case RUN:
                batch.draw(runningFrame, player.rectangle.x, player.rectangle.y);
                break;
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        music.dispose();
    }
}
