package com.andrei.game.graphics;

import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.awt.Font;

//gestionare fonturi
public class Fontf {

    private HashMap <String, Font> fonts;
    //mapa intre diferite fonturi si numele acestuia

    public Fontf()
    {
        fonts = new HashMap<String,Font>();
    }
    public void loadFont(String path, String name){
        try{
            System.out.println("Loading: " + path);
            Font custom = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream(path));
            GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(custom);
            Font font = new Font(name, Font.PLAIN, 32);
            fonts.put(name, font);
        }catch (Exception e)
        {
            System.out.println("ERROR: can't load font " + path);
            e.printStackTrace();
        }
    }
     public Font getFont(String name){return fonts.get(name);}
}

