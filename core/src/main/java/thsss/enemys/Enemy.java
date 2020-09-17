package thsss.enemys;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import thsss.CircleObject;
import thsss.Point;
import thsss.Thsss;

public class Enemy extends CircleObject {
    public boolean ableToDamage;
    public int hp;
    public Enemy (Thsss thsss, Point point) {
        super(thsss, point);
    }
    public Enemy (Point point, TextureRegion img, Thsss thsss) {
        super(point, img, thsss);

    }
    public Point getCheckPosition() {
        return checkPointPosition;
    }
}
