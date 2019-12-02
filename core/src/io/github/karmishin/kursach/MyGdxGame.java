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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class MyGdxGame extends ApplicationAdapter {
    private World world;
    Box2DDebugRenderer debugRenderer;

    private Background background;
    private Player player = new Player();
    private Animation<TextureRegion> runningAnimation, idleAnimation;
    private TextureAtlas runningAtlas, idleAtlas;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Music music;
    private float elapsedTime = 0;
    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;

    @Override
    public void create() {
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();


        player.body = world.createBody(player.bodyDef);

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

        map = new TmxMapLoader().load("map/level.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);
        tmr.setView(camera);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background.sprite1, 0, 0, 800, 480);
        batch.draw(background.sprite2, 0, 0, 800, 480);
        batch.draw(background.sprite3, 0, 0, 800, 480);
        batch.draw(background.sprite4, 0, 0, 800, 480);
        batch.draw(background.sprite5, 0, 0, 800, 480);


        TextureRegion idleFrame = idleAnimation.getKeyFrame(elapsedTime, true);
        TextureRegion runningFrame = runningAnimation.getKeyFrame(elapsedTime, true);

        player.state = Player.PlayerState.IDLE;

        Vector2 vel = this.player.body.getLinearVelocity();
        Vector2 pos = this.player.body.getPosition();

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.body.applyLinearImpulse(-0.80f, 0, pos.x, pos.y, true);
            player.state = Player.PlayerState.RUN;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.rectangle.x += 100 * Gdx.graphics.getDeltaTime();
            player.state = Player.PlayerState.RUN;
            player.direction = Player.Direction.RIGHT;
        }



        switch (player.state) {
            case IDLE:
                    boolean flip = (player.direction == Player.Direction.LEFT);
                    batch.draw(idleFrame, flip ? pos.x + player.rectangle.width : pos.x,
                            pos.y,flip ? -player.rectangle.width : player.rectangle.width,
                            player.rectangle.height);
                break;
            case RUN:
                 flip = (player.direction == Player.Direction.LEFT);
                batch.draw(runningFrame, flip ? pos.x + player.rectangle.width : pos.x,
                        pos.y,flip ? -player.rectangle.width : player.rectangle.width,
                        player.rectangle.height);
                break;
        }
        batch.end();
        tmr.render();
        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);
    }

    @Override
    public void dispose() {
        batch.dispose();
        music.dispose();
        tmr.dispose();
        map.dispose();

    }
}
