package com.tutorial.main;

import com.tutorial.main.objects.MenuParticle;
import com.tutorial.main.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 5825867419458798954L;
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;


    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    private Menu menu;



    public enum STATE{
        Menu,
        Game,
        Help,
        End
    }

    public STATE gameState = STATE.Menu;

    public Game() {

        handler = new Handler();
        hud = new HUD();
        menu=new Menu(this,handler,hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Let's build a game", this);


        spawn=new Spawn(handler,hud);

        r= new Random();


        if(gameState==STATE.Game){
//            handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player,handler));
//            hud.resetScoreLevelAndHealth();
//            handler.clearMenuParticles();
        }

        else{
            for (int i = 0; i < 20; i++) {
                handler.addObject(new MenuParticle(r.nextInt(WIDTH-50),r.nextInt(HEIGHT-50),ID.MenuParticle,handler));
            }
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running=true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
            }

        }
    }

    private void tick() {
        handler.tick();
        if(gameState==STATE.Game){
            hud.tick();
            spawn.tick();
            if(HUD.HEALTH==0){
                endGame();
            }

        }else if(gameState==STATE.Menu){
            menu.tick();
        }

    }
    private void endGame(){
        gameState=STATE.End;
        handler.removePlayer();
        spawn.resetScore();
//        for (int i = 0; i < 20; i++) {
//            handler.addObject(new MenuParticle(r.nextInt(WIDTH-50),r.nextInt(HEIGHT-50),ID.MenuParticle,handler));
//        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();  //co robi ten buffer
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);
        if(gameState==STATE.Game){

            hud.render(g);
        }else if(gameState==STATE.Menu||gameState==STATE.Help||gameState==STATE.End){
            menu.render(g);
        }


        g.dispose();
        bs.show();
    }
    public static float clamp(float var, float min, float max){
        if(var>=max)
            return var=max;
        if(var<=min)
            return var=min;
        return var;
    }

    public static void main(String[] args) {
        new Game();
    }


}
