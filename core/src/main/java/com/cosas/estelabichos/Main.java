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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.rmi.dgc.DGC;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {


    Array<Sprite> enemigos;
    Array<Sprite> auras;


    SpriteBatch batch;
    Sprite prota,fondo,enemigo,aura;
    Rectangle hitBXprota,hitBXenemigo,hbxaura;


    static float ranX;
    static float ranY;
    Camera camara;
    static final int world_width= 600;
    static final int world_height= 440;

    public void movimiento_enemigo() {
        float delta = Gdx.graphics.getDeltaTime();
        enemigo.translate(ranX * delta, ranY * delta);
        enemigo.setX(MathUtils.clamp(enemigo.getX(),0,world_width-hitBXenemigo.getWidth()));
        enemigo.setY(MathUtils.clamp(enemigo.getY(),0,world_height-hitBXenemigo.getHeight()));

    }

    public void movimiento_personaje(){
        float speed=100;
        float delta= Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            prota.translateY(speed);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            prota.translateY(-speed);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            prota.translateX(-speed);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            prota.translateX(speed);
        }

        prota.setX(MathUtils.clamp(prota.getX(),0,world_width));
        prota.setY(MathUtils.clamp(prota.getY(),0,world_height));

        if (prota.getX()>=world_width){
            System.out.println("tocamos X");
        }
        if (prota.getY()>=world_height){
            System.out.println("tocamos Y");
        }

        if (prota.getY()<=(-world_height)){
            System.out.println("tocamos -Y");
        }

        if (prota.getX()<=(-world_width)){
            System.out.println("tocamos -X");
        }

    }



    @Override
    public void create() {
        setScreen(new firstScreen());

        // multiples enemigos
        enemigos=new Array<>(5);

        // cracion aura
        aura=new Sprite(new Texture(Gdx.files.internal("bicho.png")));
        hbxaura=new Rectangle();

        //cofiguracionese del fondo
        fondo=new Sprite(new Texture(Gdx.files.internal("fondo.png")));
        fondo.setPosition(20,20);
        fondo.setSize(world_width,world_height);

        // cosas aun no usadas
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
        ranX=MathUtils.random(0,fondo.getWidth()-enemigo.getWidth());
        ranY=MathUtils.random(0,fondo.getHeight()-enemigo.getHeight());
        enemigo.setPosition(ranX,ranY);


        //hitbox's iniciadas
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

        // movimientos
        movimiento_personaje();
        movimiento_enemigo();


        //hitbox's configuracion
        hitBXprota.set(prota.getX(), prota.getY(), prota.getWidth()/2, prota.getHeight()/2);
        hitBXenemigo.set(enemigo.getX(),enemigo.getY(),enemigo.getWidth()/2,enemigo.getHeight()/2);


        // colision
        if (hitBXprota.overlaps(hitBXenemigo)) {
            System.out.println("choco");
            //prota.setPosition((prota.getX()-prota.getWidth()/2),(prota.getY()-prota.getHeight()/2));
            prota.setPosition(-20,150);
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            System.out.println("Prota X: "+(prota.getX()-prota.getWidth())+"---Y: "+(prota.getY()-prota.getHeight()+""));
        }





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
        batch.dispose();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
