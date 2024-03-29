package io.github.karmishin.kursach;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MainMenuScreen implements Screen {
    final Game game;
    OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;

    public MainMenuScreen(final Game game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        map = new TmxMapLoader().load("map/mainmenu.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        tiledMapRenderer.setView(camera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.2f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.render();
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Kursach: The Game!", 100, 150);
        game.font.draw(game.batch, "Press ANY KEY to begin... ", 100, 100);
        game.font.draw(game.batch, "(c) 2019. Biba&Boba Entertainment. All rights reserved.", 20, 20);
        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
