package thsss.enemys;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import thsss.CircleObject;
import thsss.Point;
import thsss.Thsss;

public class Enemy extends CircleObject {
    public boolean ableToDamage;
    public int hp;
    public boolean spellCardEnd;
    public Enemy (Thsss thsss, Point point) {
        super(thsss, point);
     //   thsss.gameScreen.gameStage.enemyArray.add(this);
        breakableByBomb = false;
    }
    public Enemy (Point point, TextureRegion img, Thsss thsss) {
        super(point, img, thsss);
     //   thsss.gameScreen.gameStage.enemyArray.add(this);

        breakableByBomb = false;

    }
    public void defeated() {
        thsss.gameScreen.gameStage.enemyLinkedList.remove(this);
        remove();
    }
    public Point getCheckPosition() {
        return checkPointPosition;
    }
}
