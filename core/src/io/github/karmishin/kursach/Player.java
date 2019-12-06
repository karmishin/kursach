package io.github.karmishin.kursach;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player {
    int jumpsLeft = 12;
    int width, height;
    PlayerState state;
    Direction direction;
    PolygonShape shape;
    BodyDef bodyDef;
    Body body;
    Vector2 position;

    public TextureRegion idleFrame, runningFrame;
    public Animation<TextureRegion> runningAnimation, idleAnimation;
    public Texture jumpTexture, landingTexture;
    public TextureAtlas runningAtlas, idleAtlas;
    Vector2 velocity = new Vector2();
    Vector2 spawnPoint = new Vector2();

    enum PlayerState {
        IDLE, RUN, JUMP, FALL
    }

    enum Direction {
        LEFT, RIGHT
    }

    Player(Game game) {
        width = 21;
        height = 35;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        if (game.currentLevel == 0) {
            spawnPoint.x = 25;
            spawnPoint.y = 250;
        }

        bodyDef.position.set(spawnPoint);
    }

    public void createPlayer(World world) {
        body = world.createBody(this.bodyDef);
        body.setFixedRotation(true);
        this.shape = new PolygonShape();
        this.shape.setAsBox(11, 21, new Vector2(11, 20), 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = this.shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 30f;
        fixtureDef.restitution = 0;
        this.body.createFixture(fixtureDef);
    }

    public void createResources() {
        runningAtlas = new TextureAtlas(Gdx.files.internal("character/sprites/running.atlas"));
        runningAnimation = new Animation<TextureRegion>(0.05f, runningAtlas.getRegions());
        idleAtlas = new TextureAtlas(Gdx.files.internal("character/sprites/idle.atlas"));
        idleAnimation = new Animation<TextureRegion>(0.1f, idleAtlas.getRegions());
        jumpTexture = new Texture("character/sprites/jump outline.png");
        landingTexture = new Texture("character/sprites/landing outline.png");
    }

    public void getKeyFrames(float elapsedTime) {
        idleFrame = idleAnimation.getKeyFrame(elapsedTime, true);
        runningFrame = runningAnimation.getKeyFrame(elapsedTime, true);
    }

    public void updatePosition() {
        position = body.getPosition();
    }

    public void control() {

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.applyLinearImpulse(new Vector2(-70000f, 0), body.getWorldCenter(), true);
            state = PlayerState.RUN;
            direction = Direction.LEFT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            body.applyLinearImpulse(new Vector2(70000f, 0), body.getWorldCenter(), true);
            state = PlayerState.RUN;
            direction = Direction.RIGHT;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (jumpsLeft > 0) {
                body.applyLinearImpulse(new Vector2(0, 80000f), body.getWorldCenter(), true);
                jumpsLeft--;
            }
        }

        if (body.getLinearVelocity().y > 0) {
            state = PlayerState.JUMP;
        }

        if (body.getLinearVelocity().y < 0) {
            state = PlayerState.FALL;
        }
    }

    public void flip(SpriteBatch batch) {
        switch (state) {
            case IDLE:
                boolean flip = (direction == Direction.LEFT);
                batch.draw(idleFrame, flip ? position.x + width : position.x,
                        position.y, flip ? -width : width,
                        height);
                break;
            case RUN:
                flip = (direction == Direction.LEFT);
                batch.draw(runningFrame, flip ? position.x + width : position.x,
                        position.y, flip ? -width : width,
                        height);
                break;
            case JUMP:
                flip = (direction == Direction.LEFT);
                batch.draw(jumpTexture, flip ? position.x + width : position.x,
                        position.y, flip ? -width : width,
                        height);
                break;
            case FALL:
                flip = (direction == Direction.LEFT);
                batch.draw(landingTexture, flip ? position.x + width : position.x,
                        position.y, flip ? -width : width,
                        height);
        }
    }
}
