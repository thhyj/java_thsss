package thsss.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    Array<TextureRegion> InterfaceLine;
    private Thsss thsss;
    private int lifePart;
    private int bombPart;
    private BitmapFont bitmapFont;
    private Texture numberFont;
    //0~9 is the corresponding number img, and 10 is comma, 11 is period
    Array<TextureRegion>number;

    Array<Array<Sprite>>Border;
    Sprite activeBorder;

    public InterFace(Thsss thsss) {
        this.thsss = thsss;
        init();
    }
    private void load() {
        interFace2 = thsss.manager.get("Image/Interface/Interface2.png");
        interFace1 = thsss.manager.get("Image/Interface/Interface.png");
        bitmapFont = thsss.manager.get("Font/Score.fnt");
        numberFont = thsss.manager.get("Image/Font/Font_Num.png");

        bitmapFont.getData().setScale(0.65f, 0.5f);
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
        InterfaceLine = new Array<TextureRegion>();
        InterfaceLine.add(new TextureRegion(interFace2,820, 358, 152, 8));
        InterfaceLine.add(new TextureRegion(interFace2,820, 379, 152, 8));
        InterfaceLine.add(new TextureRegion(interFace2,820, 413, 152, 8));
        InterfaceLine.add(new TextureRegion(interFace2,820, 452, 152, 8));

        number = new Array<TextureRegion>();
        for(int i = 0; i <= 9 ;++i) {
            number.add(new TextureRegion(numberFont, 26 + 13 * i, 0, 13, 18));
        }
        number.add(new TextureRegion(numberFont, 0, 0, 13, 18));
        number.add(new TextureRegion(numberFont, 13, 0, 13, 18));

        Border = new Array<Array<Sprite>>();
        Border.add(new Array<Sprite>());
        Border.add(new Array<Sprite>());
        Border.add(new Array<Sprite>());

        //Green
        Border.get(0).add(new Sprite(interFace1, 0, 276, 66, 118));
        Border.get(0).add(new Sprite(interFace1, 66, 276, 66, 118));
        Border.get(0).add(new Sprite(interFace1, 132, 276, 66, 118));
        //Border.get(0).get(2).flip(true,true);
        //Blue
        Border.get(1).add(new Sprite(interFace1, 0, 394, 66, 118));
        Border.get(1).add(new Sprite(interFace1, 66, 394, 66, 118));
        Border.get(1).add(new Sprite(interFace1, 132, 394, 66, 118));
        //Border.get(1).get(2).flip(false,true);
        //Red
        Border.get(2).add(new Sprite(interFace1, 198, 394, 66, 118));
        Border.get(2).add(new Sprite(interFace1, 264, 394, 66, 118));
        Border.get(2).add(new Sprite(interFace1, 330, 394, 66, 118));
       // Border.get(2).get(2).flip(false,true);
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
        activeBorder = new Sprite(Border.get(thsss.borderType).get(2),
                0, 118 - Math.round(118 * thsss.borderPercent), 66, Math.round(118 * thsss.borderPercent));
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
        batch.draw(InterfaceLine.get(0), 490, 480 - 70);
        batch.draw(InterfaceLine.get(1), 490, 480 - 95);
        batch.draw(InterfaceLine.get(2), 490, 480 - 130);
        batch.draw(InterfaceLine.get(3), 490, 480 - 155);

        drawScore(batch);

        drawBorder(batch);
    }

    private void drawBorder(Batch batch) {
        int X = 35, Y = 15;

        //activeBorder.flip(false, true);
        activeBorder.setPosition(X, Y);
        Border.get(thsss.borderType).get(0).setPosition(X,Y);
        Border.get(thsss.borderType).get(1).setPosition(X,Y);
        if(thsss.gameScreen.gameStage.character.getX() < 110 &&
                thsss.gameScreen.gameStage.character.getY() < 160) {
            activeBorder.setAlpha(0.2f);
            Border.get(thsss.borderType).get(0).setAlpha(0.2f);
            Border.get(thsss.borderType).get(1).setAlpha(0.2f);
        } else {
            activeBorder.setAlpha(0.8f);
            Border.get(thsss.borderType).get(0).setAlpha(0.8f);
            Border.get(thsss.borderType).get(1).setAlpha(0.8f);
        }
        activeBorder.draw(batch);
        Border.get(thsss.borderType).get(0).draw(batch);
        Border.get(thsss.borderType).get(1).draw(batch);
       // batch.draw(activeBorder, X, Y);
      //  batch.draw(, X, Y);
      //  batch.draw(Border.get(thsss.borderType).get(1), X, Y);

       // Border.get(thsss.borderType).get(2).setRegion(0, 0, 66, Math.round(118 * thsss.borderPercent));

    }

    private void drawScore(Batch batch) {
        StringBuilder highestScoreString = new StringBuilder("0,000,000,000");
        StringBuilder ScoreString = new StringBuilder("0,000,000,000");
        long Hscore = thsss.highestScore;
        long score = thsss.score;
        int now = 12;
        while(Hscore > 0) {
            highestScoreString.setCharAt(now, (char)(Hscore % 10 + (int)'0'));
            Hscore /= 10;
            --now;
            if(now == 1 || now == 5 || now == 9) {
                --now;
            }
        }
        now = 12;
        while(score > 0) {
            ScoreString.setCharAt(now, (char)(score % 10 + (int)'0'));
            score /= 10;
            --now;
            if(now == 1 || now == 5 || now == 9) {
                --now;
            }
        }
        drawFont(batch, highestScoreString, ScoreString);
      //  bitmapFont.draw(batch,highestScoreString, 490, 480 - 50);
      //  bitmapFont.draw(batch,ScoreString, 490, 480 - 75);
    }
    private void drawFont(Batch batch, StringBuilder Hscore, StringBuilder score) {
        int X = 492, Y = 480 - 65;
        for(int i = 0; i <= 12; ++i) {
            //System.out.println((int)Hscore.charAt(i));
            if(i == 1 || i == 5 || i == 9) {
                batch.draw(number.get(10), X, Y);
            } else
            batch.draw(number.get(((int)Hscore.charAt(i) - (int)'0')), X, Y);
            X += 11;
        }
        X = 492;
        Y = 480 - 90;
        for(int i = 0; i <= 12; ++i) {
            if(i == 1 || i == 5 || i == 9) {
                batch.draw(number.get(10), X, Y);
            } else
            batch.draw(number.get(((int)score.charAt(i) - (int)'0')), X, Y);
            X += 11;
        }
    }
}
