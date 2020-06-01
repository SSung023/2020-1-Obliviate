package gfx;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class SpriteSheet {

    //모든 이미지를 모아놓은 sprite sheet을 담을 object
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    //불러들인 sprite sheet에서 이미지를 자르는 함수
    public BufferedImage crop(int x, int y, int width, int height){
        //(x*100, y*100)부터 100*100의 그림을 잘라온다
        return sheet.getSubimage(x*100, y*100, width, height);
    }

}
