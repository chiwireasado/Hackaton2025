package com.cosas.estelabichos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;

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
    Array<Rectangle> hitBXauras;



    Texture pared,piso,prueba;


    personaje prota;
    int posXActualprota;
    int posYActualprota;



    SpriteBatch batch;
    Sprite enemigo,aura;
    Rectangle hitBXenemigo;
    Rectangle hitBXaura;


    Camera camara;
    static float ranX;
    static float ranY;
    static final int world_width= 600;
    static final int world_height= 440;

    final int pixeles =32;




    public void movimiento_enemigo() {

        float delta = Gdx.graphics.getDeltaTime();
        if (mapa[(int) enemigo.getY()/pixeles][(int) enemigo.getX()/pixeles] != 0) {
            ranX = -ranX;
            ranY = -ranY;
        }
        enemigo.translate(ranX*delta, ranY* delta);

        enemigo.setX(MathUtils.clamp(enemigo.getX(),0,world_width-hitBXenemigo.getWidth()));
        enemigo.setY(MathUtils.clamp(enemigo.getY(),0,world_height-hitBXenemigo.getHeight()));
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
        for (Sprite aura : auras) {
            int pixelx = (int)(prota.getX() / pixeles);
            int pixely = (int)(prota.getY() / pixeles);

            if (pixely >= 0 && pixely < mapa.length && pixelx >= 0 && pixelx < mapa[0].length) {
                if (mapa[pixely][pixelx] != 1) {
                    mapa[pixely][pixelx] = 2;
                }
            }
        }
    }



    @Override
    public void create() {
        setScreen(new firstScreen());


        // multiples enemigos
        enemigos=new Array<>(5);
        auras=new Array<>();
        hitBXauras=new Array<>();


        // si se quita esto exzplota
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camara= new OrthographicCamera(30, 30 * (h / w));


        //todo sobre el personaje principal
        prota =new personaje(new Texture("caracol.png"));
        prota.setTamaño(pixeles,pixeles);


        // todo sobre el enemigo
        enemigo=new Sprite(new Texture(Gdx.files.internal( "bicho.png")));
        enemigo.setSize(50,50);
        ranX=MathUtils.random(0,world_width-enemigo.getWidth());
        ranY=MathUtils.random(0,world_height-enemigo.getHeight());
        enemigo.setPosition(ranX,ranY);


        //hitbox enemigo iniciadas
        hitBXenemigo=new Rectangle();


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

        posXActualprota = prota.getX();
        posYActualprota = prota.getY();



        // movimientos
        prota.moverse();
        movimiento_enemigo();




        //hitbox's configuracion
        prota.hitbox.set(prota.getX(), prota.getY(), prota.forma.getWidth()/2, prota.forma.getHeight()/2);
        hitBXenemigo.set(enemigo.getX(),enemigo.getY(),enemigo.getWidth()-15,enemigo.getHeight()-15);




        // colision
        if (prota.hitbox.overlaps(hitBXenemigo)) {
            System.out.println("choco");
            //prota.setPosition((prota.getX()-prota.getWidth()/2),(prota.getY()-prota.getHeight()/2));
            prota.forma.setPosition(-20,150);
        }


        crea_aura();


        if (prota.getX()<=0 || prota.getX()>=world_width-10){
            auras.clear();
        }
        if (prota.getY()<0 || prota.getY()>= world_height-10){
            auras.clear();
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
        enemigo.draw(batch);


        for (int i=0;i<auras.size-1;i++){
            auras.get(i).draw(batch);
            hitBXauras.get(i).setPosition(auras.get(i).getX(),auras.get(i).getY());

            if (hitBXenemigo.overlaps(hitBXauras.get(i))) {
                System.out.println("choco");
                //prota.setPosition((prota.getX()-prota.getWidth()/2),(prota.getY()-prota.getHeight()/2));
                prota.forma.setPosition(-20,150);
                auras.clear();
            }

        }


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