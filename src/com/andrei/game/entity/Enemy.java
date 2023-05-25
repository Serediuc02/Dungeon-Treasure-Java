package com.andrei.game.entity;

import com.andrei.game.GamePanel;
import com.andrei.game.graphics.Sprite;
import com.andrei.game.util.AABB;
import com.andrei.game.util.Camera;
import com.andrei.game.util.Vector2f;
import java.awt.*;

public class Enemy extends Entity {
    private AABB sens;
    private int r;
    private Camera cam;
    protected int xOffset;
    protected int yOffset;
    public float health=100;
    public Coin coin;
    public int coinPick=0;


    public Enemy(Camera cam, Sprite sprite, Vector2f f, int size) {
        super(sprite, f, size);
        this.cam = cam;
        acc = 1f;
        maxSpeed = 2f;
        r = 350;

        coin = new Coin(cam,new Sprite("entity/coin_bronze.png",10,10),new Vector2f((this.pos.x),(this.pos.y)),10);
        bounds.setWidth(45);
        bounds.setHeight(20);
        bounds.setXOffset(43);
        bounds.setYOffset(80);

        coin.setPos(new Vector2f(pos.x, pos.y));
        sens = new AABB(new Vector2f(f.x + size / 2 - r / 2, f.y + size / 2 - r / 2), r);

    }
    private void destroy(){
       // dead=true;
    }
    private void move(Player player) {
        if (sens.colCircleBox(player.getBounds())) {
            //System.out.println("Te a vazut");
            if (pos.y  > player.pos.y + 1) {
                dy -= acc;
                up = true;
                down = false;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            } else if (pos.y  < player.pos.y - 1) {
                dy += acc;
                down = true;
                up = false;
                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
            } else {
                dy = 0;
                up = false;
                down = false;
            }
            if (pos.x > player.pos.x + 1) {
                dx -= acc;
                left = true;
                right=false;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            } else if (pos.x < player.pos.x - 1)
            {
                dx += acc;
                right = true;
                left = false;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
                }
            } else {
                dx = 0;
                right = false;
                left = false;
            }
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
            dx = 0;
            dy = 0;
        }

    }
    @Override
    public void animate(){
        if(health==0)
            destroy();

        if(left)
        {
            lastAni=LEFT;
        } else if (right) {
            lastAni= RIGHT;
        }

        if(left){
            if (currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT,sprite.getSpriteArray(LEFT),5);

            }
        }
        else if(right){
            if (currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT,sprite.getSpriteArray(RIGHT),5);

            }
        } else if(up){
            if (currentAnimation != lastAni || ani.getDelay() == -1) {
                setAnimation(lastAni,sprite.getSpriteArray(lastAni),5);
            }
        }
        else if(down){
            if (currentAnimation != lastAni || ani.getDelay() == -1) {
                setAnimation(lastAni,sprite.getSpriteArray(lastAni),5);
            }
        } else
        {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }

    }
    public void update(Player player) {
        if(player != null )
        {

            if(cam.getBoundsOnScreen().collides(dx,dy,this.bounds))
            {
                super.update();// pt animatie
                move(player);
                if (!tc.collisionTile(dx, 0))
                {
                    sens.getPos().x += dx;
                    pos.x += dx;
                   // coin.pos.x += dx;
                }
                if (!tc.collisionTile(0, dy)) {
                    sens.getPos().y += dy;
                    pos.y += dy;
                  //  coin.pos.y +=dy;
                }
                coin.setPos(new Vector2f(pos.x ,pos.y));
                coin.updateBounds(this.bounds);

            }
            else {
                destroy();
            }

            if (bounds.collides(player.getBounds())) {
                if(this.left)
                {
                    player.dx -= 2;
                }
                if(this.right)
                {
                    player.dx += 2;
                    System.out.println("haha");
                }
                if(this.up)
                {
                    player.dy -= 2;
                }
                if(this.down)
                {
                    player.dy += 2;
                }
                coin.setPos(new Vector2f(pos.x ,pos.y));
                coin.updateBounds(this.bounds);

            }

        }
    }
    public void checkCoin(Player player){
        if(health<=0 && coin.picked==false)
        {
            this.coin.isVisible=true;
        }
        if(coin.coinCollision(player))
        {
            this.coin.picked=true;
            this.coin.isVisible=false;

        }
    }

    public void knockBack(){
        if (this.up) {
            this.dy += 15;
        }
        if (this.left) {
            this.dx += 15;
        }
        if (this.right) {
            this.dx -= 15;
        }
        if (this.down) {
            this.dy -= 15;
        }
    }
    public float getHealth(){return health;}
    @Override
    public void render(Graphics2D g) {
        if(cam.getBoundsOnScreen().collides(this.bounds))
         {

             g.setColor(Color.red);
             g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
             g.setColor(Color.blue);
             g.drawOval((int) (sens.getPos().getWorldVar().x), (int) (sens.getPos().getWorldVar().y), r, r);
             g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);

            //bara de viata inamic
            //todo de implementat viata la inamic si scalat dupa procentajul de viata
             g.setColor(Color.red);
             g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset() ), (int) (pos.getWorldVar().y + 40), 44, 5);
             g.setColor(Color.green);
             g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset() ), (int) (pos.getWorldVar().y + 40), (int) (44 * (health/100)), 5);
        }

    }
}
