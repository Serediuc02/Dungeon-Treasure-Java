package com.andrei.game.states;

import com.andrei.game.GamePanel;
import com.andrei.game.graphics.Sprite;
import com.andrei.game.util.Camera;
import com.andrei.game.util.KeyHandler;
import com.andrei.game.util.MouseHandler;
import com.andrei.game.util.AABB;
import com.andrei.game.util.Vector2f;
import jdk.jshell.spi.SPIResolutionException;
import com.andrei.game.graphics.Font;
import com.andrei.game.graphics.Fontf;
import java.awt.*;



public class GameStatesManager
{
    private GameState states[];
    public static Vector2f map;
    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
    public static Font font;
    public static Fontf fontf;
    public static Sprite ui;
    public static Sprite button;
    public static Camera cam;

    public static Graphics2D g;

    public GameStatesManager(Graphics2D g){
        GameStatesManager.g=g;
        map=new Vector2f(GamePanel.width,GamePanel.height);
        Vector2f.setWorldVar(map.x,map.y);
        states = new GameState[4];

        font= new Font("font/font.png", 10, 10);
        fontf= new Fontf();
        fontf.loadFont("font/Stackedpixel.ttf", "MeatMadness");
        fontf.loadFont("font/GravityBold8.ttf", "GravityBold8");
        Sprite.currentFont = font;

        ui = new Sprite("ui/ui.png",64,64);
        button = new Sprite("ui/buttons.png",122,57);

        cam = new Camera(new AABB(new Vector2f(-64, -64), GamePanel.width + 128, GamePanel.height + 128));

        states[PLAY]= new PlayState(this,cam);
    }

    public void add(int state) {
        if (states[state] != null){
            return;
        }
        if(state == PLAY)
        {
            cam = new Camera(new AABB(new Vector2f(0,0), GamePanel.width+64,GamePanel.height+64));
            states[PLAY]= new PlayState(this,cam);
        }
        else if(state == MENU)
        {
            states[MENU]= new MenuState(this);
        }
        else if(state == PAUSE)
        {
            states[PAUSE]= new PauseState(this);
        }
        else if(state == GAMEOVER)
        {
            states[GAMEOVER]= new GameOverState(this);
        }
    }

    public void addAndpop(int state){
       addAndpop(state,0);
    }

    public void addAndpop(int state, int remove){
        pop(state);
        add(state);
    }

    public void update(){
        for(int i=0; i < states.length; i++)
        {
            if(states[i]!=null)
            {
                states[i].update();
            }
        }
    }
    public void input(MouseHandler mouse, KeyHandler key){

        for(int i=0;i < states.length; i++)
        {
            if(states[i]!=null)
            {
                states[i].input(mouse,key);
            }
        }

    }
    public void render(Graphics2D g){
        g.setFont(GameStatesManager.fontf.getFont("MeatMadness"));
        for(int i=0;i < states.length; i++)
        {
            if(states[i]!=null)
            {
                states[i].render(g);
            }
        }
    }
    public GameState getState(int state){
        return states[state];
    }

    public boolean isStateActive(int state){return states[state]!= null;}
    public void pop(int state){
        states[state]=null;
    }

}
