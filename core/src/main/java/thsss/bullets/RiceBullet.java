package thsss.bullets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import thsss.CircleObject;
import thsss.Point;
import thsss.Thsss;

public class RiceBullet extends CircleObject {
    public RiceBullet (Thsss thsss, Point point) {
        super(thsss, point);
    }
    public RiceBullet(Point point, TextureRegion img, Thsss thsss) {
        super(point, img, thsss);
        radius = 2.4;
    }
}
