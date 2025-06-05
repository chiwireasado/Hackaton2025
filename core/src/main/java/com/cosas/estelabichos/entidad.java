package com.cosas.estelabichos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class entidad {

    int width, height;
    float cordX, cordY;
    Sprite forma;

    entidad(int width, int height, float cordX, float cordY, Texture piel) {
        this.width = width;
        this.height = height;
        this.cordX = cordX;
        this.cordY = cordY;
        forma = new Sprite(piel);
    }

    public void movimiento() {
    }

}
