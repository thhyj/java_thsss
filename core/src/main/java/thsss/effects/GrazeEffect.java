package thsss.effects;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import thsss.Thsss;

public class GrazeEffect extends ParticleEffect {
    Thsss thsss;

    public GrazeEffect(Thsss thsss) {
        super(thsss.manager.get("Image/Effect/Graze/Graze"));
        this.thsss = thsss;

    }
}
