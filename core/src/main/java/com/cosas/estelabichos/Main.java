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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.rmi.dgc.DGC;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {


    Array<Sprite> enemigos;

    SpriteBatch batch;
    Sprite prota,fondo,enemigo,aura;
    Rectangle hitBXprota,hitBXenemigo,hbxaura;


    Camera camara;
    static final int world_width= 600;
    static final int world_height= 440;

    public void movimiento_enemigo(){
        enemigo.translate(1,1);
    }

    public void movimiento_personaje(){
        float speed=100;
        float delta= Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            prota.translateY(speed*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            prota.translateY(-speed*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            prota.translateX(-speed*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            prota.translateX(speed*delta);
        }
    }


    @Override
    public void create() {
        setScreen(new firstScreen());

        // multiples enemigos
        enemigos=new Array<>(5);


        aura=new Sprite(new Texture(Gdx.files.internal("bicho.png")));
        hbxaura=new Rectangle();

        //cofiguracionese del fondo
        fondo=new Sprite(new Texture(Gdx.files.internal("fondo.png")));
        fondo.setPosition(20,20);
        fondo.setSize(world_width,world_height);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camara= new OrthographicCamera(30, 30 * (h / w));

        //todo sobre el personaje principal
        prota =new Sprite(new Texture("caracol.png"));
        prota.setSize(50,50);
        prota.setPosition(0,0);


        // todo sobre el enemigo
        enemigo=new Sprite(new Texture(Gdx.files.internal( "bicho.png")));
        enemigo.setSize(50,50);
        float ranX=MathUtils.random(0,fondo.getWidth()-enemigo.getWidth());
        float ranY=MathUtils.random(0,fondo.getHeight()-enemigo.getHeight());
        enemigo.setPosition(ranX,ranY);


        hitBXprota=new Rectangle();
        hitBXenemigo=new Rectangle();



        camara.update();
        batch=new SpriteBatch();


    }

    @Override
    public void render() {
        super.render();
        camara.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        movimiento_personaje();

        //hitbox's
        hitBXprota.set(prota.getX(), prota.getY(), prota.getWidth()/2, prota.getHeight()/2);
        hitBXenemigo.set(enemigo.getX(),enemigo.getY(),enemigo.getWidth()/2,enemigo.getHeight()/2);



        if (hitBXprota.overlaps(hitBXenemigo)) {
            System.out.println("choco");
            //prota.setPosition((prota.getX()-prota.getWidth()/2),(prota.getY()-prota.getHeight()/2));
            prota.setPosition(-20,150);
        }



        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            System.out.println("Prota X: "+(prota.getX()-prota.getWidth())+"---Y: "+(prota.getY()-prota.getHeight()+""));
        }

        movimiento_enemigo();





        //dibujar
        batch.begin();
        fondo.draw(batch);
        prota.draw(batch);

        enemigo.draw(batch);

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
