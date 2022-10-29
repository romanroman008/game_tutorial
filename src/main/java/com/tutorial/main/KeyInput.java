package com.tutorial.main;

import com.tutorial.main.objects.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown=new boolean[4];
    private Game game;

    public KeyInput(Handler handler,Game game) {
        this.handler = handler;
        this.game=game;
        this.keyDown[0]=false;
        this.keyDown[1]=false;
        this.keyDown[2]=false;
        this.keyDown[3]=false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(game.gameState== Game.STATE.Game){
            for (GameObject object : this.handler.objects) {
                if(object.getId() ==ID.Player){
                    if(key==KeyEvent.VK_LEFT) {
                        object.setVelX(-5);
                        keyDown[0]=true;
                    }
                    if(key==KeyEvent.VK_RIGHT){
                        object.setVelX(5);
                        keyDown[1]=true;
                    }
                    if(key==KeyEvent.VK_UP){
                        object.setVelY(-5);
                        keyDown[2]=true;
                    }

                    if(key==KeyEvent.VK_DOWN)
                    {
                        object.setVelY(5);
                        keyDown[3]=true;
                    }

                }
            }
            if(key==KeyEvent.VK_P)
            {
                if(game.pause!=true)
                    game.pause=true;
                else
                    game.pause=false;
            }
        }



        if(key==KeyEvent.VK_ESCAPE) System.exit(1);

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for (GameObject object : this.handler.objects) {
            if(object.getId() ==ID.Player) {
                if (key == KeyEvent.VK_LEFT)
                    keyDown[0] = false;
                if (key == KeyEvent.VK_RIGHT)
                    keyDown[1] = false;
                if (key == KeyEvent.VK_UP)
                    keyDown[2] = false;
                if (key == KeyEvent.VK_DOWN)
                    keyDown[3] = false;


                //horizontal movement
                if (!keyDown[0] && !keyDown[1])
                    object.setVelX(0);
                //vertical movement
                if (!keyDown[2] && !keyDown[3])
                    object.setVelY(0);
            }
        }



    }
}
