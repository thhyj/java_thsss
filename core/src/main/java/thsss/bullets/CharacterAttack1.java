package thsss.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import thsss.*;
import thsss.MoveMethod.LineMove;
import thsss.enemys.Enemy;

public class CharacterAttack1 extends CharacterAttack {

    private SubPlane subPlane;
    public CharacterAttack1(Thsss thsss, Point point, SubPlane subPlane) {
        super(thsss, point);
        this.subPlane = subPlane;
        image = new TextureRegion((Texture) thsss.manager.get("Image/MyPlane/Sanae.png"), 0, 184, 64, 16);
        appearance = new Sprite(image);
        init();
    }
    private void init() {
       // appearance.rotate90(false);
        appearance.setRotation(90.0f);
        appearance.setAlpha(0.6f);
        this.setHeight(64);
        this.setWidth(16);
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
    }
}
