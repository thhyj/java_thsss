package thsss.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import thsss.CircleObject;
import thsss.MoveMethod.LineMove;
import thsss.Point;
import thsss.SubPlane;
import thsss.Thsss;
import thsss.enemys.Enemy;

public class CharacterAttack2 extends CircleObject {
    private int hitDamage;
    private SubPlane subPlane;

    //Left is 0, Mid is 1, Right is 2
    private int type;
    public CharacterAttack2(Thsss thsss, Point point, SubPlane subPlane, int type) {
        super(thsss, point);
        this.subPlane = subPlane;
        this.type = type;
        image = new TextureRegion((Texture) thsss.manager.get("Image/MyPlane/Sanae.png"),
                0, 144, 32, 32);

        appearance = new Sprite(image);

        init();
    }
    private void init() {
        // appearance.rotate90(false);
       // appearance.setRotation(90.0f);
        appearance.setAlpha(0.6f);

        this.setHeight(32);
        this.setWidth(32);
        //this.moveFunction = new LineMove(0, 1500, initPosition);
        switch (type) {
            case 0:
                this.moveFunction = new LineMove(-250, 1500, initPosition);
                break;
            case 1:
                this.moveFunction = new LineMove(0, 1500, initPosition);
                break;
            case 2:
                this.moveFunction = new LineMove(250, 1500, initPosition);
                break;
        }
        radius = 32;
        hitDamage = 10;
    }
    @Override
    protected boolean checkHit() {
        for(Enemy a: thsss.gameScreen.gameStage.enemyArray) {
            double dis = checkPointPosition.getdis(new Point(a.getCheckPosition()));
            if(dis <= radius + a.radius) {
                a.hp -= hitDamage;
                return true;
            }
        }
        return false;
    }

    private void hit() {

    }
    @Override
    public void act(float delta) {
        existTime += delta;
        move();
        getAngle();
        if(checkHit()) {
            hit();
            this.remove();
        }
        //thsss.gameScreen.hit |= checkHit() && this.remove();

        checkDispose();
        updateCheckPointPosition();
        appearance.setPosition((float) nowPosition.x - 8, (float)nowPosition.y);
        appearance.setRotation((float) angle);
   //     System.out.println(appearance.getX() + appearance.getY());
    }

    @Override
    public void draw(Batch batch, float delta) {
        appearance.draw(batch);
    }
}
