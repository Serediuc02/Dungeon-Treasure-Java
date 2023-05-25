package com.andrei.game.states;

import com.andrei.game.GamePanel;
import com.andrei.game.graphics.Sprite;
import com.andrei.game.util.KeyHandler;
import com.andrei.game.util.MouseHandler;
import com.andrei.game.util.Vector2f;
import com.andrei.game.ui.Button;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverState extends GameState{
    private String gameover = "GAME OVER";

    private BufferedImage imgButton;
    private BufferedImage imgHover;
    private Button btnReset;
    private Button btnQuit;
    private Font font;

    public GameOverState(GameStatesManager gsm)
    {
        super(gsm);

        imgButton = GameStatesManager.button.getSubimg(0, 0, 121, 26);
        imgHover = GameStatesManager.button.getSubimg(0, 29, 122, 28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnReset = new Button("RESTART", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 - 48), 32, 16);
        btnQuit = new Button("QUIT", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 + 48), 32, 16);

        btnReset.addHoverImage(btnReset.createButton("RESTART", imgHover, font, btnReset.getWidth(), btnReset.getHeight(), 32, 20));
        btnQuit.addHoverImage(btnQuit.createButton("QUIT", imgHover, font, btnQuit.getWidth(), btnQuit.getHeight(), 32, 20));

        btnReset.addEvent(e -> {

            gsm.add(GameStatesManager.LEVELSELECTOR);

            gsm.pop(GameStatesManager.GAMEOVER);
        });

        btnQuit.addEvent(e -> {
            System.exit(0);
        });
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        btnReset.input(mouse, key);
        btnQuit.input(mouse, key);

    }

    @Override
    public void render(Graphics2D g) {
        Sprite.drawArray(g, gameover, new Vector2f(GamePanel.width / 2 - gameover.length() * (32 / 2), GamePanel.height / 2 - 32 / 2), 32, 32, 32);
        btnReset.render(g);
        btnQuit.render(g);
    }
}
