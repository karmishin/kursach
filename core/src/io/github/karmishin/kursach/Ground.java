package io.github.karmishin.kursach;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Ground {
    BodyDef bodyDef;
    Body groundBody;
    PolygonShape groundBox;

    public Ground(World world, Camera camera) {
        bodyDef = new BodyDef();
        groundBox = new PolygonShape();
        bodyDef.position.set(new Vector2(0, 20));

        groundBody = world.createBody(bodyDef);
        groundBody.setType(BodyDef.BodyType.DynamicBody);
        groundBox.setAsBox(camera.viewportWidth, camera.viewportHeight);
        Fixture fixture = groundBody.createFixture(groundBox, 0.0f);
        fixture.setFriction(1f);

    }
}