package io.github.karmishin.kursach;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Platform {
    World world;
    TiledMap map;
    float tileSize;

    public Platform(World world, TiledMap map) {
        this.world = world;
        this.map = map;
        buildBuildingBodies();
    }

    private void buildBuildingBodies() {
        MapObjects objects = map.getLayers().get(2).getObjects();
        tileSize = map.getProperties().get("tilewidth", Integer.class);
        build(objects, 1f);

        MapObjects obstacles = map.getLayers().get(4).getObjects();
        build(obstacles, 100f);

        MapObjects finish = map.getLayers().get(3).getObjects();
        build(finish, 10000f);
    }

    private void build(MapObjects objects, float friction) {
        for (MapObject mapObject : objects) {
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bodyDef);

            Fixture fixture = body.createFixture(getShapeFromRectangle(rectangle), 10.0f);
            fixture.setFriction(friction);

            body.setTransform(getTransformedCenterForRectangle(rectangle), 0);
        }
    }

    Shape getShapeFromRectangle(Rectangle rectangle) {
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(rectangle.width * 0.5f, rectangle.height * 0.5f);
        return polygonShape;
    }


    Vector2 getTransformedCenterForRectangle(Rectangle rectangle) {
        Vector2 center = new Vector2();
        rectangle.getCenter(center);
        return center.scl(1);
    }
}
