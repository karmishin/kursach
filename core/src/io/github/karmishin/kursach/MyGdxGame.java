package io.github.karmishin.kursach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class MyGdxGame extends ApplicationAdapter {
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Background background;
    private Player player = new Player();
    private Ground ground;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Music music;
    private float elapsedTime = 0;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;


    @Override
    public void create() {
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        player.createPlayer(world);
        player.createResources();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/golosovanie.mp3"));
        music.setVolume(0);
        music.play();

        map = new TmxMapLoader().load("map/level.tmx");

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        tiledMapRenderer.setView(camera);

        background = new Background();
        ground = new Ground(world, camera);

        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        background.drawBackground(batch);
        player.getKeyFrames(elapsedTime);

        player.state = Player.PlayerState.IDLE;

        player.updatePosition();

        float velocityX = 0;
        float velocityY = -100.0f;

        player.control();

        player.flip(batch);

        batch.end();

        tiledMapRenderer.render();
        debugRenderer.render(world, camera.combined);

        world.step(1/60f, 6, 2);
    }

    @Override
    public void dispose() {
        batch.dispose();
        music.dispose();
        tiledMapRenderer.dispose();
        map.dispose();
        ground.groundBox.dispose();
    }
}
