package thsss;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.sql.Time;


public class Character extends Actor {

    //private Array<TextureRegion>[] Sanae;
    private Thsss thsss;
    private TextureRegion[][] Sanae;
    private Texture Sa;
    private int moveStatus;
    private long lastSanaeTime;
    private int preSanaeType;
    private int sanaeType;
    private int sanaeTot;
    private int totFrame;
    private static final int sqrt270 = 191;
    private static final int lowSqrt270 = 85;
    private double characterX, characterY, radius;
    public Character(Thsss thsss) {
        this.thsss = thsss;
        characterX= 40;
        characterY = 20;
        this.setX(40);
        this.setY(20);
        this.init();
    }
    private int getMoveStatus() {
        return thsss.moveStatus;
    }
    private void init() {
        Sa = thsss.manager.get("Image/MyPlane/Sanae.png", Texture.class);
        Sanae = new TextureRegion[3][8];
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 8; ++j) {
                Sanae[i][j] = new TextureRegion(Sa, 32 * j, 48 * i,32, 48);
            }
        }
    }
    @Override
    public void act(float delta){
        int speed = thsss.lowSpeed ? 120:270;
        int sqrtSpeed = thsss.lowSpeed?lowSqrt270:sqrt270;
        ++totFrame;
 //       System.out.println(sqrtSpeed);
        switch (getMoveStatus()) {
            case 1:
                characterY += speed *delta;
                this.setY((float)characterY);
                sanaeType = 0;
                break;
            case 2:
                characterX += speed * delta;
                this.setX((float)characterX);
                sanaeType = 2;
                break;
            case 3:
                characterY -= speed * delta;
                this.setY((float)characterY);
                sanaeType = 0;
                break;
            case 4:
                characterX -= speed   * delta;
                this.setX((float) characterX);
                sanaeType = 1;
                break;
            case 5:
                characterY += sqrtSpeed * delta;
                characterX += sqrtSpeed * delta;
                this.setY((float)characterY);
                this.setX((float)characterX);
                sanaeType = 2;
                break;
            case 6:
                characterY -= sqrtSpeed * delta;
                characterX += sqrtSpeed * delta;
                this.setY((float)characterY);
                this.setX((float)characterX);
                sanaeType = 2;
                break;
            case 7:
                characterY -= sqrtSpeed * delta;
                characterX -= sqrtSpeed * delta;
                this.setY((float)characterY);
                this.setX((float)characterX);
                sanaeType = 1;
                break;
            case 8:
                characterY += sqrtSpeed * delta;
                characterX -= sqrtSpeed * delta;
                this.setY((float)characterY);
                this.setX((float)characterX);
                sanaeType = 1;
                break;
            case 0:
                sanaeType = 0;
                break;
        }
        if(characterX < 35) {
            characterX = 35;
        }
        if(characterX > 420 - 32) {
            characterX = 420 - 32;
        }
        if(characterY > 465 - 48) {
            characterY = 465 - 48;
        }
        if(characterY < 15){
            characterY = 15;
        }

        if(sanaeType != preSanaeType) {
            sanaeTot = 0;
        } else {
            if(TimeUtils.nanoTime() - lastSanaeTime > 100000000) {
                lastSanaeTime = TimeUtils.nanoTime();
                sanaeTot += 1;
                if(sanaeTot >= 8) {
                    if(sanaeType == 0) {
                        sanaeTot = 0;
                    } else {
                        sanaeTot = 3;
                    }
                }
                totFrame = 0;
            }
        }
        preSanaeType = sanaeType;
      //  System.out.println("acting");
    }
    @Override
    public void draw(Batch batch, float delta) {
        batch.draw(Sanae[sanaeType][sanaeTot], getX(), getY());
       // System.out.println("drawing");
    }
    public Point getPosition() {
        return new Point(characterX, characterY);
    }
    public double getRadius() {
        return radius;
    }
}
