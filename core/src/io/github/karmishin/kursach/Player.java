package io.github.karmishin.kursach;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    Texture texture;
    Sprite sprite;
    Rectangle rectangle;
    PlayerState state;

    enum PlayerState {
        IDLE, RUN
    }

    Player() {
        rectangle = new Rectangle();

        rectangle.x = 400;
        rectangle.y = 20;
        rectangle.width = 21;
        rectangle.height = 35;
    }
}
