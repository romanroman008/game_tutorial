package com.tutorial.main;

import com.tutorial.main.objects.enemies.BasicEnemy;
import com.tutorial.main.objects.enemies.BossEnemy;
import com.tutorial.main.objects.enemies.FastEnemy;
import com.tutorial.main.objects.enemies.SmartypantsEnemy;

public class Spawn {

    private Handler handler;
    private HUD hud;

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;


        if (hud.getScore()==1) {
            handler.addObject(new BasicEnemy(1, 1, ID.Enemy, handler));
        }


        if (scoreKeep >= 100) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            if (hud.getLevel()==2) {
                handler.addObject(new BasicEnemy(1, 1, ID.Enemy, handler));
            }
            if(hud.getLevel()==3){
                handler.addObject(new FastEnemy(100,100,ID.Enemy,handler));
            }
            if(hud.getLevel()==5)
                handler.addObject(new SmartypantsEnemy(10,10,ID.Enemy,handler));
            if (hud.getLevel()==7) {
                handler.addObject(new BasicEnemy(1, 1, ID.Enemy, handler));
            }
            if (hud.getLevel()==9) {
                handler.addObject(new BasicEnemy(1, 1, ID.Enemy, handler));
            }
            if (hud.getLevel()==10) {
                handler.clearEnemies();
                handler.addObject(new BossEnemy((Game.WIDTH/2)-48,-100,ID.Enemy,handler));
            }

            
        }
    }
}


