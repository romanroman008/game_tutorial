package com.tutorial.main.objects.enemies;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.ID;
import com.tutorial.main.objects.GameObject;
import com.tutorial.main.objects.Trail;

import java.awt.*;
import java.util.Random;

public class BossBulletEnemy extends GameObject {

    private Handler handler;
    private Random r=new Random();

    public BossBulletEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler=handler;

        velX=r.nextInt(5+5)-5;
        velY=5;

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
            this.handler.removeObject(this);

        if(x<=0||x>=Game.WIDTH)
            this.handler.removeObject(this);

        handler.addObject(new Trail(x,y,ID.Trail,Color.red,16,16,0.1f,handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }
}
