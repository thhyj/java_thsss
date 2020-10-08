package thsss.enemys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import thsss.Point;
import thsss.SpellCard.SpellCard;
import thsss.Thsss;

public class Boss extends Enemy{
    private long lastChangeTime;
    protected int moveStatus, showStatus;
    static private final long changeTime = 200000000l;
    public  Array<SpellCard> spellCards;
    public int nowSpell;
    public Boss(Thsss thsss, Point point) {
        super(thsss, point);
        staying = new Array<TextureRegion>();
        movingRight = new Array<TextureRegion>();
        movingLeft = new Array<TextureRegion>();
        shooting = new Array<TextureRegion>();
        spellCards = new Array<SpellCard>();
    }
    Array<TextureRegion>staying, movingLeft, movingRight, shooting;

    private void stay(Batch batch) {
        if(TimeUtils.nanoTime() - lastChangeTime > changeTime) {
            ++showStatus;
            lastChangeTime = TimeUtils.nanoTime();
        }
        if(showStatus == staying.size) {
            showStatus = 0;
        }
        batch.draw(staying.get(showStatus), (float) nowPosition.x, (float) nowPosition.y);
    }
    private void moveLeft(Batch batch) {
        if(TimeUtils.nanoTime() - lastChangeTime > changeTime) {
            ++showStatus;
            lastChangeTime = TimeUtils.nanoTime();

        }
        if(showStatus == staying.size) {
            showStatus-=1;
        }
        batch.draw(movingLeft.get(showStatus), (float) nowPosition.x, (float) nowPosition.y);
    }
    private void moveRight(Batch batch) {
        if(TimeUtils.nanoTime() - lastChangeTime > changeTime) {
            ++showStatus;
            lastChangeTime = TimeUtils.nanoTime();

        }
        if(showStatus == staying.size) {
            showStatus-=1;
        }
        batch.draw(movingRight.get(showStatus), (float) nowPosition.x, (float) nowPosition.y);
    }
    private void shoot(Batch batch) {
        if(TimeUtils.nanoTime() - lastChangeTime > changeTime) {
            ++showStatus;
            lastChangeTime = TimeUtils.nanoTime();

        }
        if(showStatus == staying.size) {
            showStatus-=1;
        }
        batch.draw(shooting.get(showStatus), (float) nowPosition.x, (float) nowPosition.y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(hp <= 0) {
            spellCardEnd = true;
            spellCards.get(nowSpell).end();
            ++nowSpell;
            if(nowSpell >= spellCards.size) {
                this.defeated();
            } else {
                spellCards.get(nowSpell).begin();
            }
        }
    }

    @Override
    public void draw(Batch batch, float delta) {
        switch (moveStatus) {
            case 0:
                stay(batch);
                break;
            case 1:
                moveLeft(batch);
                break;
            case 2:
                moveRight(batch);
                break;
            case 3:
                shoot(batch);
                break;
        }

    }
}
