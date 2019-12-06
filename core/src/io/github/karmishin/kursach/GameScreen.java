package io.github.karmishin.kursach;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;

public class GameScreen implements Screen {
    final Game game;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    BitmapFont font;
    private Background background;
    Player player;
    private Ground ground;
    private OrthographicCamera camera;
    SpriteBatch batch;
    private Music music;
    private float elapsedTime = 0;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;
    private Platform platform;
    private Timer timer;
    int seconds;
    private HUD hud;

    public GameScreen(Game game) {
        this.game = game;

        world = new World(new Vector2(0, -100), true);
        world.setContactListener(new ListenerClass(game));
        debugRenderer = new Box2DDebugRenderer();

        font = new BitmapFont();

        player = new Player(game);
        player.createPlayer(world);
        player.createResources();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/golosovanie.mp3"));
        music.setVolume(0);
        music.play();

        map = new TmxMapLoader().load("map/level" + game.currentLevel + ".tmx");

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        tiledMapRenderer.setView(camera);
        platform = new Platform(world, map);

        background = new Background();
        // ground = new Ground(world, camera);

        batch = new SpriteBatch();
        timer = new Timer();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                seconds++;
            }
        }, 1, 1);

        hud = new HUD(game, this);
    }

    @Override
    public void show() {
        timer.start();
    }

    @Override
    public void render(float delta) {
        world.step(1 / 60f, 6, 2);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        background.drawBackground(batch);

        player.getKeyFrames(elapsedTime);
        player.state = Player.PlayerState.IDLE;
        player.updatePosition();
        player.control();
        player.flip(batch);

        hud.renderHUD();
        batch.end();

        tiledMapRenderer.render();

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            game.setScreen(new GameScreen(game));
        }

        debugRenderer.render(world, camera.combined);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

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
