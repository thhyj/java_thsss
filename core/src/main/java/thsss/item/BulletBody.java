package thsss.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import thsss.Point;
import thsss.Thsss;

public class BulletBody extends Item{
    Texture texture;
    ParticleEffect particleEffect;
    float speed = 600, x1, y1, temp;
    public BulletBody(Thsss thsss, float x, float y) {
        super(thsss, x, y);
        texture = thsss.manager.get("Image/Effect/SmallStar.png");
        sprite = new Sprite(texture);
        sprite.setAlpha(0.6f);
     //   particleEffect = new ParticleEffect(thsss.manager.get("Image/Effect/SmallStar"));

    }

    private void get() {
        thsss.score += 1000;
        remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        x1 = (float) thsss.gameScreen.gameStage.character.getCheckPosition().x - x;
        y1 = (float) thsss.gameScreen.gameStage.character.getCheckPosition().y - y;
        temp = (float) Math.sqrt(x1 * x1 + y1 * y1);
        x1 /= temp;
        y1 /= temp;
        x += x1 * speed * delta;
        y += y1 * speed * delta;
        if(Point.getdis(new Point(x, y), new Point(thsss.gameScreen.gameStage.character.getCheckPosition().x,
                thsss.gameScreen.gameStage.character.getCheckPosition().y)) < 15) {
                get();
                return;
        }
        sprite.setPosition(x - 8, y - 8);
       //  particleEffect.setPosition(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
       // particleEffect.draw(batch, thsss.gameScreen.delta);
    }
}
