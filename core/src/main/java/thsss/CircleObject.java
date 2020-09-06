package thsss;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

//CircleObject means Objects who's collision boxes are a circle.
public class CircleObject extends Actor {
    protected Thsss thsss;
    protected Point initPosition, nowPosition, lastPosition, temp;
    protected Sprite appearance;
    protected TextureRegion image;
    protected double radius;
    public MoveFunction moveFunction;
    protected double existTime;
    protected double angle, tempAngle;
    protected  boolean damage;
    public CircleObject(Thsss thsss) {
        this.thsss = thsss;
    }
    public CircleObject(Point point, TextureRegion img, Thsss thsss) {
        this.thsss = thsss;
        this.initPosition = point;
        this.nowPosition = this.initPosition;
        this.image = new TextureRegion(img);
        appearance = new Sprite(this.image);
        damage = true;
    }
    protected void move() {
        lastPosition = nowPosition;
        nowPosition = moveFunction.getPosition(existTime);
        //nowPosition.x = (int)(nowPosition.x);
       // nowPosition.y = (int)(nowPosition.y);

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
            return nowPosition.getdis(thsss.gameScreen.gameStage.character.getPosition()) >
                    radius + thsss.gameScreen.gameStage.character.getRadius();
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
        checkHit();
        checkDispose();
    }

    @Override
    public void draw(Batch batch, float delta) {
        if(nowPosition.x < 35 || nowPosition.y < 15 || nowPosition.x > 420 || nowPosition.y > 465 ){
            return;
        }
        appearance.setPosition((float)nowPosition.x, (float)nowPosition.y);
        appearance.setRotation((float)angle);
        appearance.draw(batch);
    }
    @Override
    public float getX(){
        return (float) nowPosition.x;
    }

    public float getY(){
        return (float) nowPosition.y;
    }
}
