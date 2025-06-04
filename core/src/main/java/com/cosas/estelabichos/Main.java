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
import com.badlogic.gdx.scenes.scene2d.InputEvent;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    SpriteBatch batch;
    Sprite cosa,fondo;
    Camera camara;
    static final int world_width=550;
    static final int world_height=400;

    public void movimiento(){
        float speed=50;
        float delta= Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            cosa.translateY(speed*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            cosa.translateY(-speed*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            cosa.translateX(-speed*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            cosa.translateX(speed*delta);
        }
    }


    @Override
    public void create() {
        setScreen(new firstScreen());
        fondo=new Sprite(new Texture(Gdx.files.internal("bicho.png")));
        fondo.setPosition(0,0);
        fondo.setSize(world_width,world_height);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camara= new OrthographicCamera(30, 30 * (h / w));
        cosa=new Sprite(new Texture("bicho.png"));
        cosa.setSize(50,50);
        cosa.setPosition(150,150);

        camara.update();
        batch=new SpriteBatch();
        movimiento();
    }

    @Override
    public void render() {
        super.render();
        camara.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        movimiento();

        batch.begin();
        fondo.draw(batch);
        cosa.draw(batch);
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
