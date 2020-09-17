package thsss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;
import thsss.bullets.CharacterAttack1;
import thsss.bullets.CharacterAttack2;
import thsss.bullets.CharacterAttack3;

import javax.swing.*;
import java.security.Key;
import java.sql.Time;

public class SubPlane extends Actor {
    public Sprite subPlaneImage;
    public Point position, highSpeedPosition, lowSpeedPosition;
    private Thsss thsss;
    private float scale;
    private long createTime;
    private int type;
    private boolean nowLowSpeedStatus;
    private boolean movingUp, movingDown;
    static private final float moveSpeed = 700f;
    static private final int distance = 16;
    private double lastY;
    private long lastCreateBulletTime;
    SubPlane(Thsss thsss, int type) {
        this.thsss = thsss;
        this.type = type;
        createTime = TimeUtils.nanoTime();
        position = new Point(0, 0);
        highSpeedPosition = new Point(0, 0);
        lowSpeedPosition = new Point(0, 0);
        switch (type) {
            case 1:
                //highSpeedPosition = new Point(thsss.gameScreen.gameStage.character.getCheckPosition());
                highSpeedPosition.x = thsss.gameScreen.gameStage.character.checkPointX;
                highSpeedPosition.y = thsss.gameScreen.gameStage.character.checkPointY;

                lowSpeedPosition = new Point(highSpeedPosition);
                highSpeedPosition.y -= distance;
                lowSpeedPosition.y += distance;
                if(thsss.lowSpeed == true) {
                    position = lowSpeedPosition;
                } else{
                    position = highSpeedPosition;
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
        this.nowLowSpeedStatus = thsss.lowSpeed;
        subPlaneImage = new Sprite((Texture) thsss.manager.get("Image/MyPlane/Sanae.png"), 64, 144,17,17);
    }
    public void updatePosition(float delta) {
        switch (type) {
            case 1:
                lastY = highSpeedPosition.y;
                highSpeedPosition = thsss.gameScreen.gameStage.character.getCheckPosition();
                lowSpeedPosition = new Point (highSpeedPosition);
                highSpeedPosition.y -= distance + 23;
                lowSpeedPosition.y += distance + 7;
                position.x = highSpeedPosition.x - 7;
                position.y += (highSpeedPosition.y - lastY);
             //   System.out.println(highSpeedPosition.y - lastY);
                if(movingDown) {
                    position.y -= moveSpeed * delta;
                    if(position.y <= highSpeedPosition.y) {
                        position.y = highSpeedPosition.y;
                        movingDown = false;
                    }
                } else {
                    if(movingUp) {
                        position.y += moveSpeed * delta;
                        if(position.y >= lowSpeedPosition.y) {
                            position.y = lowSpeedPosition.y;
                            movingUp = false;
                        }
                    }
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }

    }
    private void getScale() {
        long tempTime = (TimeUtils.nanoTime() - createTime) % 500000000;
        if(tempTime < 250000000) {
            if (tempTime < 125000000) {
                scale = (float)(1f - 0.2 * tempTime / 250000000);
            } else {
                tempTime = 250000000 - tempTime;
                scale = (float) (1f - 0.2 * tempTime / 250000000);
            }
        } else {
            tempTime -= 250000000;
            if (tempTime < 125000000) {
                scale = (float)(1f + 0.2 * tempTime / 250000000);
            } else {
                tempTime = 250000000 - tempTime;
                scale = (float) (1f + 0.2 * tempTime / 250000000);
            }
        }

    }

    private void createSubBullet() {
        if(Gdx.input.isKeyPressed(54)&&TimeUtils.nanoTime() - lastCreateBulletTime> 70000000) {
            //System.out.println("created!");
            lastCreateBulletTime = TimeUtils.nanoTime();
            CharacterAttack1 attack1Left = new CharacterAttack1(thsss,
                    new Point(position.x - 7, position.y), this);
            CharacterAttack1 attack1Right = new CharacterAttack1(thsss,
                    new Point(position.x + 7, position.y), this);
            thsss.gameScreen.gameStage.addActor(attack1Left);
            thsss.gameScreen.gameStage.addActor(attack1Right);
            attack1Left.setZIndex(4);
            attack1Right.setZIndex(4);
            if(thsss.lowSpeed == false) {
            //    System.out.println("created!");
                CharacterAttack2 attack2Left = new CharacterAttack2(thsss,
                        new Point(position), this, 0);
                CharacterAttack2 attack2Mid = new CharacterAttack2(thsss,
                        new Point(position), this, 1);
                CharacterAttack2 attack2Right = new CharacterAttack2(thsss,
                        new Point(position), this, 2);
                thsss.gameScreen.gameStage.addActor(attack2Left);
                thsss.gameScreen.gameStage.addActor(attack2Mid);
                thsss.gameScreen.gameStage.addActor(attack2Right);
                attack2Left.setZIndex(4);
                attack2Mid.setZIndex(4);
                attack2Right.setZIndex(4);

            } else {
                if(thsss.lowSpeed == true) {
                    CharacterAttack3 attack3Left = new CharacterAttack3(thsss,
                            new Point(position.x - 4, position.y), this, 0);
                    CharacterAttack3 attack3Right = new CharacterAttack3(thsss,
                            new Point(position.x + 4, position.y), this, 0);
                    thsss.gameScreen.gameStage.addActor(attack3Left);
                    thsss.gameScreen.gameStage.addActor(attack3Right);
                    attack3Left.setZIndex(4);
                    attack3Right.setZIndex(4);
                }
            }
        }
    }



    @Override
    public void act(float delta) {
        if(thsss.lowSpeed && !nowLowSpeedStatus) {
            nowLowSpeedStatus = true;
            movingUp = true;
            movingDown = false;
        }
        if(!thsss.lowSpeed && nowLowSpeedStatus) {
            nowLowSpeedStatus = false;
            movingDown = true;
            movingUp = false;
        }
        updatePosition(delta);
        subPlaneImage.setPosition((float)position.x, (float)position.y);
        getScale();
        subPlaneImage.setScale(scale);
        createSubBullet();

    }

    @Override
    public void draw(Batch batch, float delta) {

        subPlaneImage.draw(batch);
    }
}

