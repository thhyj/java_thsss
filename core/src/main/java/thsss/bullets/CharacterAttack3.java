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

public class CharacterAttack3 extends CharacterAttack {
    private SubPlane subPlane;

    //Left is 0, Mid is 1, Right is 2
    private int type;
    public CharacterAttack3(Thsss thsss, Point point, SubPlane subPlane, int type) {
        super(thsss, point);
        this.subPlane = subPlane;
        this.type = type;
        image = new TextureRegion((Texture) thsss.manager.get("Image/MyPlane/Sanae.png"),
                144, 144, 64, 11);

        appearance = new Sprite(image);

        init();
    }
    private void init() {
        // appearance.rotate90(false);
        appearance.setRotation(90.0f);
        appearance.setAlpha(0.6f);

        this.setHeight(64);
        this.setWidth(11);
        //this.moveFunction = new LineMove(0, 1500, initPosition);
        this.moveFunction = new LineMove(0, 1500, initPosition);


        radius = 32;
        hitDamage = 10;
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
        appearance.setPosition((float) nowPosition.x - 24, (float)nowPosition.y);
        appearance.setRotation((float) angle + 90.0f);
        //     System.out.println(appearance.getX() + appearance.getY());
    }
}
