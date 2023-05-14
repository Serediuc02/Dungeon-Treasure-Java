package com.andrei.game.states;
import com.andrei.game.GamePanel;
import com.andrei.game.entity.Enemy;
import com.andrei.game.entity.Player;
import com.andrei.game.graphics.Font;
import com.andrei.game.tiles.TileManager;
import com.andrei.game.util.*;
import com.andrei.game.graphics.*;
import java.awt.*;
public class PlayState extends GameState {

    public static Vector2f map;
    private Font font;
    private Player player;
    private TileManager tm;
    private Enemy enemy;
    private Camera cam;

    public PlayState(GameStatesManager gsm) {
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
        cam = new Camera(new AABB(new Vector2f(0, 0), GamePanel.width + 64, GamePanel.height + 64));
        tm = new TileManager("tile/tilemap.xml", cam);
        enemy = new Enemy(cam,new Sprite("entity/littlegirl.png", 48, 48), new Vector2f(0 + (GamePanel.width / 2) - 32  - 200, 0 + (GamePanel.height / 2) - 32 + 200), 64);
        player = new Player(cam,new Sprite("entity/player2.png"), new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 128);
        cam.target(player);
    }
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update(enemy);
        cam.update();
        if(enemy.health > 0)
        {
            enemy.update(player);
        }

    }
    public void input(MouseHandler mouse, KeyHandler key) {

        key.escape.tick();
        player.input(mouse, key);
        cam.input(mouse, key);
        if(key.escape.clicked)
        {
            if(gsm.getState(GameStatesManager.PAUSE))
                gsm.pop(GameStatesManager.PAUSE);
            else {
                gsm.add(GameStatesManager.PAUSE);
            }
        }
    }
    public void render(Graphics2D g) {
        tm.render(g);
        Sprite.drawArray(g, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 192, 32), 32, 24);
        // de revenit si implementat scorul TODO
        Sprite.drawArray(g, "SCORE: 0", new Vector2f(GamePanel.width/2, 10), 32, 23);
        player.render(g);
        if(enemy.health > 0)
        {
            enemy.render(g);

        }
        cam.render(g);



    }
}
