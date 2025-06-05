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

import java.sql.Array;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    SpriteBatch batch;
    Sprite caracol, partida;
    Camera pointOfView;
    static final int world_width= 550;
    static final int world_height= 400;


    public void movimiento(){
        float speed=20;
        float delta= Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            caracol.translateY(speed*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            caracol.translateY(-speed*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            caracol.translateX(-speed*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            caracol.translateX(speed*delta);
        }
    }


    @Override
    public void create() {
        setScreen(new firstScreen());

        partida =new Sprite(new Texture(Gdx.files.internal("fondoPartida.png")));
        partida.setPosition(45,45);
        partida.setSize(world_width,world_height);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        pointOfView = new OrthographicCamera(30, 30 * (h / w));

        caracol = new Sprite(new Texture("caracol.png"));
        caracol.setSize(100,100);
        caracol.setPosition(0,0);

        pointOfView.update();
        batch=new SpriteBatch();
        movimiento();



    }

    @Override
    public void render() {
        super.render();
        pointOfView.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        movimiento();

        batch.begin();
        partida.draw(batch);

        caracol.draw(batch);
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
