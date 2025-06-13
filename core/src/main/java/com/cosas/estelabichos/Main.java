package com.cosas.estelabichos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    int[][] mapa = { // mapa de 16x16 en pixeles
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    Array<enemigo> enemigos;
    Array<Sprite> auras;
    Array<Rectangle> hitBXauras, hitBXenemigos;


    Texture pared, piso, prueba;


    personaje prota;
    int posXActualprota;
    int posYActualprota;
    boolean conectamos;


    enemigo enemigo;


    SpriteBatch batch;
    Sprite aura;
    Rectangle hitBXaura;

    BitmapFont fontim,vidas,fontpunt;
    int puntos;
    int temporizador;
    float tiempo;


    Camera camara;
    static float ranX;
    static float ranY;
    static final int world_width= 600;
    static final int world_height= 440;

    final int pixeles =32;





    public void crea_enemigo() {
        enemigo = new enemigo(new Texture(Gdx.files.internal("bicho.png")));
        enemigo.setTamaño(50,50);
        ranX = MathUtils.random(enemigo.getWidth(), world_width - enemigo.forma.getWidth());
        ranY = MathUtils.random(enemigo.getHeight(), world_height - enemigo.forma.getHeight());
        enemigo.forma.setPosition(ranX, ranY);

        enemigos.add(enemigo);
    }



    public void crea_aura(){
        // cracion aura
        aura=new Sprite(new Texture(Gdx.files.internal("background.png")));
        aura.setSize(pixeles,pixeles);
        aura.setPosition(posXActualprota, posYActualprota);
        hitBXaura=new Rectangle(aura.getX(),aura.getY(),aura.getWidth(),aura.getHeight());


        hitBXauras.add(hitBXaura);
        auras.add(aura);
    }

    public void pon_aura_en_matriz(){
        int pixelx = prota.getX() / pixeles;
        int pixely = prota.getY() / pixeles;

        for (int i=0;i<auras.size;i++) {

            if (pixelx >= 0 && pixelx < mapa.length && pixely >= 0 && pixely < mapa[0].length) {
                if (mapa[pixelx][pixely] != 1) {
                    mapa[pixelx][pixely] = 2;
                }
            }
        }
    }
    public void correTiempo(float segundos) {
        temporizador -= (int) segundos;
    }

    public void sumarPuntos(int cantidad) {
        puntos += cantidad;
    }


    @Override
    public void create() {
        setScreen(new firstScreen());

        conectamos=false;
        fontim =new BitmapFont();
        fontpunt=new BitmapFont();
        vidas=new BitmapFont();
        fontim.getData().setScale(2);
        vidas.getData().setScale(1);
        fontpunt.getData().setScale(2);


        temporizador=3000;


        enemigos=new Array<>(5);
        auras=new Array<>();
        hitBXauras=new Array<>();
        hitBXenemigos=new Array<>(5);


        // si se quita esto exzplota
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camara= new OrthographicCamera(30, 30 * (h / w));


        //todo sobre el personaje principal
        prota =new personaje(new Texture("caracol.png"));
        prota.setTamaño(100,100);


        for (int i=0;i<6;i++){
            crea_enemigo();
        }


        crea_aura();

        piso=new Texture(Gdx.files.internal("fondo.png"));
        pared=new Texture(Gdx.files.internal("background.png"));
        prueba=new Texture(Gdx.files.internal("prueba.png"));



        camara.update();
        batch=new SpriteBatch();



    }

    @Override
    public void render() {
        super.render();
        camara.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        prota.actualizaposicion();
        tiempo+=Gdx.graphics.getDeltaTime();



        posXActualprota = prota.getX();
        posYActualprota = prota.getY();


        if (temporizador>=0){
            correTiempo(tiempo);
            if (temporizador<=0){
                prota.setVidas(0);
            }
        }


        // movimientos
        prota.moverse();



        //hitbox's configuracion
        prota.hitbox.set(prota.getX(), prota.getY(), prota.forma.getWidth()/2, prota.forma.getHeight()/2);



        crea_aura();

        if (prota.getVidas()==0){
            batch.dispose();
        }


        if (prota.getX()<=0 || prota.getX()>=world_width-10 || prota.getY()<0 || prota.getY()>= world_height-10){
            auras.clear();
            if (!conectamos){
                sumarPuntos(100);
            }
            conectamos=true;
        }
        else {
            tiempo*=1;
            conectamos=false;
        }





        //dibujar
        batch.begin();

        pon_aura_en_matriz();


        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j <mapa[i].length; j++) {
                if (mapa[i][j]==1){
                    batch.draw(pared,i* pixeles,j* pixeles);
                } else if (mapa[i][j]==2) {
                    batch.draw(prueba,i*pixeles,j*pixeles);
                } else {
                    batch.draw(piso,i* pixeles,j* pixeles);
                }
            }
        }


        prota.dibujar(batch);

        for (int i=0;i<enemigos.size-1;i++){
            enemigos.get(i).dibujar(batch);

            enemigos.get(i).movimiento_enemigo();

            if (enemigos.get(i).hitbox.overlaps(prota.hitbox)) {
                prota.forma.setPosition(-20,150);
                auras.clear();
                prota.setVidas(prota.getVidas()-1);
            }

        }



        for (int i=0;i<auras.size-1;i++){
            auras.get(i).draw(batch);
            hitBXauras.get(i).setPosition(auras.get(i).getX(),auras.get(i).getY());
            for (int j = 0; j <enemigos.size-1 ; j++) {
                if (enemigos.get(j).hitbox.overlaps(hitBXauras.get(i))) {
                    prota.forma.setPosition(-20,150);
                    auras.clear();
                    prota.setVidas(prota.getVidas()-1);
                }
            }
        }

        fontim.draw(batch, "Puntos: " + puntos, 20, Gdx.graphics.getHeight() - 20);
        fontpunt.draw(batch,"Tiempo: "+temporizador,350,Gdx.graphics.getHeight() - 20);
        vidas.draw(batch,"Vidas: "+prota.getVidas(),550,Gdx.graphics.getHeight() - 20);

        batch.end();

    }



    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        piso.dispose();
        pared.dispose();
        prueba.dispose();


    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}