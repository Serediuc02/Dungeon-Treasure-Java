package com.andrei.game.states;

import com.andrei.game.util.KeyHandler;
import com.andrei.game.util.MouseHandler;

import java.awt.*;

public abstract class GameState {
    protected GameStatesManager gsm;
    public GameState(GameStatesManager gsm){
        this.gsm = gsm;
    }
    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
}
