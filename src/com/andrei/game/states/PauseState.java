package com.andrei.game.states;

import com.andrei.game.util.KeyHandler;
import com.andrei.game.util.MouseHandler;

import java.awt.*;


public class PauseState extends GameState {
    public PauseState(GameStatesManager gsm){
        super(gsm);
    }

    @Override
    public void update() {

        System.out.println("PAUSE");
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics2D g) {

    }
}
