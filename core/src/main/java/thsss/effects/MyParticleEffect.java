package thsss.effects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import thsss.Thsss;

public class MyParticleEffect extends Actor {
    ParticleEffect particleEffect;
    protected Thsss thsss;
    public float lifeTime, maxLife;
    public Sprite sprite;
    public MyParticleEffect(Thsss thsss, ParticleEffect particleEffect,float maxLife, float x, float y) {
        this.particleEffect = particleEffect;
        this.thsss = thsss;
        this.particleEffect.setPosition(x, y);
        this.maxLife = maxLife;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        lifeTime += thsss.gameScreen.delta;
        if(lifeTime > maxLife) {
            remove();
        } else {
            this.particleEffect.draw(batch, thsss.gameScreen.delta);
        }
    }
}
