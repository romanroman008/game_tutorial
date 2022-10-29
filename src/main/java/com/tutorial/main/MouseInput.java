package com.tutorial.main;

import com.tutorial.main.objects.GameObject;
import com.tutorial.main.objects.enemies.Missile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

public class MouseInput extends MouseAdapter {
    Game game;
    Handler handler;
    GameObject player;

    public MouseInput(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;


//        for (int i = 0; i < handler.getObjects().size(); i++) {
//            if(handler.getObjects().get(i).getId()==ID.Player) player= handler.getObjects().get(i);
//        }
    }

    public void mousePressed(MouseEvent m) {
        float mx = m.getX();
        float my = m.getY();
        if (player == null) {
            player = handler.objects.stream()
                    .filter(p -> p.getId().equals(ID.Player))
                    .findAny()
                    .orElse(null);
        }
        if (game.gameState == Game.STATE.Game) {
            float diffX = (player.getX()) - mx;
            float diffY = (player.getY()) - my;
            shootMissile(diffX, diffY);
        }


    }

    private void shootMissile(float diffX, float diffY) {
        float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        float velX = ((-1 / distance) * diffX) * 10;
        float velY = ((-1 / distance) * diffY) * 10;
        handler.addObject(new Missile(player.getX()+16, player.getY()+16, ID.Enemy, velX, velY, handler));
    }
}
