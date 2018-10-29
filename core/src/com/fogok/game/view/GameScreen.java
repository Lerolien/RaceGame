package com.fogok.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fogok.game.model.Car;

public class GameScreen implements Screen {

    private Texture carTexture;
    private SpriteBatch batch;
    private Car car;
    private OrthographicCamera camera;

    public static float deltaC;

    @Override
    public void show() {
        batch = new SpriteBatch();
        carTexture = new Texture(Gdx.files.internal("car.png"));
        carTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        car = new Car(carTexture, 0, 0, 1f, 1f * 2.09f);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        deltaC = delta;

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        car.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(20f, 20f * aspectRatio);

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
        carTexture.dispose();
        batch.dispose();
    }
}
