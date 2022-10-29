package com.tutorial.main.objects.enemies;

import com.tutorial.main.*;
import com.tutorial.main.objects.GameObject;
import com.tutorial.main.objects.Trail;

import java.awt.*;

public class BasicEnemy extends GameObject {

    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler=handler;
        velX=5;
        velY=5;

    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }

    private boolean collision(){
        for (GameObject object : handler.getObjects()) {
            if(object.getId()==ID.PlayerMissile){
                if(getBounds().intersects(object.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        if(collision())
            this.handler.removeObject(this);

        if(y<=0||y>= Game.HEIGHT-50)
            velY*=-1;

        if(x<=0||x>=Game.WIDTH-50)
            velX*=-1;

        handler.addObject(new Trail(x,y,ID.Trail,Color.red,16,16,0.1f,handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }
}
