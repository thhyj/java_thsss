package thsss;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

//Enemy means little mobs in every stage.
public class Enemy extends CircleObject {
    protected int hp;
    protected long lastChageTime;
    protected int imageTot;
    Array<TextureRegion>imageArray;
    public Enemy(Thsss thsss) {
        super(thsss);
    }
    public Enemy(Point point, Array<TextureRegion> img, int hp, Thsss thsss) {
        super(thsss);
        this.initPosition = point;
        this.nowPosition = this.initPosition;
        this.imageArray = new Array<TextureRegion>(img);
        appearance = new Sprite(this.image);
        damage = true;
        this.hp = hp;
    }
    @Override
    public void draw(Batch batch,float delta) {
        if(TimeUtils.nanoTime() - lastChageTime > 100000000) {
            lastChageTime = TimeUtils.nanoTime();
            ++imageTot;
            if(imageTot >= imageArray.size) {
                imageTot = 0;
            }
        }
        appearance.setRegion(imageArray.get(imageTot));
    }
}
