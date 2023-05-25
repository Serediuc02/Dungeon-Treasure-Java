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

public class LevelSelector extends GameState {
    private Button lvlOne;
    private Button lvlTwo;
    private Button lvlThree;
    private Button btnBack;
    private Font font;

    private BufferedImage fundal;

    public LevelSelector(GameStatesManager gsm){
        super(gsm);
        BufferedImage imgButton = GameStatesManager.button.getSubimg(0,0,121,26);
        BufferedImage imgHover = GameStatesManager.button.getSubimg(0,29,122,28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        lvlOne = new Button("Level 1", imgButton, font, new Vector2f(GamePanel.width/2, GamePanel.height/2-96),32,16);
        lvlTwo = new Button("Level 2", imgButton, font, new Vector2f(GamePanel.width/2, GamePanel.height/2-20),32,16);
        lvlThree = new Button("Level 3", imgButton, font, new Vector2f(GamePanel.width/2, GamePanel.height/2+60),32,16);
        btnBack = new Button("BACK", imgButton, font, new Vector2f(GamePanel.width/2, GamePanel.height/2+144),32,16);

        lvlOne.addHoverImage(lvlOne.createButton("Level 1", imgHover, font, lvlOne.getWidth(), lvlOne.getHeight(), 32, 20));
        lvlTwo.addHoverImage(lvlTwo.createButton("Level 2", imgHover, font, lvlTwo.getWidth(), lvlTwo.getHeight(), 32, 20));
        lvlThree.addHoverImage(lvlThree.createButton("Level 3", imgHover, font, lvlThree.getWidth(), lvlThree.getHeight(), 32, 20));
        btnBack.addHoverImage(btnBack.createButton("BACK", imgHover, font, btnBack.getWidth(), btnBack.getHeight(), 32, 20));



        try {
            fundal = ImageIO.read(new File("res/background/fundal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        lvlOne.addEvent(e -> {
            gsm.add(GameStatesManager.PLAY1);
            gsm.pop(GameStatesManager.LEVELSELECTOR);

        });

        lvlTwo.addEvent(e -> {
            gsm.add(GameStatesManager.PLAY2);
            gsm.pop(GameStatesManager.LEVELSELECTOR);
        });

        lvlThree.addEvent(e -> {
            gsm.add(GameStatesManager.PLAY3);
            gsm.pop(GameStatesManager.LEVELSELECTOR);
        });

        btnBack.addEvent(e -> {
            gsm.add(GameStatesManager.MENU);
            gsm.pop(GameStatesManager.LEVELSELECTOR);

        });
    }


    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        btnBack.input(mouse,key);
        lvlOne.input(mouse,key);
        lvlTwo.input(mouse,key);
        lvlThree.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(fundal, 0, 0, null);
        lvlOne.render(g);
        lvlTwo.render(g);
        lvlThree.render(g);
        btnBack.render(g);
    }
}
