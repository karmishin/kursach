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
    int width, height;
    PlayerState state;
    Direction direction;
    PolygonShape shape;
    BodyDef bodyDef;
    Body body;
    public TextureRegion idleFrame, runningFrame, fallFrame;
    Vector2 position;

    public Animation<TextureRegion> runningAnimation, idleAnimation, fallAnimation;
    public Texture jumpTexture;
    public TextureAtlas runningAtlas, idleAtlas, fallAtlas;
    Vector2 velocity = new Vector2();

    enum PlayerState {
        IDLE, RUN, JUMP, FALL
    }

    enum Direction {
        LEFT, RIGHT
    }

    Player() {
        width = 21;
        height = 35;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(21,30);
    }

    public void createPlayer(World world) {
        body = world.createBody(this.bodyDef);
        this.shape = new PolygonShape();
        this.shape.setAsBox(11, 21, new Vector2(11, 20), 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = this.shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        this.body.createFixture(fixtureDef);
    }

    public void createResources() {
        runningAtlas = new TextureAtlas(Gdx.files.internal("character/sprites/running.atlas"));
        runningAnimation = new Animation<TextureRegion>(0.05f, runningAtlas.getRegions());
        idleAtlas = new TextureAtlas(Gdx.files.internal("character/sprites/idle.atlas"));
        idleAnimation = new Animation<TextureRegion>(0.1f, idleAtlas.getRegions());
        jumpTexture = new Texture("character/sprites/jump outline.png");
        fallAtlas = new TextureAtlas(Gdx.files.internal("character/sprites/fall.atlas"));
        fallAnimation = new Animation<TextureRegion>(0.1f, fallAtlas.getRegions());
    }

    public void getKeyFrames(float elapsedTime) {
        idleFrame = idleAnimation.getKeyFrame(elapsedTime, true);
        runningFrame = runningAnimation.getKeyFrame(elapsedTime, true);
        fallFrame = fallAnimation.getKeyFrame(elapsedTime, true);
    }

    public void updatePosition() {
        position = body.getPosition();
    }

    public void control() {

        velocity.x = 0;
        velocity.y = -100f;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            body.applyLinearImpulse(-0.80f, 0, this.body.getPosition().x, this.body.getPosition().y, true);
            velocity.x = -100.0f;
//            body.setLinearVelocity(-100.0f, 0);
            state = PlayerState.RUN;
            direction = Direction.LEFT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.x = 100.0f;
//            body.applyLinearImpulse(0.80f, 1, this.body.getPosition().x, this.body.getPosition().y, true);
//            body.setLinearVelocity(new Vector2(100.0f, 0));
            state = PlayerState.RUN;
            direction = Direction.RIGHT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            velocity.y = 100.0f;
            state = PlayerState.JUMP;
        }

        if (body.getLinearVelocity().y < 0) {
            state = PlayerState.FALL;
        }

        body.setLinearVelocity(velocity);
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
                batch.draw(fallFrame, flip ? position.x + width : position.x,
                        position.y, flip ? -width : width,
                        height);
        }
    }
}
