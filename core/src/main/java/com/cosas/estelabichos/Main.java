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
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    int [][]mapa ={ // mapa de 16x16 en pixeles
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    Array<Sprite> enemigos;
    Array<Sprite> auras;
    static Array <Integer> cordx,cordy;


    Texture pared,piso;
    Sprite pare;



    SpriteBatch batch;
    Sprite prota,enemigo,aura;
    Rectangle hitBXprota,hitBXenemigo,hbxaura;


    Camera camara;
    static float ranX;
    static float ranY;
    static final int world_width= 600;
    static final int world_height= 440;

    final int pixeles =32;


    public void movimiento_enemigo() {

        float delta = Gdx.graphics.getDeltaTime();

        if (hitBXenemigo.getX()<=0 || enemigo.getX()>=world_width-hitBXenemigo.getWidth()){
            ranX=-ranX;
        }
        if (hitBXenemigo.getY()<=0 || enemigo.getY()>=world_height-hitBXenemigo.getHeight()){
            ranY=-ranY;
        }
        enemigo.translate(ranX*delta, ranY* delta);

        enemigo.setX(MathUtils.clamp(enemigo.getX()+hitBXenemigo.getWidth(),0,world_width-hitBXenemigo.getWidth()));
        enemigo.setY(MathUtils.clamp(enemigo.getY()+hitBXenemigo.getHeight(),0,world_height-hitBXenemigo.getHeight()));
    }


    public void movimiento_personaje(){
        float speedX=0;
        float speedY=0;
        float delta= Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            speedY=250;
            speedX=0;
            prota.translate(speedX * delta,speedY*delta);
            cordx.add((int)prota.getX());
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            speedY=-250;
            prota.translate(speedX * delta,speedY*delta);
            cordx.add((int)prota.getX());
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            speedX=-250;
            prota.translate(speedX * delta,speedY*delta);
            cordy.add((int)prota.getY());
            prota.flip(true,false);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)){

            prota.flip(false,false);
            speedX=250;
            prota.translate(speedX * delta,speedY*delta);
            cordy.add((int)prota.getY());
        }

        prota.translate(speedX * delta,speedY*delta);


        prota.setX(MathUtils.clamp(prota.getX(),-10,world_width));
        prota.setY(MathUtils.clamp(prota.getY(),-10,world_height));

    }




    public void crea_aura(){
        // cracion aura
        aura=new Sprite(new Texture(Gdx.files.internal("background.png")));
        aura.setSize(10,10);
        aura.setPosition((hitBXprota.getX()+hitBXprota.getWidth()),(hitBXprota.getY()+hitBXprota.getHeight()));
        hbxaura=new Rectangle(aura.getX(),aura.getY(),aura.getWidth(),aura.getHeight());
        hbxaura.setPosition(aura.getX(),aura.getY());
        auras.add(aura);

    }

    @Override
    public void create() {
        setScreen(new firstScreen());

        cordx=new Array<>();
        cordy=new Array<>();

        // multiples enemigos
        enemigos=new Array<>(5);
        auras=new Array<>();


        // si se quita esto exzplota
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camara= new OrthographicCamera(30, 30 * (h / w));


        //todo sobre el personaje principal
        prota =new Sprite(new Texture("caracol.png"));
        prota.setSize(50,50);
        prota.setPosition(20,20);


        // todo sobre el enemigo
        enemigo=new Sprite(new Texture(Gdx.files.internal( "bicho.png")));
        enemigo.setSize(50,50);
        ranX=MathUtils.random(0,world_width-enemigo.getWidth());
        ranY=MathUtils.random(0,world_height-enemigo.getHeight());
        enemigo.setPosition(ranX,ranY);

        //hitbox's iniciadas
        hitBXprota=new Rectangle();
        hitBXenemigo=new Rectangle();


        crea_aura();

        piso=new Texture(Gdx.files.internal("fondo.png"));
        pared=new Texture(Gdx.files.internal("background.png"));


        camara.update();
        batch=new SpriteBatch();


        /*
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j <mapa[i].length; j++) {

                if (mapa[i][j]==1){
                    batch.draw(pared,i* pixeles,j* pixeles);
                    Rectangle hitpare=new Rectangle(i,j,pixeles,pixeles);
                    hitparedes.add(hitpare);

                }else {
                    batch.draw(piso,i* pixeles,j* pixeles);
                }

            }
        }*/




    }

    @Override
    public void render() {
        super.render();
        camara.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        // movimientos
        movimiento_personaje();
        movimiento_enemigo();

        //hitbox's configuracion
        hitBXprota.set(prota.getX(), prota.getY(), prota.getWidth()/2, prota.getHeight()/2);
        hitBXenemigo.set(enemigo.getX(),enemigo.getY(),enemigo.getWidth()-15,enemigo.getHeight()-15);


        // colision
        if (hitBXprota.overlaps(hitBXenemigo)) {
            System.out.println("choco");
            //prota.setPosition((prota.getX()-prota.getWidth()/2),(prota.getY()-prota.getHeight()/2));
            prota.setPosition(-20,150);
        }



        crea_aura();









        Array<Rectangle> paredes = new Array<Rectangle>();


        if (prota.getX()<=0 || prota.getX()>=world_width-10){
            auras.clear();
            pare=new Sprite(pared,cordx.get(0),cordy.get(cordy.size-1),cordy.get(cordy.size-1)*pixeles,cordy.get(cordy.size-1)*pixeles);
            System.out.println(cordx.get(0));
            System.out.println(cordy.get(cordy.size-1));

        }
        if (prota.getY()<0 || prota.getY()>= world_height-10){
            auras.clear();
        }



        for(Rectangle pare:paredes){
            if (hitBXenemigo.overlaps(pare)){
                System.out.println("miau");
            }

        }

        //dibujar
        batch.begin();


        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                float x = i * pixeles;
                float y = j * pixeles;

                if (mapa[i][j] == 1) {
                    batch.draw(pared, x, y);
                    paredes.add(new Rectangle(x, y, pixeles, pixeles));
                } else {
                    batch.draw(piso, x, y);
                }
            }
        }


        prota.draw(batch);
        enemigo.draw(batch);

        for (Sprite aura: auras){
            aura.draw(batch);
        }




        batch.end();

    }



    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        piso.dispose();
        pared.dispose();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
