package thsss.SpellCard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import thsss.MoveMethod.LineMove;
import thsss.Point;
import thsss.bullets.RiceBullet;
import thsss.enemys.Enemy;

public class TestSpell extends SpellCard{
    public TestSpell(Enemy master, int hp) {
        super(master, hp);
        init();
    }

    @Override
    public void begin() {
        super.begin();
        for(int i = 1; i <= 100; ++i) {
            RiceBullet bulletTest = (RiceBullet) new RiceBullet(new Point(200, 400),
                    new TextureRegion((Texture) thsss.manager.get("Image/Bullet/bullet-8.png"), 0, 17, 16, 16),
                    thsss
            );
            //  bulletTest.radius = 3;
            bulletTest.setHeight(16);
            bulletTest.setWidth(16);
            //    System.out.println(bulletTest.getHeight());
            //    System.out.println(bulletTest.getHeight());
            bulletTest.moveFunction = new LineMove((Math.random() - 0.5) * 100,
                    Math.min((Math.random() - 0.5) * 100 - 50, -20), bulletTest.initPosition);
            //   bulletTest.moveFunction = new LineMove(-0,-0.1,bulletTest.initPosition);
            thsss.gameScreen.gameStage.addActor(bulletTest);
            bulletTest.setZIndex(3);
            bullets.add(bulletTest);
        }
    }

    private void init() {

    }
}
