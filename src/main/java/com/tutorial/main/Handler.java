package com.tutorial.main;

import com.tutorial.main.objects.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }

    public void clearEnemies(){
        objects.removeIf(object -> object.getId() == ID.Enemy);
    }

    public void clearMenuParticles(){
        objects.removeIf(object -> object.getId() == ID.MenuParticle);
        objects.removeIf(object -> object.getId() == ID.Trail);
    }

    public LinkedList<GameObject> getObjects() {
        return objects;
    }
}
