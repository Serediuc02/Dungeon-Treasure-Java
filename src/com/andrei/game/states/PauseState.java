package com.andrei.game.states;

import com.andrei.game.GamePanel;
import com.andrei.game.util.KeyHandler;
import com.andrei.game.util.MouseHandler;
import com.andrei.game.util.Vector2f;
import com.andrei.game.ui.Button;
import java.awt.*;
import java.awt.image.BufferedImage;


public class PauseState extends GameState {
    private Button btnResume;
    private Button btnExit;
    private Font font;

    public PauseState(GameStatesManager gsm){
        super(gsm);
        BufferedImage imgButton = GameStatesManager.button.getSubimg(0,0,121,26);
        BufferedImage imgHover = GameStatesManager.button.getSubimg(0,29,122,28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnResume = new Button("Resume", imgButton, font, new Vector2f(GamePanel.width/2, GamePanel.height/2-48),32,16);
        btnExit = new Button("Exit", imgButton, font, new Vector2f(GamePanel.width/2, GamePanel.height/2+48),32,16);

        btnResume.addHoverImage(btnResume.createButton("RESUME", imgHover, font, btnResume.getWidth(), btnResume.getHeight(), 32, 20));
        btnExit.addHoverImage(btnExit.createButton("EXIT", imgHover, font, btnExit.getWidth(), btnExit.getHeight(), 32, 20));

        btnResume.addEvent(e -> {
            gsm.pop(GameStatesManager.PAUSE);
        });

        btnExit.addEvent(e -> {
            System.exit(0);
        });
    }
    @Override
    public void update() {
    }
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

        btnResume.input(mouse,key);
        btnExit.input(mouse,key);
    }
    @Override
    public void render(Graphics2D g) {

        btnResume.render(g);
        btnExit.render(g);
//        g.setColor(Color.red);
//        g.drawRect((int) btnResume.getPos().x, (int )btnResume.getPos().y, btnResume.getWidth(), btnResume.getHeight());

    }
}
