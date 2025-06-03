package com.cosas.estelabichos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    float speed;
    SpriteBatch batch;
    Texture img;
    Sprite cosa;


    void movimentos(){
        if (Gdx.input.isButtonPressed(Input.Keys.W)){
            cosa.translateY(speed);
        }
        else if (Gdx.input.isButtonPressed(Input.Keys.S)){
            cosa.translateY(-speed);
        }
        else if (Gdx.input.isButtonPressed(Input.Keys.A)){
            cosa.translateX(-speed);
        }
        else if (Gdx.input.isButtonPressed(Input.Keys.D)){
            cosa.translateX(speed);
        }

    }

    @Override
    public void render() {
        super.render();

        movimentos();

        batch.begin();
        cosa.setPosition(0,0);
        cosa.draw(batch);
        batch.end();

    }

    @Override
    public void create() {
        setScreen(new firstScreen());
        batch=new SpriteBatch();
        speed= .23f;
        img=new Texture("bicho.png");
        cosa=new Sprite(img,20,20);


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
