package com.tutorial.main.objects.enemies;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.ID;
import com.tutorial.main.objects.GameObject;
import com.tutorial.main.objects.Trail;

import java.awt.*;

public class Missile extends GameObject {
    private Handler handler;

    public Missile(float x, float y, ID id,float velX,float velY, Handler handler) {
        super(x, y, id);

        this.handler=handler;
        this.velX=velX;
        this.velY=velY;

    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,4,4);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        if(y<=0||y>= Game.HEIGHT)
            handler.removeObject(this);

        if(x<=0||x>=Game.WIDTH)
            handler.removeObject(this);

        //handler.addObject(new Trail(x,y,ID.Trail,Color.red,16,16,0.1f,handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,4,4);
    }
}
