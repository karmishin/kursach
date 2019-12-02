package io.github.karmishin.kursach;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Player {
    Texture texture;
    Sprite sprite;
    Rectangle rectangle;
    PlayerState state;
    Direction direction;

    BodyDef bodyDef;
    Body body;

    enum PlayerState {
        IDLE, RUN
    }

    enum Direction {
        LEFT, RIGHT
    }

    Player() {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(10,50);

        rectangle = new Rectangle();

        rectangle.x = 400;
        rectangle.y = 50;
        rectangle.width = 21;
        rectangle.height = 35;
    }
}
