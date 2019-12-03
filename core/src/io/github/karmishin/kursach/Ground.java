package io.github.karmishin.kursach;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Ground {
    BodyDef bodyDef;
    Body groundBody;
    PolygonShape groundBox;

    public Ground() {
        bodyDef = new BodyDef();
        groundBox = new PolygonShape();
    }
}
