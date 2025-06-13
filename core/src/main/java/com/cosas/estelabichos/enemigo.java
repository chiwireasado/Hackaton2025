package com.cosas.estelabichos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.cosas.estelabichos.Main.world_height;
import static com.cosas.estelabichos.Main.world_width;

public class enemigo {
    private int width;
    private int height;
    private int x;
    private int y;
    private Vector2 velocidadEnemigo;
    Sprite forma;
    Rectangle hitbox;

    enemigo(Texture piel) {
        forma = new Sprite(piel);
        hitbox=new Rectangle(getX(), getY(),width,height);
        setVelocidadEnemigo(new Vector2(100, 120));

    }

    public void movimiento_enemigo() {
        float delta = Gdx.graphics.getDeltaTime();

        float rotacion= MathUtils.random(-120,120)*delta;

        if (forma.getX()-width<=0 || forma.getX()>=world_width-width) {
            getVelocidadEnemigo().x *= -1;
            getVelocidadEnemigo().y *=rotacion;
        }

        if (forma.getY()-height<0 || forma.getY()>= world_height-height) {
            getVelocidadEnemigo().y *= -1;
            getVelocidadEnemigo().x *=rotacion;
        }

        forma.translate(getVelocidadEnemigo().x * delta, getVelocidadEnemigo().y * delta);

        forma.setX(MathUtils.clamp(forma.getX(),0,world_width));
        forma.setY(MathUtils.clamp(forma.getY(),0,world_height));

        hitbox.setPosition(forma.getX(), forma.getY());

    }

    public void dibujar(SpriteBatch batch){
        forma.draw(batch);
    }
    public void setTamaño(int width,int height){
        forma.setSize(width,height);
        setWidth(width-10);
        setHeight(height-10);
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

    public Vector2 getVelocidadEnemigo() {
        return velocidadEnemigo;
    }

    public void setVelocidadEnemigo(Vector2 velocidadEnemigo) {
        this.velocidadEnemigo = velocidadEnemigo;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
