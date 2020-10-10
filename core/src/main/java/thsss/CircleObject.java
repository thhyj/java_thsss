package thsss;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import thsss.effects.BulletEndEffect;
import thsss.effects.GrazeEffect;
import thsss.effects.MyParticleEffect;

import java.util.SortedSet;

//CircleObject means Objects who's collision boxes are a circle.
public class CircleObject extends Actor {
    public Thsss thsss;
    public Point initPosition, nowPosition, lastPosition,checkPointPosition, temp;
    protected Texture point;
    protected Sprite appearance;
    protected TextureRegion image;
    public double radius;
    public MoveFunction moveFunction;
    protected double existTime;
    protected double angle, tempAngle;
    protected  boolean damage = true;
    protected boolean destructible = true;
    private boolean grazed = false;
    private Sound grazeSound;
    protected MyParticleEffect grazeEffect;
    protected boolean isJustGrazed = true;
    protected float lastGrazedTime = 0;
    protected boolean breakableByBomb = true;
    public boolean removed = false;
    protected int hitDamage;

    public int getHitDamage() {
        return  (int)(hitDamage * thsss.gameScreen.gameStage.damageRatio);
    }

    private void init() {
     //   appearance.setColor(0f, 1.0f, 0f, 0.8f);
    }

    public CircleObject(Thsss thsss, Point point) {
        this.point = thsss.manager.get("Image/point.png", Texture.class);

        this.thsss = thsss;
        this.initPosition = new Point(point);
        this.nowPosition = new Point(this.initPosition);
        this.checkPointPosition = new Point(this.nowPosition);
        damage = true;
        grazeSound = thsss.manager.get("Sound/se_graze.wav");
        appearance = new Sprite();

        //  init();
    }
    public CircleObject(Point point, TextureRegion img, Thsss thsss) {
        this.point = thsss.manager.get("Image/point.png", Texture.class);
        this.thsss = thsss;
        this.initPosition = point;
        this.nowPosition = new Point(this.initPosition);
        this.checkPointPosition = new Point(this.nowPosition);
        updateCheckPointPosition();
        this.image = new TextureRegion(img);
        appearance = new Sprite(this.image);
        damage = true;
        grazeSound = thsss.manager.get("Sound/se_graze.wav");
        init();

    }
    protected void move() {
        lastPosition = nowPosition;
        nowPosition = moveFunction.getPosition(existTime);
        //nowPosition.x = (int)(nowPosition.x);
       // nowPosition.y = (int)(nowPosition.y);

    }

    protected void updateCheckPointPosition() {
        checkPointPosition.x = nowPosition.x + getWidth() / 2;

        checkPointPosition.y = nowPosition.y + getHeight() / 2;

    }

    protected void getAngle() {
        temp = nowPosition.minus(initPosition);
       // temp.y = Math.round(nowPosition.y) - Math.round(nowPosition.y);
       // temp.x = Math.round(nowPosition.x) - Math.round(nowPosition.x);
        tempAngle = Math.atan2(temp.y, temp.x);
        if(tempAngle < 0) tempAngle += 2*Math.PI;
        tempAngle = tempAngle / Math.PI * 180 - 90;
        //if(Math.abs(tempAngle - angle) > 5) {
            angle = tempAngle;
      //  }
    }
    protected boolean checkHit() {
        if(damage) {
            //System.out.println(checkPointPosition.getdis(thsss.gameScreen.gameStage.character.getCheckPosition()));
           // System.out.println(radius + thsss.gameScreen.gameStage.character.getRadius());
            double dis = checkPointPosition.getdis(thsss.gameScreen.gameStage.character.getCheckPosition());
            if((!grazed) &&dis < 15) {
                grazed = true;

                grazeSound.play(0.3f);
                thsss.gameScreen.gameStage.addActor(new MyParticleEffect(thsss,new GrazeEffect(thsss), 1.0f,
                        thsss.gameScreen.gameStage.character.getX()+16.0f,
                        thsss.gameScreen.gameStage.character.getY()+16.0f));
                thsss.score += 100;
            }
            if(checkPointPosition.getdis(thsss.gameScreen.gameStage.character.getCheckPosition())<
                    radius + thsss.gameScreen.gameStage.character.getRadius()){
                return true;
            }

        }
        return false;
    }

    public void endBullet() {
      //  System.out.println(thsss.gameScreen.gameStage);
     //   System.out.println(appearance);
        thsss.gameScreen.gameStage.addActor(new BulletEndEffect(thsss, (float)checkPointPosition.x,
                (float) checkPointPosition.y, appearance.getColor()));

    }

    public boolean dispose() {
        if(this.hasParent()) {
            endBullet();
            this.getParent().removeActor(this);

        }
        return true;
    }

    public void checkDispose() {
        if(nowPosition.x < 0 || nowPosition.y < 0 || nowPosition.x > 500 || nowPosition.y > 500 ){
            if(this.hasParent()) this.getParent().removeActor(this);
        }
    }
    @Override
    public void act(float delta) {
        existTime += delta;
        move();
        getAngle();
        thsss.gameScreen.hit |= checkHit() && this.dispose();
        if(thsss.gameScreen.gameStage.bombing && breakableByBomb) {
            dispose();
        }
        checkDispose();
        updateCheckPointPosition();
    }

    @Override
    public void draw(Batch batch, float delta) {
       /* if(nowPosition.x < 35 || nowPosition.y < 15 || nowPosition.x > 420 || nowPosition.y > 465 ){
            return;
        }*/
        appearance.setPosition((float)nowPosition.x, (float)nowPosition.y);
        appearance.setRotation((float)angle);
        appearance.draw(batch);

        if(grazed) {
            lastGrazedTime += thsss.gameScreen.delta;
            if(lastGrazedTime > 1.0f) {
                lastGrazedTime = 0;
                grazed = false;
            }
        }
      /*  if(grazed && isJustGrazed) {
            lastGrazedTime += thsss.gameScreen.delta;
            if(lastGrazedTime > 1f)
                isJustGrazed = false;
            grazeEffect.setPosition();
            grazeEffect.draw(batch, thsss.gameScreen.delta);
        }*/
     //   batch.draw(point, (float)checkPointPosition.x, (float)checkPointPosition.y);
    }
    @Override
    public float getX(){
        return (float) nowPosition.x;
    }

    public float getY(){
        return (float) nowPosition.y;
    }
}
