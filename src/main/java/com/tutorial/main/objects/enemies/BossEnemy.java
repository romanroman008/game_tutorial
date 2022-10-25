package com.tutorial.main.objects.enemies;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.ID;
import com.tutorial.main.objects.GameObject;
import com.tutorial.main.objects.Trail;

import java.awt.*;
import java.util.Random;

public class BossEnemy extends GameObject {

    private Handler handler;
    private int timer=80;
    private int timer2=50;
    private int timer3 = 10;
    private Random r=new Random();

    public BossEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler=handler;
      //  velX=0;
        velY=2;

    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,96,96);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

//        if(y<=0||y>= Game.HEIGHT-50)
//            velY*=-1;


        if(timer==0)
        {
            velY=0;
            if(timer2==0){
                velX=2;
                timer--;
            }
            else
            timer2--;
        }
        else
            timer--;

//        if(timer2==0&&timer3%20==0){
//            handler.addObject(new BossBulletEnemy((int)x+48,(int)y+48,ID.Enemy,handler));
//            timer3=20;
//        }
//        timer3--;

        if(r.nextInt(10)==0){
            handler.addObject(new BossBulletEnemy((int)x+48,(int)y+48,ID.Enemy,handler));
        }

        if(x<=0||x>=Game.WIDTH-96)
            velX*=-1.1f;

       // handler.addObject(new Trail(x,y,ID.Trail,Color.red,16,16,0.1f,handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,96,96);
    }
}
