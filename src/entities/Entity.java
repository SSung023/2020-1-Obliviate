package entities;

import java.awt.*;

public abstract class Entity {

    //부드러운 움직임을 위해 float으로 선언
    protected float x, y;

    //set starting position
    public Entity(float x, float y){
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
