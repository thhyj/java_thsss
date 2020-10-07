package thsss.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import thsss.Thsss;

abstract public class Item extends Actor {
    protected Sprite sprite;
    protected Thsss thsss;
    protected float x, y;
    public Item(Thsss thsss, float x, float y){
        this.thsss = thsss;
        this.x = x;
        this.y = y;
    }
}
