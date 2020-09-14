package thsss;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Thsss extends Game {
    public AssetManager manager;
    private Texture Sa;
    public GameScreen gameScreen;
    //1:Up, 2:Right, 3:Down, 4:Left, 5:RightUp, 6:RightDownm, 7:LeftDown, 8:LeftUp;
    public int moveStatus;
    public boolean lowSpeed;

    @Override
    public void create() {
        manager = new AssetManager();
    //    Sa = new Texture();
   //     Sa = new Texture(Gdx.files.internal("Sanae.png"));

        manager.load("Image/MyPlane/Sanae.png", Texture.class);
        manager.load("Image/Bullet/bullet-8.png", Texture.class);
        manager.load("Sound/se_pldead00.wav", Sound.class);
        manager.load("Image/Interface/Interface2.png", Texture.class);
        manager.load("Image/Interface/Interface.png", Texture.class);
        manager.finishLoading();
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

}