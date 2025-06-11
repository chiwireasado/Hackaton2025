package com.cosas.estelabichos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import static com.cosas.estelabichos.Main.world_height;
import static com.cosas.estelabichos.Main.world_width;

public class personaje{

    ArrayList <Vector2> posiciones=new ArrayList<>();
    Vector2 posicion=new Vector2();
    int width, height, cordX, cordY;
    float velocidad;
    Sprite forma,aura;
    Rectangle hitbox,hbxaura;

    personaje(int width, int height, Texture piel) {
        this.width = width;
        this.height = height;
        cordX =0;
        cordY =0;
        forma = new Sprite(piel,cordX,cordY,width,height);
        hitbox=new Rectangle(cordX,cordY,width,height);
        velocidad=250;
    }

    public void movimiento() {
        float delta= Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            forma.translate((cordX+velocidad)*delta,((cordY+velocidad)*delta));
            posicion.add(cordX,cordY);
            posiciones.add(posicion);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            forma.translate((cordX+velocidad)*delta,((cordY+velocidad)*delta));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            forma.translate(((cordX+velocidad)*delta),(cordY+velocidad)*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            forma.translate(((cordX+velocidad)*delta),(cordY+velocidad)*delta);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)){
            forma.flip(true,false);
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.A)){
            forma.flip(true,false);
        }

    }

}
