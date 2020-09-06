package thsss;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class GameInputListener extends InputListener {
    private Thsss thsss;
    public GameInputListener(Thsss thsss) {
        this.thsss = thsss;
    }
    public boolean keyDown(InputEvent event, int keycodes) {
        switch (keycodes) {
            case Input.Keys.UP:
                System.out.println("UP is typed");
                thsss.moveStatus = 1;
                break;
            case Input.Keys.RIGHT:
                System.out.println("Right is typed");
                thsss.moveStatus = 2;
                break;
            case Input.Keys.DOWN:
                System.out.println("Down is typed");
                thsss.moveStatus = 3;
                break;
            case Input.Keys.LEFT:
                System.out.println("Left is typed");
                thsss.moveStatus = 4;
                break;
            default:
                thsss.moveStatus = 0;
                return false;
        }
        return true;
    }
}
