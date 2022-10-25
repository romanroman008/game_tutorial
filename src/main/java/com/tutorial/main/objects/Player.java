package com.tutorial.main.objects;

import com.tutorial.main.*;

import java.awt.*;
import java.util.Random;

public class   Player extends GameObject {
    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        this.handler=handler;

    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;
        x= Game.clamp(x,0,Game.WIDTH-50);
        y=Game.clamp(y,0,Game.HEIGHT-70);

        handler.addObject(new Trail(x,y,ID.Trail,Color.white,32,32,0.1f,handler));
        collision();

    }

    private void collision(){
        for (GameObject object : handler.getObjects()) {
            if(object.getId()==ID.Enemy){
                if(getBounds().intersects(object.getBounds())){
                    HUD.HEALTH-=2;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (id == ID.Player) g.setColor(Color.white);
        else g.setColor(Color.blue);
        g.fillRect((int)x,(int) y, 32, 32);

//        Graphics2D g2d=(Graphics2D)g;
//        g.setColor(Color.CYAN);
//        g2d.draw(getBounds());
    }


}
