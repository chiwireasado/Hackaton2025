package com.cosas.estelabichos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    SpriteBatch batch;


        float delta= Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)){
        }
        }


    @Override
    public void create() {
        setScreen(new firstScreen());


        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();


        batch=new SpriteBatch();



    }

    @Override
    public void render() {
        super.render();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
