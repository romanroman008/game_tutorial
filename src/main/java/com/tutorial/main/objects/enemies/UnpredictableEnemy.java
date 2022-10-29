package com.tutorial.main.objects.enemies;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.ID;
import com.tutorial.main.objects.GameObject;
import com.tutorial.main.objects.Trail;

import java.awt.*;
import java.util.Random;

public class UnpredictableEnemy extends GameObject {

    private Handler handler;
    private Random r=new Random();

    public UnpredictableEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler=handler;
        velX=5;
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

//        if(y<=0||y>= Game.HEIGHT-50)
//            velY*=-1*r.nextFloat(7)/r.nextFloat(7);
//
//
//        if(x<=0||x>=Game.WIDTH-50)
//            velX*=-1*r.nextFloat(7)/r.nextFloat(7);

        if(y<=0||y>= Game.HEIGHT-50){
            if(velY>0){
                velY=r.nextInt(10)*(-1);
            }
            else{
                velY=r.nextInt(10)+1;
            }


        }

        if(x<=0||x>=Game.WIDTH-50)
        {
            if(velX>0){
                velX=(r.nextInt(10)+1)*(-1);
            }

            else{
                velX=r.nextInt(10)+1;
            }


        }

        handler.addObject(new Trail(x,y,ID.Trail,Color.yellow,16,16,0.1f,handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int)x,(int)y,16,16);
    }
}
