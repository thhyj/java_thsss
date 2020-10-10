package thsss.effects;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import thsss.Thsss;

public class HitEffect extends ParticleEffect {
    Thsss thsss;

    public HitEffect(Thsss thsss) {
        super(thsss.manager.get("Image/Effect/hiteffect"));
        this.thsss = thsss;
    }
}
