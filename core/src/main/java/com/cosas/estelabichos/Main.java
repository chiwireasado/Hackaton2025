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

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {


    Array<Sprite> enemigos;
    SpriteBatch batch;
    Sprite cosa,fondo,enemigo;
    Rectangle hitBXprota,hitBXenemigo;

    Camera camara;
    static final int world_width= 550;
    static final int world_height= 400;



    public void movimiento_personaje(){
        float speed=20;
        float delta= Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            cosa.translateY(14);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            cosa.translateY(-14);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            cosa.translateX(-14);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            cosa.translateX(14);
        }
    }


    @Override
    public void create() {
        setScreen(new firstScreen());

        // multiples enemigos
        enemigos=new Array<>(5);

        //cofiguracionese del fondo
        fondo=new Sprite(new Texture(Gdx.files.internal("fondo.png")));
        fondo.setPosition(45,45);
        fondo.setSize(world_width,world_height);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camara= new OrthographicCamera(30, 30 * (h / w));

        //todo sobre el personaje principal
        cosa=new Sprite(new Texture("caracol.png"));
        cosa.setSize(100,100);
        cosa.setPosition(0,0);


        // todo sobre el enemigo
        enemigo=new Sprite(new Texture(Gdx.files.internal( "bicho.png")));
        enemigo.setSize(50,50);
        float ranX=MathUtils.random(0,world_width-enemigo.getWidth());
        float ranY=MathUtils.random(0,world_height-enemigo.getHeight());
        enemigo.setPosition(ranX,ranY);


        hitBXprota=new Rectangle();
        hitBXenemigo=new Rectangle();



        camara.update();
        batch=new SpriteBatch();
        movimiento_personaje();



    }

    @Override
    public void render() {
        super.render();
        camara.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        movimiento_personaje();

        hitBXprota.set(cosa.getX(),cosa.getY(),cosa.getWidth()/2,cosa.getHeight()/2);
        hitBXenemigo.set(enemigo.getX(),enemigo.getY(),enemigo.getWidth()/2,enemigo.getHeight()/2);


        if (hitBXprota.overlaps(hitBXenemigo)) {
            System.out.println("choco");
        }

        batch.begin();
        fondo.draw(batch);
        cosa.draw(batch);

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
