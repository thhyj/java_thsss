package thsss.enemys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import thsss.MoveMethod.LineMove;
import thsss.Point;
import thsss.Thsss;

public class Boss03 extends Boss{
    private Texture XiaQingliu;

    public Boss03(Thsss thsss, Point point) {
        super(thsss, point);
        init();
    }
    private void init() {
        XiaQingliu = thsss.manager.get("Image/Boss/BossSeiryuu.png");
        setWidth(64);
        setHeight(80);
        for(int i = 0; i < 4; ++i) {
            staying.add(new TextureRegion(XiaQingliu, 64*i,0,64,80));
        }
        for(int i = 0 ; i < 4; ++i) {
            movingLeft.add(new TextureRegion(XiaQingliu, 64 * i, 80, 64, 80));
            movingLeft.get(i).flip(true, false);
        }
        for(int i = 0; i < 4; ++i) {
            movingRight.add(new TextureRegion(movingLeft.get(i)));
            movingLeft.get(i).flip(true,false);
        }
        for (int i = 0; i < 4; ++i) {
            shooting.add(new TextureRegion(XiaQingliu, 64 * i, 160, 64, 80));
        }
        moveFunction = new LineMove(0 ,0, initPosition);
        hp = 1000;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(hp <= 0) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float delta) {
        super.draw(batch, delta);
        batch.draw(point, (float) checkPointPosition.x, (float)checkPointPosition.y);
     //   System.out.println(getWidth());
     //   System.out.println(getHeight());
     //   batch.draw(point, (float) nowPosition.x + getWidth() / 2, (float)nowPosition.y + getHeight()/ 2);
    }
}
