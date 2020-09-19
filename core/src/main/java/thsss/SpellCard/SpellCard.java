package thsss.SpellCard;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import thsss.CircleObject;
import thsss.Thsss;
import thsss.enemys.Enemy;

public class SpellCard extends Actor {
    public Enemy master;
    public Thsss thsss;
    Array<CircleObject> bullets;
    public int hp;
    public long startTime;
    public SpellCard(Enemy master, int hp) {
        this.master = master;
        this.hp = hp;
        thsss = master.thsss;
        bullets = new Array<CircleObject>();
    }
    public void dispose() {
        remove();
    }

    public void begin() {
        master.hp = hp;
        startTime = TimeUtils.nanoTime();
    }
    public void end() {
        for(CircleObject a: bullets) {
            a.dispose();
        }
    }
}
