package thsss.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import thsss.Thsss;
import thsss.item.BulletBody;

public class BulletEndEffect extends Actor {
    private Texture endBullet;
    private Array<TextureRegion> endBullets;
    private Sprite sprite;
    private Thsss thsss;
    private int status;
    private float existTime;
    private float x, y;
    private Color color;
    public BulletEndEffect(Thsss thsss, float x, float y, Color color) {
        this.thsss = thsss;
        endBullet = thsss.manager.get("Image/Effect/EndBullet.png");
        this.x = x;
        this.y = y;
        endBullets = new Array<TextureRegion>();
        for(int i = 0 ;i < 8;++i) {
            endBullets.add(new TextureRegion(endBullet, i * 32, 0, 32, 32));
        }
        sprite = new Sprite(endBullets.get(0));
        sprite.setPosition(x - 16, y - 16);
        sprite.setColor(color);
        sprite.setAlpha(0.8f);
        this.color = color;
    }

    private void endEffect() {
        thsss.gameScreen.gameStage.addActor(new BulletBody(thsss, x, y));
        for(int i = 1;i  <= 6; ++i)
        thsss.gameScreen.gameStage.addActor(new BulletBody(thsss, x + (float) ((Math.random() - 0.5) * 20),
                y + (float) ((Math.random() - 0.5) * 20)));
        if(hasParent())
        getParent().removeActor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        existTime += delta;
        while (existTime > 0.05) {
            existTime -= 0.05;
            status+=1;
            if(status >= 8) {
                endEffect();
            }
        }
        if(status >= 8) {
            endEffect();
            return;
        }
        sprite.setRegion(endBullets.get(status));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
       // sprite.setAlpha(0.5);

        sprite.draw(batch);
    }
}
