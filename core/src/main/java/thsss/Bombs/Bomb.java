package thsss.Bombs;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import thsss.Thsss;

public class Bomb extends Actor {
    protected Thsss thsss;
    protected float existTime;
    protected Sound spellSound;

    public Bomb(Thsss thsss) {
   //    System.out.println("gan");
        this.thsss = thsss;
        existTime = 0;
        thsss.gameScreen.gameStage.bombing = true;
        spellSound = thsss.manager.get("Sound/se_spell.wav");
        spellSound.play();
        thsss.gameScreen.gameStage.damageRatio *= 2.0f;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        existTime += delta;
        if(existTime > 5.0f) {
            dispose();
        }
    }

    public void dispose() {
        thsss.gameScreen.gameStage.damageRatio /= 2.0f;
        thsss.gameScreen.gameStage.bombing = false;
        remove();
    }
}
