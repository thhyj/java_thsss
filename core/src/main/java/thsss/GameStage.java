package thsss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.PooledLinkedList;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import thsss.MoveMethod.LineMove;
import thsss.actors.InterFace;
import thsss.bullets.RiceBullet;
import thsss.enemys.Boss03;
import thsss.enemys.Enemy;

import java.util.ArrayList;

public class GameStage extends Stage {
    private Thsss thsss;
    public Character character;
    public InterFace interFace;
    public Boss03 boss03;
    public static final int mainStageWidth = 385;
    public static final int mainStageHeight = 450;
    public static final int mainStageX = 35;
    public static final int MainStageY = 15;
    private Music bgm;
    public Array<Actor> actorArray;
    public Array<Enemy> enemyArray;
    public GameStage(Thsss thsss){
        //super(new FillViewport(385,450));
        this.thsss = thsss;

        init();
    }
    private void createBullets() {
        for(int i = 1; i <= 100; ++i) {
            RiceBullet bulletTest = (RiceBullet) new RiceBullet(new Point(200, 400),
                    new TextureRegion((Texture) thsss.manager.get("Image/Bullet/bullet-8.png"), 0, 17, 16, 16),
                    thsss
            );
          //  bulletTest.radius = 3;
            bulletTest.setHeight(16);
            bulletTest.setWidth(16);
        //    System.out.println(bulletTest.getHeight());
        //    System.out.println(bulletTest.getHeight());
            bulletTest.moveFunction = new LineMove((Math.random() - 0.5) * 100, Math.min((Math.random() - 0.5) * 100 - 50, -20), bulletTest.initPosition);
         //   bulletTest.moveFunction = new LineMove(-0,-0.1,bulletTest.initPosition);
            addActor(bulletTest);
            actorArray.add(bulletTest);
        }

    }
    private void init() {
        bgm = thsss.manager.get("BGM/Boss03.wav");
        bgm.play();
        //Gdx.gl.glClearColor(1,1,1,1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        actorArray = new Array<Actor>();
        character = new Character(thsss);
        interFace = new InterFace(thsss);
        boss03 = new Boss03(thsss, new Point(200, 300));
        enemyArray = new Array<Enemy>();
        addActor(character);
        addActor(boss03);
        addActor(interFace);
        interFace.setZIndex(100);
        character.setZIndex(1);
        boss03.setZIndex(0);
        enemyArray.add(boss03);

        createBullets();
        for(Actor a:actorArray) {

            addActor(a);
            a.setZIndex(20);
       //     actorArray.removeValue(a, true);
        }
    }
}
