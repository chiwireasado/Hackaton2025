package com.cosas.estelabichos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import static com.cosas.estelabichos.Main.world_height;
import static com.cosas.estelabichos.Main.world_width;

public class personaje{


    private int width;
    private int height;
    private int x;
    private int y;
    private float velocidad;
    Sprite forma;
    Rectangle hitbox;
    private int vidas;

    personaje(Texture piel) {
        width=0;
        height=0;
        setX(0);
        setY(0);
        forma = new Sprite(piel);
        hitbox=new Rectangle(getX(), getY(),width,height);
        setVelocidad(250);
        setVidas(3);
    }

    public void moverse(){
        float speedX=0;
        float speedY=0;
        float delta= Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
            speedY=250;
            speedX=0;
            forma.translateY(speedY*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            speedY=-250;
            forma.translateY(speedY*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            speedX=-250;
            forma.translateX(speedX * delta);
            forma.flip(true,false);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            forma.flip(false,false);
            speedX=250;
            forma.translateX(speedX * delta);
        }

        forma.translate(speedX * delta,speedY*delta);


        forma.setX(MathUtils.clamp(forma.getX(),-10,world_width));
        forma.setY(MathUtils.clamp(forma.getY(),-10,world_height));

    }
    public void dibujar(SpriteBatch batch){
        forma.draw(batch);
    }
    public void setTamaño(int width,int height){
        forma.setSize(width,height);
    }

    public void setHitbox(){
        hitbox.setPosition(getX(),getY());
    }

    public void actualizaposicion(){
        setX((int)forma.getX());
        setY((int)forma.getY());
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }


    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}
