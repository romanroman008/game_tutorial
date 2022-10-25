package com.tutorial.main.objects.enemies;

import com.tutorial.main.*;
import com.tutorial.main.objects.GameObject;
import com.tutorial.main.objects.Trail;

import java.awt.*;

public class FastEnemy extends GameObject {

    private Handler handler;

    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler=handler;
        velX=2;
        velY=10;

    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        if(y<=0||y>= Game.HEIGHT-50)
            velY*=-1;

        if(x<=0||x>=Game.WIDTH-50)
            velX*=-1;

        handler.addObject(new Trail(x,y,ID.Trail,Color.cyan,16,16,0.1f,handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }
}
