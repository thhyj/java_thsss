package thsss.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import thsss.CircleObject;
import thsss.MoveFunction;
import thsss.MoveMethod.LineMove;
import thsss.Point;
import thsss.Thsss;
import thsss.enemys.Enemy;

public class CharacterAttack extends CircleObject {
    private int hitDamage;
    CharacterAttack(Point point, TextureRegion img, Thsss thsss) {
        super(point, img, thsss);
        init();
    }
    private void init() {
        this.setHeight(32);
        this.setWidth(32);
        radius = 16;
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
    }

    @Override
    public void draw(Batch batch, float delta) {
        super.draw(batch, delta);
    }
}
