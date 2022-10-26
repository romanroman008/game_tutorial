package com.tutorial.main.objects;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.ID;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject {

    private Handler handler;



    private Random r=new Random();

    private int red=r.nextInt(255);
    private int blue=r.nextInt(255);
    private int green=r.nextInt(255);

    private Color color=new Color(red,blue,green);

    public MenuParticle(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler=handler;
        velX=r.nextInt(7- -7)+-7;
        velY=r.nextInt(7- -7)+-7;
        if(velX<1&&velX>-1)
            velX=1;
        if(velY<1&&velY>-1)
            velY=1;

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

        if(x<=0||x>=Game.WIDTH-30)
            velX*=-1;

        handler.addObject(new Trail(x,y,ID.Trail,color,16,16,0.05f,handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x,(int)y,16,16);
    }
}
