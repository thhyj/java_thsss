package thsss;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.sql.Time;


public class Character extends Actor {

    //private Array<TextureRegion>[] Sanae;
    private Thsss thsss;
    private TextureRegion[][] Sanae;
    private Texture checkPoint;
    private Texture Sa;
    private int moveStatus;
    private long lastSanaeTime;
    private int preSanaeType;
    private int sanaeType;
    private int sanaeTot;
    private int totFrame;
    private static final int sqrt270 = 191;
    private static final int lowSqrt270 = 85;
    private double characterX, characterY, radius;
    public double checkPointX, checkPointY;
    public boolean unbreakable = false;
    public long lastHitTime;
    public boolean controlling = true;
    public boolean reborning = false;
    public long lastLostControlTime;
    public boolean visible = true;
    public int power = 100;
    public int powerStatus = 1;
    private boolean isInit = false;

    public Array<SubPlane> subPlanes;
    public Character(Thsss thsss) {
        this.thsss = thsss;
        characterX= 40;
        characterY = 20;
        this.setX(40);
        this.setY(20);
       // this.init();
    }
    private int getMoveStatus() {
        return thsss.moveStatus;
    }
    private void init() {
        radius = 3;
        checkPoint = thsss.manager.get("Image/MyPlane/Center.png", Texture.class);
        Sa = thsss.manager.get("Image/MyPlane/Sanae.png", Texture.class);
        Sanae = new TextureRegion[3][8];
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 8; ++j) {
                Sanae[i][j] = new TextureRegion(Sa, 32 * j, 48 * i,32, 48);
            }
        }
        power = 100;
        subPlanes = new Array<SubPlane>();
        createSubPlane(1);
    }

    private void createSubPlane(int type) {
        SubPlane temp = new SubPlane(thsss, type);

        subPlanes.add(temp);
        thsss.gameScreen.gameStage.addActor(temp);

        temp.setZIndex(2);
    }

    /*private void updateSubPlane(float delta) {
        for(SubPlane a:subPlanes) {
            a.updatePosition(delta);
        }
    }*/

    public void reborn() {
        unbreakable = true;
        lastHitTime = TimeUtils.nanoTime();
        controlling = false;
        reborning = true;
        characterX = 35 + (420 - 35) / 2 - 16;
        characterY = -50;
        this.setY((float) characterY);
        this.setX((float) characterX);
        updateCheckPoint();

    }

    private void updateCheckPoint() {
        checkPointX = characterX + 16;
        checkPointY = characterY + 24;
    }



    @Override
    public void act(float delta){
        if(!isInit) {
            isInit = true;
            init();
        }
        int speed = thsss.lowSpeed ? 120:270;
        int sqrtSpeed = thsss.lowSpeed?lowSqrt270:sqrt270;
        ++totFrame;
 //       System.out.println(sqrtSpeed);
        if(controlling) {
            switch (getMoveStatus()) {
                case 1:
                    characterY += speed * delta;
                    this.setY((float) characterY);
                    sanaeType = 0;
                    break;
                case 2:
                    characterX += speed * delta;
                    this.setX((float) characterX);
                    sanaeType = 2;
                    break;
                case 3:
                    characterY -= speed * delta;
                    this.setY((float) characterY);
                    sanaeType = 0;
                    break;
                case 4:
                    characterX -= speed * delta;
                    this.setX((float) characterX);
                    sanaeType = 1;
                    break;
                case 5:
                    characterY += sqrtSpeed * delta;
                    characterX += sqrtSpeed * delta;
                    this.setY((float) characterY);
                    this.setX((float) characterX);
                    sanaeType = 2;
                    break;
                case 6:
                    characterY -= sqrtSpeed * delta;
                    characterX += sqrtSpeed * delta;
                    this.setY((float) characterY);
                    this.setX((float) characterX);
                    sanaeType = 2;
                    break;
                case 7:
                    characterY -= sqrtSpeed * delta;
                    characterX -= sqrtSpeed * delta;
                    this.setY((float) characterY);
                    this.setX((float) characterX);
                    sanaeType = 1;
                    break;
                case 8:
                    characterY += sqrtSpeed * delta;
                    characterX -= sqrtSpeed * delta;
                    this.setY((float) characterY);
                    this.setX((float) characterX);
                    sanaeType = 1;
                    break;
                case 0:
                    sanaeType = 0;
                    break;
            }
            if (characterX < 35) {
                characterX = 35;
            }
            if (characterX > 420 - 32) {
                characterX = 420 - 32;
            }
            if (characterY > 465 - 48) {
                characterY = 465 - 48;
            }
            if (characterY < 15) {
                characterY = 15;
            }
        } else {
            if(reborning) {
                characterY += 75 * delta;
                this.setY((float) characterY);
                if(TimeUtils.nanoTime() - lastHitTime > 1000000000) {
                    reborning = false;
                    controlling = true;
                }
              //  System.out.println(characterX);
              //  System.out.println(checkPointX);

            }
        }
        if(TimeUtils.nanoTime() - lastHitTime > 5000000000l) {
            unbreakable = false;
            visible = true;
        }
        if(unbreakable) {
            if(((TimeUtils.nanoTime() - lastHitTime) / 25000000) % 2 == 0) {
                visible = false;
            } else {
                visible = true;
            }
        }
        if(sanaeType != preSanaeType) {
            sanaeTot = 0;
        } else {
            if(TimeUtils.nanoTime() - lastSanaeTime > 100000000) {
                lastSanaeTime = TimeUtils.nanoTime();
                sanaeTot += 1;
                if(sanaeTot >= 8) {
                    if(sanaeType == 0) {
                        sanaeTot = 0;
                    } else {
                        sanaeTot = 3;
                    }
                }
                totFrame = 0;
            }
        }
        preSanaeType = sanaeType;
      //  System.out.println("acting");
        updateCheckPoint();

    }
    @Override
    public void draw(Batch batch, float delta) {
        if(visible){
            batch.draw(Sanae[sanaeType][sanaeTot], getX(), getY());
            if(thsss.lowSpeed) {
                batch.draw(checkPoint, (float) checkPointX - 8, (float) checkPointY - 8);
            }
        }
        // System.out.println("drawing");

    }
    public Point getPosition() {
        return new Point(characterX, characterY);
    }
    public Point getCheckPosition() {
        return new Point(checkPointX, checkPointY);
    }
    public double getRadius() {
        return radius;
    }

}
