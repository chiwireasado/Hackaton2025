package com.cosas.estelabichos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class personajes {

    int width, height;
    float cordX, cordY;
    Sprite forma;

    personajes(int width, int height, float cordX, float cordY, Texture piel) {
        this.width = width;
        this.height = height;
        this.cordX = cordX;
        this.cordY = cordY;
        forma = new Sprite(piel);
    }

    public void movimiento() {
    }

}
