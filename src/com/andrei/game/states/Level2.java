package com.andrei.game.states;
import com.andrei.game.GamePanel;
import com.andrei.game.entity.Enemy;
import com.andrei.game.entity.Player;
import com.andrei.game.graphics.Font;
import com.andrei.game.tiles.TileManager;
import com.andrei.game.util.*;
import com.andrei.game.graphics.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
public class Level2 extends GameState {
    public static Vector2f map;
    private Font font;
    private Player player;
    private TileManager tm;
    private Enemy enemy;
    private Camera cam;
    private List<Enemy> enemies;
    private int score=0;

    public Level2(GameStatesManager gsm,Camera cam) {
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
        cam = new Camera(new AABB(new Vector2f(0, 0), GamePanel.width + 128, GamePanel.height + 128));
        this.cam=cam;
        tm = new TileManager("tile/tilemap2.xml", cam);

        enemies = new ArrayList<>();
        enemies.add(new Enemy(cam, new Sprite("entity/guard.png"), new Vector2f(0 + (GamePanel.width / 2) +300  , 0 + (GamePanel.height / 2) - 32 ), 128));
        enemies.add(new Enemy(cam, new Sprite("entity/guard.png"), new Vector2f(0 + (GamePanel.width / 2) +1600  , 0 + (GamePanel.height / 2) - 32 ), 128));
        enemies.add(new Enemy(cam, new Sprite("entity/guard.png"), new Vector2f(0 + (GamePanel.width / 2) +1000  , 0 + (GamePanel.height / 2) +1400 ), 128));
        enemies.add(new Enemy(cam, new Sprite("entity/guard.png"), new Vector2f(0 + (GamePanel.width / 2) +1000  , 0 + (GamePanel.height / 2) +2500 ), 128));

        ScoreBd.CitireScoruri();

        for (Enemy enemy : enemies) {
            enemy.setHealth(65,65);
        }
        player = new Player(cam,new Sprite("entity/player2.png"), new Vector2f(0 + (GamePanel.width / 2) - 32+60, 0 + (GamePanel.height / 2) - 32), 128);
        cam.target(player);
    }


    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        if(!gsm.isStateActive(GameStatesManager.PAUSE))
        {
            boolean allEnemiesDead = true;
            if(player.health <=0)
            {

                gsm.add(GameStatesManager.GAMEOVER);
                ScoreBd.ScriereScor(2,score);
                gsm.pop(GameStatesManager.PLAY2);
            }


            for (int i = 0; i < enemies.size(); i++) {
                Enemy currentEnemy = enemies.get(i);
                currentEnemy.checkCoin(player);
                if (currentEnemy.getHealth() > 0)
                    allEnemiesDead = false;


                if (currentEnemy.getHealth() <= 0) {

                    continue; // Treci la următorul inamic dacă inamicul curent este mort
                }


                currentEnemy.update(player);
                for (int j = i + 1; j < enemies.size(); j++) {
                    Enemy otherEnemy = enemies.get(j);
                    if (otherEnemy.getHealth() <= 0) {

                        continue; // Treci la următorul inamic dacă inamicul analizat este mort
                    }
                    if (currentEnemy.getBounds().collides(otherEnemy.getBounds())) {
                        currentEnemy.knockBack();
                    }
                }
            }
            player.update(enemies);
            cam.update();
            if (allEnemiesDead) {
                gsm.add(GameStatesManager.GAMEOVER);
                ScoreBd.ScriereScor(2,score);
                gsm.pop(GameStatesManager.PLAY2);
            }
        }


    }


    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();
        if(!gsm.isStateActive(GameStatesManager.PAUSE))
        {
            player.input(mouse,key);
            cam.input(mouse,key);
        }
        if(key.escape.clicked)
        {
            if(gsm.isStateActive(GameStatesManager.PAUSE))
            {
                gsm.pop(GameStatesManager.PAUSE);
            }
            else
            {
                gsm.add(GameStatesManager.PAUSE);
            }
        }
    }

    public void render(Graphics2D g) {
        tm.render(g);
//        Sprite.drawArray(g, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 192, 32), 32, 24);
        Sprite.drawArray(g, String.valueOf(score), new Vector2f(GamePanel.width/2, 32), 32, 20);
        Sprite.drawArray(g, "Max Score:", new Vector2f(GamePanel.width/2-620 , 32), 26, 20);
        Sprite.drawArray(g, String.valueOf(ScoreBd.scoreLv2), new Vector2f(GamePanel.width/2-620 , 64), 26, 20);
        player.render(g);

        for (Enemy enemy : enemies) {
            if (enemy.health > 0) {
                enemy.render(g);
            }
            if (enemy.coin.isVisible() && enemy.coin.isPicked()==false)
            {
                enemy.coin.render(g);
                if(enemy.coin.coinCollision(player))
                {
                    enemy.coin.once=true;
                }
                if(enemy.coin.once==true)
                {
                    score+= 150;
                }
            }
        }
        cam.render(g);
    }
}
