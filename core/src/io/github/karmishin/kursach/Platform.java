package io.github.karmishin.kursach;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class Platform {
    World world;
    TiledMap map;
    Camera camera;
    float tileSize;

    public Platform(World world, TiledMap map) {
        this.world = world;
        this.map = map;
        buildBuildingBodies();
    }

    private void buildBuildingBodies() {
        Array<RectangleMapObject> objects = map.getLayers().get("Слой тайлов 1").getObjects().getByType(RectangleMapObject.class);
        tileSize = 32;

        for (RectangleMapObject rectangleMapObject : objects) {
            Rectangle rectangle = rectangleMapObject.getRectangle();

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bodyDef);

            Fixture fixture = body.createFixture(getShapeFromRectangle(rectangle), 10.0f);
            fixture.setFriction(0.4f);

            body.setTransform(getTransformedCenterForRectangle(rectangle), 0);
        }
    }

    Shape getShapeFromRectangle(Rectangle rectangle) {
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(rectangle.width * 0.5F / tileSize, rectangle.height * 0.5F / tileSize);
        return polygonShape;
    }

    Vector2 getTransformedCenterForRectangle(Rectangle rectangle) {
        Vector2 center = new Vector2();
        rectangle.getCenter(center);
        return center.scl(1 / tileSize);
    }
}
