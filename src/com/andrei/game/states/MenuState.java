package com.andrei.game.states;

import com.andrei.game.GamePanel;
import com.andrei.game.ui.Button;
import com.andrei.game.util.KeyHandler;
import com.andrei.game.util.MouseHandler;
import com.andrei.game.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuState extends GameState {
    private Button btnPlay;
    private Button btnExit;
    private Font font;

    private BufferedImage fundal;

    public MenuState(GameStatesManager gsm){
        super(gsm);
        BufferedImage imgButton = GameStatesManager.button.getSubimg(0,0,121,26);
        BufferedImage imgHover = GameStatesManager.button.getSubimg(0,29,122,28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnPlay = new Button("Play", imgButton, font, new Vector2f(GamePanel.width/2, GamePanel.height/2-48),32,16);
        btnExit = new Button("Exit", imgButton, font, new Vector2f(GamePanel.width/2, GamePanel.height/2+46),32,16);

        btnPlay.addHoverImage(btnPlay.createButton("Play", imgHover, font, btnPlay.getWidth(), btnPlay.getHeight(), 32, 20));
        btnExit.addHoverImage(btnExit.createButton("EXIT", imgHover, font, btnExit.getWidth(), btnExit.getHeight(), 32, 20));

        try {
            fundal = ImageIO.read(new File("res/background/fundal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnPlay.addEvent(e -> {
            gsm.add(GameStatesManager.LEVELSELECTOR);
         //   gsm.add(GameStatesManager.PLAY);
            gsm.pop(GameStatesManager.MENU);
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
        btnPlay.input(mouse,key);
        btnExit.input(mouse,key);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(fundal, 0, 0, null);
        btnPlay.render(g);
        btnExit.render(g);

    }
}
