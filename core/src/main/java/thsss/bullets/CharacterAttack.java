package thsss.bullets;

import com.badlogic.gdx.graphics.g2d.Batch;
import thsss.CircleObject;
import thsss.Point;
import thsss.Thsss;
import thsss.effects.HitEffect;
import thsss.effects.MyParticleEffect;
import thsss.enemys.Enemy;

public class CharacterAttack extends CircleObject {


    public CharacterAttack(Thsss thsss, Point point) {
        super(thsss, point);
    }

    @Override
    protected boolean checkHit() {
        for(Enemy a: thsss.gameScreen.gameStage.enemyLinkedList) {
            double dis = checkPointPosition.getdis(new Point(a.getCheckPosition()));
            if(dis <= radius + a.radius) {
                a.hp -= getHitDamage();
                return true;
            }
        }
        return false;
    }

    protected void hit() {
        thsss.gameScreen.gameStage.addActor(new MyParticleEffect(thsss, new HitEffect(thsss), 0.25f,
                (float) nowPosition.x,
                (float) nowPosition.y));
    }
    @Override
    public void act(float delta) {
        existTime += delta;
        move();
        getAngle();
        if(checkHit()) {
            hit();
            if(hasParent())
                getParent().removeActor(this);
        }
        //thsss.gameScreen.hit |= checkHit() && this.remove();

        checkDispose();
        updateCheckPointPosition();
        appearance.setPosition((float) nowPosition.x -(40 -  getWidth()), (float)nowPosition.y);
    }

    @Override
    public void draw(Batch batch, float delta) {
        appearance.draw(batch);
    }
}
