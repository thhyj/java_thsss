package thsss.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import thsss.Thsss;

public class InterFace extends Actor {
    private Texture interFace2, interFace1;
    private TextureRegion interFace;
    private TextureRegion highestScore;
    private TextureRegion Score;
    private TextureRegion lifeFont;
    private TextureRegion bombFont;
    private TextureRegion lifeImage;
    Array<TextureRegion> lifePartImage;
    private TextureRegion bombImage;
    Array<TextureRegion> bombPartImage;
    private TextureRegion borderFont;
    private TextureRegion borderImage0, borderImage1;
    private Thsss thsss;
    private int lifePart;
    private int bombPart;
    public InterFace(Thsss thsss) {
        this.thsss = thsss;
        init();
    }
    private void load() {
        interFace2 = thsss.manager.get("Image/Interface/Interface2.png");
        interFace1 = thsss.manager.get("Image/Interface/Interface.png");

        interFace = new TextureRegion(interFace2, 0, 0, 640, 480);
        highestScore = new TextureRegion(interFace2, 864,	40,	64,	24);
        Score = new TextureRegion(interFace2, 864, 64, 40, 24);
        lifeFont = new TextureRegion(interFace2, 864, 96, 72,24);
        bombFont = new TextureRegion(interFace2, 864, 136, 72,24);
        lifeImage = new TextureRegion(interFace1, 1, 31, 28, 28);
        bombImage = new TextureRegion(interFace1, 1, 1, 28, 28);
        lifePartImage = new Array<TextureRegion>();
        bombPartImage = new Array<TextureRegion>();
        for(int i =1; i <= 4; ++i) {
            lifePartImage.add(new TextureRegion(interFace1, 1 + 30 * i, 31, 28, 28));
            bombPartImage.add(new TextureRegion(interFace1, 1 + 30 * i, 1, 28, 28));
        }
        lifePartImage.add(new TextureRegion(interFace1, 1 + 30 * 5, 31, 28, 28));
        borderFont = new TextureRegion(interFace2, 865, 179, 64, 24);
        borderImage0 = new TextureRegion(interFace1, 171, 1,18,18);
        borderImage1 = new TextureRegion(interFace1, 151, 1,18,18);

    }
    private void init() {
        load();
    //    thsss.gameScreen.life = 3;
    //    thsss.gameScreen.bomb = 3;
        lifePart = 0;
        bombPart = 0;
    }
    @Override
    public void act(float delta) {
        if(lifePart == 4) {
            lifePart = 0;
            thsss.gameScreen.life += 1;
        }
        if(bombPart == 4) {
            bombPart = 0;
            thsss.gameScreen.bomb += 1;
        }
    }
    @Override
    public void draw(Batch batch, float delta) {
        batch.draw(interFace, 0, 0);
        batch.draw(highestScore, 425, 480 - 70);
        batch.draw(Score, 425, 480 - 95);
        batch.draw(lifeFont, 425, 480 - 125);
        batch.draw(bombFont,425, 480 - 150);
        batch.draw(borderFont, 425, 480 - 175);
        for(int i = 0;i  < 8; ++i) {
            batch.draw(lifePartImage.get(4), 425 + 72 + 15 * i, 480 - 118, 15, 15);
            batch.draw(bombPartImage.get(3), 425 + 72 + 15 * i, 480 - 143, 15, 15);
        }
        for(int i = 0; i < 8; ++i) {
            batch.draw(borderImage0, 425 + 72 + 15 * i, 480 - 168, 15, 15);
        }
        for(int i = 0; i < thsss.gameScreen.life; ++i) {
            batch.draw(lifeImage,425 + 72 + 15 * i, 480 - 118, 15, 15);
        }
        for(int i = 0; i < thsss.gameScreen.bomb; ++i) {
            batch.draw(bombImage, 425 + 72 + 15 * i, 480 - 143, 15, 15);
        }
        for(int i = 0; i < thsss.gameScreen.border; ++i) {
            batch.draw(borderImage1, 425 + 72 + 15 * i, 480 - 168, 15, 15);
        }
    }
}
