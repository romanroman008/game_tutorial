package com.tutorial.main.objects.enemies;

import com.tutorial.main.objects.GameObject;
import com.tutorial.main.Handler;
import com.tutorial.main.ID;
import com.tutorial.main.objects.Trail;

import java.awt.*;

public class SmartypantsEnemy extends GameObject {

    private Handler handler;
    private GameObject player;


    public SmartypantsEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

//        for (GameObject object : handler.objects) {
//            if (object.getId() == ID.Player) player = object;
//        }


        for (int i = 0; i < handler.getObjects().size(); i++) {
            if(handler.getObjects().get(i).getId()==ID.Player) player= handler.getObjects().get(i);
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() -9;
        float diffY = y - player.getY() - 9;
        float distance=(float) Math.sqrt(Math.pow(diffX+9,2)+Math.pow(diffY+9,2));



        velX= ((-1/distance)*diffX)*2;
        velY= ((-1/distance)*diffY)*2;

        handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.1f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x,(int) y, 16, 16);
    }
}
