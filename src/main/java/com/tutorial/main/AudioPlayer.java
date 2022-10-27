package com.tutorial.main;


import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {


    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();



    public static void load(){
//        File file=new File("resources/music/midas.wav");
//        File file2=new File("resources/sound/beep.wav");
//
//        AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(file);
//        Clip clip=AudioSystem.getClip();
//        clipMap.put("midas",clip);

        try {

            musicMap.put("theme_song",new Music("src/main/resources/music/Sentino-Midas.ogg"));
            soundMap.put("click_sound",new Sound("src/main/resources/sounds/click.ogg"));

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public static Sound getSoundMap(String key) {
        return soundMap.get(key);
    }

    public static Music getMusicMap(String key) {
        return musicMap.get(key);
    }
//    public static Clip getClip(String key){
//        return clipMap.get(key);
//    }
}
