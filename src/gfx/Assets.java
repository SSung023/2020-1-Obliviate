package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

//image & sound
public class Assets {

    private static final int width = 100, height = 100;

    public static BufferedImage player, wall, floor;

    //going to load in everything on our game
    //it will call once
    public static void init(){

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/Final.png"));

        player = sheet.crop(0,1, width, height);
        wall = sheet.crop(0,0,width,height);
        floor = sheet.crop(7,0,width,height);
    }


}
