package com.andrei.game.entity;

import com.andrei.game.graphics.Sprite;
import com.andrei.game.states.PlayState;
import com.andrei.game.util.KeyHandler;
import com.andrei.game.util.MouseHandler;
import com.andrei.game.util.Vector2f;

import java.awt.*;

public class Player extends Entity{
    public Player(Sprite sprite, Vector2f origin, int size) {

        super(sprite, origin, size);
        maxSpeed = 4f;
        acc = 1f; //accelerare
        deacc = 0.3f;
        bounds.setWidth(45);
        bounds.setHeight(20);
        bounds.setXOffset(43);
        bounds.setYOffset(80);

        hitBounds.setWidth(64);
        hitBounds.setHeight(64);
    }
    private void move(){
        if(up){
            dy -= acc;
            if(dy < -maxSpeed){
                dy = -maxSpeed;
            }
        }else {
            if(dy < 0){
                dy += deacc;
                if(dy >0){
                    dy=0;
                }
            }
        }
        if(down){
            dy += acc;
            if(dy > maxSpeed){
                dy = maxSpeed;
            }
        }else {
            if(dy > 0){
                dy -= deacc;
                if(dy < 0){
                    dy = 0;
                }
            }
        }
        if(left){
            dx -= acc;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }else {
            if(dx < 0){
                dx += deacc;
                if(dx > 0){
                    dx=0;
                }
            }

        }
        if(right){
            dx += acc;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }else {
            if(dx > 0){
                dx -= deacc;
                if(dx < 0){
                    dx=0;
                }
            }

        }

    }
    public void update(Enemy enemy){
        super.update();
        if(attack && hitBounds.collides(enemy.getBounds()))
        {
            enemy.health -= 1;
            System.out.println("Tinta lovita");
            if(enemy.currentAnimation == UP)
            {
                enemy.dy += 2;
            }
            if(enemy.currentAnimation == LEFT)
            {
                enemy.dx += 2;
            }
            if(enemy.currentAnimation == RIGHT)
            {
                enemy.dx -= 2;
            }
            if(enemy.currentAnimation == DOWN)
            {
                enemy.dy -= 2;
            }
        }
        move();
        if(!tc.collisionTile(dx,0))
        {
            pos.x += dx;
            xCol=false;

        }else {
            xCol=true;
        }
        if(!tc.collisionTile(0,dy))
        {

            pos.y += dy;
            yCol=false;
        }else {
            yCol=true;
        }
        xCol=false;
        yCol=false;



    }
    public void input(MouseHandler mouse, KeyHandler key){
        if(mouse.getButton() == 1)
        {
            System.out.println("Player: " + pos.x + ", " + pos.y);
        }

        if(key.up.down){
            up=true;
        }
        else {
            up=false;
        }
        if(key.down.down){
            down=true;
        }
        else {
            down=false;
        }
        if(key.left.down){
            left=true;
        }
        else
        {
            left=false;
        }
        if(key.right.down){
            right=true;
        }
        else {
            right=false;
        }
        if(key.attack.down){
            attack=true;
        }
        else {
            attack=false;
        }
        if(up && down){
            up=false;
            down= false;
        }
        if(right && left)
        {
            left=false;
            right=false;
        }

    }
    @Override
    public void animate(){
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
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.green);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), ((int) (pos.getWorldVar().y + bounds.getYOffset())), (int) bounds.getWidth(), (int) bounds.getHeight());
        if(attack){
            g.setColor(Color.red);
            g.drawRect((int) (hitBounds.getPos().getWorldVar().x + hitBounds.getXOffset()), (int) (hitBounds.getPos().getWorldVar().y + hitBounds.getYOffset()),(int) hitBounds.getWidth(),(int)  hitBounds.getHeight());
        }
        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size,size, null);
    }


}
