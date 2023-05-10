package com.andrei.game.states;

import com.andrei.game.GamePanel;
import com.andrei.game.graphics.Sprite;
import com.andrei.game.util.KeyHandler;
import com.andrei.game.util.MouseHandler;
import com.andrei.game.util.Vector2f;
import jdk.jshell.spi.SPIResolutionException;
import com.andrei.game.graphics.Font;
import java.awt.*;


public class GameStatesManager
{
    private GameState states[];
    public static Vector2f map;
    public static final int PLAY=0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;

    public int onTopState=0;
    public static Font font;

    public boolean getState(int state){
        return states[state] !=null;
    }

    public GameStatesManager(){

        map=new Vector2f(GamePanel.width,GamePanel.height);
        Vector2f.setWorldVar(map.x,map.y);
        states = new GameState[4];
        font= new Font("font/font.png", 10, 10);
        Sprite.currentFont = font;
        states[PLAY]= new PlayState(this);
    }
    public void pop(int state){
        states[state]=null;
    }
    public void add(int state) {
        if (states[state] != null){
            return;
        }
        if(state == PLAY)
        {
            states[PLAY]= new PlayState(this);
        }
        if(state == MENU)
        {
            states[MENU]= new MenuState(this);
        }
        if(state == PAUSE)
        {
            states[PAUSE]= new PauseState(this);
        }
        if(state == GAMEOVER)
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
        for(int i=0;i < states.length; i++)
        {
            if(states[i]!=null)
            {
                states[i].render(g);
            }
        }


    }
}
