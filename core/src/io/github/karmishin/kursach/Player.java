package io.github.karmishin.kursach;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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

    public Animation<TextureRegion> runningAnimation, idleAnimation, fallAnimation;
    public Texture jumpTexture;
    public TextureAtlas runningAtlas, idleAtlas, fallAtlas;

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
}
