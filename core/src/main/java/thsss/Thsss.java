package thsss;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Thsss extends Game {
    public AssetManager manager;
    private Texture Sa;
    public GameScreen gameScreen;
    //1:Up, 2:Right, 3:Down, 4:Left, 5:RightUp, 6:RightDownm, 7:LeftDown, 8:LeftUp;
    public int moveStatus;
    public boolean lowSpeed;
    public boolean fire;
    public long highestScore = 9919260817l;
    public long score = 1219260817l;
    public int borderType = 2;
    public float borderPercent = 0.7f;
    @Override
    public void create() {
        manager = new AssetManager();
    //    Sa = new Texture();
   //     Sa = new Texture(Gdx.files.internal("Sanae.png"));
        manager.load("Image/Effect/SmallStar", ParticleEffect.class);
        manager.load("Image/Effect/SmallStar.png", Texture.class);
        manager.load("Image/Effect/EndBullet.png", Texture.class);
        manager.load("Image/Effect/Graze/Graze", ParticleEffect.class);
        manager.load("Image/Boss/BossSeiryuu.png", Texture.class);
        manager.load("BGM/Boss03.wav", Music.class);
        manager.load("Sound/se_graze.wav", Sound.class);
        manager.load("Image/Font/Font_Num.png", Texture.class);
        manager.load("Font/Score.fnt", BitmapFont.class);
        manager.load("Image/point.png",Texture.class);
        manager.load("Image/MyPlane/Center.png", Texture.class);
        manager.load("Image/MyPlane/Sanae.png", Texture.class);
        manager.load("Image/Bullet/bullet-8.png", Texture.class);
        manager.load("Sound/se_pldead00.wav", Sound.class);
        manager.load("Image/Interface/Interface2.png", Texture.class);
        manager.load("Image/Interface/Interface.png", Texture.class);
        manager.finishLoading();
        gameScreen = new GameScreen(this);
        gameScreen.init();
        setScreen(gameScreen);
    }

}