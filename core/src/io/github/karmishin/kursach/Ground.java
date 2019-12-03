package io.github.karmishin.kursach;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ground {
    BodyDef bodyDef;
    Body groundBody;
    PolygonShape groundBox;

    public Ground(World world, Camera camera) {
        bodyDef = new BodyDef();
        groundBox = new PolygonShape();

        bodyDef.position.set(new Vector2(0, 20));
        groundBody = world.createBody(bodyDef);
        groundBox.setAsBox(camera.viewportWidth, 10.0f);
        groundBody.createFixture(groundBox, 10.0f);
    }
}
