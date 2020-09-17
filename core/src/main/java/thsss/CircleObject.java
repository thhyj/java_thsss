package thsss;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

//CircleObject means Objects who's collision boxes are a circle.
public class CircleObject extends Actor {
    protected Thsss thsss;
    protected Point initPosition, nowPosition, lastPosition,checkPointPosition, temp;
   // private Texture point;
    protected Sprite appearance;
    protected TextureRegion image;
    public double radius;
    public MoveFunction moveFunction;
    protected double existTime;
    protected double angle, tempAngle;
    protected  boolean damage = true;
    private boolean grazed = false;
    private Sound grazeSound;

    public CircleObject(Thsss thsss, Point point) {
        this.thsss = thsss;
        this.initPosition = new Point(point);
        this.nowPosition = new Point(this.initPosition);
        this.checkPointPosition = new Point(this.nowPosition);
        damage = true;
        grazeSound = thsss.manager.get("Sound/se_graze.wav");
    }
    public CircleObject(Point point, TextureRegion img, Thsss thsss) {
     //   this.point = thsss.manager.get("Image/point.png", Texture.class);
        this.thsss = thsss;
        this.initPosition = point;
        this.nowPosition = new Point(this.initPosition);
        this.checkPointPosition = new Point(this.nowPosition);
        updateCheckPointPosition();
        this.image = new TextureRegion(img);
        appearance = new Sprite(this.image);
        damage = true;
        grazeSound = thsss.manager.get("Sound/se_graze.wav");
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
            }
            if(checkPointPosition.getdis(thsss.gameScreen.gameStage.character.getCheckPosition())<
                    radius + thsss.gameScreen.gameStage.character.getRadius()){
                return true;
            }

        }
        return false;
    }
    public void checkDispose() {
        if(nowPosition.x < 0 || nowPosition.y < 0 || nowPosition.x > 500 || nowPosition.y > 500 ){
            this.remove();
        }
    }
    @Override
    public void act(float delta) {
        existTime += delta;
        move();
        getAngle();
        thsss.gameScreen.hit |= checkHit() && this.remove();

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
