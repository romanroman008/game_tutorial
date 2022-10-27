package com.tutorial.main;

import com.tutorial.main.objects.MenuParticle;
import com.tutorial.main.objects.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static com.tutorial.main.Game.HEIGHT;
import static com.tutorial.main.Game.WIDTH;

public class Menu extends MouseAdapter {
    Game game;
    Handler handler;
    HUD hud;
    Random r=new Random();

    public Menu(Game game, Handler handler,HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud=hud;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            //play
            if (mouseOver(mx, my, 220, 120, 200, 60)) {
                game.gameState = Game.STATE.Game;
                handler.clearMenuParticles();
                handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
            hud.resetScoreLevelAndHealth();
            }

            //help
            if (mouseOver(mx, my, 220, 220, 200, 60)) {

                game.gameState = Game.STATE.Help;

            }

            //quit
            if (mouseOver(mx, my, 220, 320, 200, 60)) {
                System.exit(1);
            }
        }

        if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 220, 320, 200, 60)) {
                game.gameState = Game.STATE.Menu;
            }
        }

        if(game.gameState== Game.STATE.End)

            if(mouseOver(mx,my,220, 320, 200, 60)){
                for (int i = 0; i < 20; i++) {
                    handler.addObject(new MenuParticle(r.nextInt(WIDTH-50),r.nextInt(HEIGHT-50),ID.MenuParticle,handler));
                }
                game.gameState= Game.STATE.Menu;
                handler.clearEnemies();
            }


    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width)
            if (my > y && my < y + height){
                AudioPlayer.getSoundMap("click_sound").play();
                return true;
            }

        return false;

    }


    public void tick() {

    }

    public void render(Graphics g) {
        Font font = new Font("arial", 1, 50);
        Font font2 = new Font("arial", 1, 30);
        Font font3 = new Font("arial", 1, 20);

        if (game.gameState == Game.STATE.Menu) {
//            Font font = new Font("arial", 1, 50);
//            Font font2 = new Font("arial", 1, 30);

            g.setColor(Color.white);
            g.setFont(font);
            g.drawString("Menu", 250, 70);

            g.setFont(font2);
            g.drawString("Play", 290, 160);
            g.drawRect(220, 120, 200, 60);

            //g.setColor(Color.white);
            g.drawString("Help", 290, 260);
            g.drawRect(220, 220, 200, 60);

            g.drawString("Quit", 290, 360);
            g.drawRect(220, 320, 200, 60);
        } else if (game.gameState == Game.STATE.Help) {

            g.setFont(font3);
            g.setColor(Color.white);
            g.drawString("Use arrows to move and dodge enemies. Peace man", 80, 260);
            // g.drawRect(220, 120, 200, 200);

            g.setFont(font2);
            g.setColor(Color.white);
            g.drawString("Back", 290, 360);
            g.drawRect(220, 320, 200, 60);
        }

        else if (game.gameState == Game.STATE.End) {


            g.setFont(font3);
            g.setColor(Color.white);
            g.drawString("They've got you this time", 200, 200);
            g.drawString("Your score: " + hud.getScore(), 245, 260);

            g.setFont(font2);
            g.setColor(Color.white);
            g.drawString("Try again", 250, 360);
            g.drawRect(220, 320, 200, 60);
        }

    }
}
