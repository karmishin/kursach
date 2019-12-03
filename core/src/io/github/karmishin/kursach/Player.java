package io.github.karmishin.kursach;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player {
    int width, height;
    PlayerState state;
    Direction direction;
    PolygonShape shape;

    BodyDef bodyDef;
    Body body;

    enum PlayerState {
        IDLE, RUN, JUMP
    }

    enum Direction {
        LEFT, RIGHT
    }

    Player() {
        width = 21;
        height = 35;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(21,35);
    }
}
