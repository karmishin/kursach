package io.github.karmishin.kursach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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
    private PolygonShape groundShape;
    private PolygonShape playerShape;

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
        batch.draw(background.sprite1, 0, 0, 800, 480);
        batch.draw(background.sprite2, 0, 0, 800, 480);
        batch.draw(background.sprite3, 0, 0, 800, 480);
        batch.draw(background.sprite4, 0, 0, 800, 480);
        batch.draw(background.sprite5, 0, 0, 800, 480);

        TextureRegion idleFrame = player.idleAnimation.getKeyFrame(elapsedTime, true);
        TextureRegion runningFrame = player.runningAnimation.getKeyFrame(elapsedTime, true);
        TextureRegion fallFrame = player.fallAnimation.getKeyFrame(elapsedTime, true);

        player.state = Player.PlayerState.IDLE;

        Vector2 vel = this.player.body.getLinearVelocity();
        Vector2 pos = this.player.body.getPosition();

        float velocityX = 0;
        float velocityY = -100.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            player.body.applyLinearImpulse(-0.80f, 0, this.player.body.getPosition().x, this.player.body.getPosition().y, true);
            velocityX = -100.0f;
//            player.body.setLinearVelocity(-100.0f, 0);
            player.state = Player.PlayerState.RUN;
            player.direction = Player.Direction.LEFT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocityX = 100.0f;
//            player.body.applyLinearImpulse(0.80f, 1, this.player.body.getPosition().x, this.player.body.getPosition().y, true);
//            player.body.setLinearVelocity(new Vector2(100.0f, 0));
            player.state = Player.PlayerState.RUN;
            player.direction = Player.Direction.RIGHT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            velocityY = 100.0f;
            player.state = Player.PlayerState.JUMP;
        }

        if (player.body.getLinearVelocity().y < 0) {
            player.state = Player.PlayerState.FALL;
        }

        player.body.setLinearVelocity(velocityX, velocityY);

        switch (player.state) {
            case IDLE:
                    boolean flip = (player.direction == Player.Direction.LEFT);
                    batch.draw(idleFrame, flip ? pos.x + player.width : pos.x,
                            pos.y,flip ? -player.width : player.width,
                            player.height);
                break;
            case RUN:
                flip = (player.direction == Player.Direction.LEFT);
                batch.draw(runningFrame, flip ? pos.x + player.width : pos.x,
                        pos.y,flip ? -player.width : player.width,
                        player.height);
                break;
            case JUMP:
                flip = (player.direction == Player.Direction.LEFT);
                batch.draw(player.jumpTexture, flip ? pos.x + player.width : pos.x,
                        pos.y,flip ? -player.width : player.width,
                        player.height);
                break;
            case FALL:
                flip = (player.direction == Player.Direction.LEFT);
                batch.draw(fallFrame, flip ? pos.x + player.width : pos.x,
                        pos.y, flip ? -player.width : player.width,
                        player.height);
        }
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
