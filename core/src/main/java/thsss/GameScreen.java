package thsss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.*;

import static java.lang.Thread.sleep;

public class GameScreen extends ScreenAdapter {
    private Thsss thsss;
    public GameStage gameStage;
    public SpriteBatch batch;
    private BitmapFont font;
    public int isKeyDowned;
    public int FPS;
    private int nowFPS;
    private long lastFPS;
    private GameInputListener gameInputListener;
    public GameScreen(Thsss thsss){
        this.thsss = thsss;
        init();
    }
    private void init() {
        gameStage = new GameStage(thsss);
        batch = new SpriteBatch();
     //   gameInputListener = new GameInputListener(thsss);
     //   gameStage.addListener(gameInputListener);
        Gdx.input.setInputProcessor(gameStage);
        font = new BitmapFont();
    }
    private void characterMove() {
        if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
            thsss.lowSpeed = true;
        } else {
            thsss.lowSpeed = false;
        }
        if(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
            thsss.moveStatus = 5;
        } else {
            if(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
                thsss.moveStatus = 6;
            } else {
                if(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.LEFT)) {
                    thsss.moveStatus = 7;
                } else {
                    if(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.LEFT)) {
                        thsss.moveStatus = 8;
                    } else {
                        if(Gdx.input.isKeyPressed(Keys.UP)) {
                            thsss.moveStatus = 1;
                        } else {
                            if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
                                thsss.moveStatus = 2;
                            } else{
                                if(Gdx.input.isKeyPressed(Keys.DOWN)) {
                                    thsss.moveStatus = 3;
                                } else {
                                    if(Gdx.input.isKeyPressed(Keys.LEFT)){
                                        thsss.moveStatus = 4;
                                    } else {
                                        thsss.moveStatus = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private long diff, start = System.currentTimeMillis();

    public void sleep(int fps) {
        if(fps>0){
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {}
            }
            start = System.currentTimeMillis();
        }
    }
    public boolean checkDelete(Actor a) {
        if(a.getX() < 0 || a.getX() > 500 || a.getY()< 0 || a.getY() > 500) {
            return true;
        }
        return false;
    }
    @Override
    public void render(float delta) {
        if(TimeUtils.nanoTime() - lastFPS >= 1000000000) {
            lastFPS = TimeUtils.nanoTime();
            FPS = nowFPS;
            nowFPS = 0;
        }
   //     Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        characterMove();
        ++nowFPS;
        batch.begin();
        gameStage.act();
        gameStage.draw();
        font.draw(batch, "FPS:"+FPS, 50, 50);
        batch.end();
      /*  for(Actor a: gameStage.actorArray){
            if(checkDelete(a)) {
                a.remove();
            }
        }*/
       sleep(60);
    }
}
