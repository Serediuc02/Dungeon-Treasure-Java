package com.andrei.game.util;

import com.andrei.game.GamePanel;
import com.andrei.game.entity.Entity;
import com.andrei.game.states.PlayState;
import com.andrei.game.util.AABB;
import com.andrei.game.util.Vector2f;

import java.awt.*;

public class Camera {
    private AABB colisionCam;
    private AABB bounds;
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;

    private float dx;
    private float dy;
    private float maxSpeed = 4f;
    private float acc = 1f;
    private float deacc = 4f;

    private int widthLimit;
    private int heightLimit;
    private Entity e;
    public Camera(AABB colisionCam){

        this.colisionCam=colisionCam;
        float x = colisionCam.getPos().x;
        float y = colisionCam.getPos().y;
        this.bounds = new AABB(new Vector2f(x,y),(int) colisionCam.getWidth(),(int) colisionCam.getHeight());
    }
    public void setLimit(int widthLim, int heightLim){
        this.widthLimit = widthLim;
        this.heightLimit = heightLim;
    }


    public Vector2f getPos() {
        return colisionCam.getPos();
    }
    public AABB getBoundsOnScreen(){
        return bounds;
    }

    public void setMaxSpeed(int maxSpeed){
        this.maxSpeed = maxSpeed;
    }
    public AABB getBounds(){
        return colisionCam;
    }


    public void update(){
        move();
        if(!e.xCol)
        {
            if((e.getBounds().getPos().getWorldVar().x + e.getDx()) < Vector2f.getWorldVarX(widthLimit - colisionCam.getWidth() / 2) - 64 &&
                    (e.getBounds().getPos().getWorldVar().x + e.getDx()) > Vector2f.getWorldVarX(GamePanel.width / 2 - 64 ))
            {
                PlayState.map.x += dx;
                bounds.getPos().x += dx;
            }
        }
        if(!e.yCol)
        {
            if((e.getBounds().getPos().getWorldVar().y + e.getDy()) < Vector2f.getWorldVarY(heightLimit - colisionCam.getHeight() / 2) - 64 &&
                    (e.getBounds().getPos().getWorldVar().y + e.getDy()) > Vector2f.getWorldVarY(GamePanel.height / 2 -64 ))
            {

                PlayState.map.y += dy;
                bounds.getPos().y += dy;
            }

        }
    }
    private void move(){
        if (up) {
            dy -= acc;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        if (down) {
            dy += acc;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }
        if (left) {
            dx -= acc;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (right) {
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    public void target(Entity e){
        this.e=e;
        if(e!= null)
        {
            acc=e.getAcc();
            deacc=e.getDeacc();
            maxSpeed=e.getMaxSpeed();
        }
        else {
            acc=3;
            deacc=0.3f;
            maxSpeed=8f;
        }

    }

    ////

    public void input(MouseHandler mouse, KeyHandler key){
        if(e == null)
        {
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
            if(up && down){
                up=false;
                down= false;
            }
            if(right && left)
            {
                left=false;
                right=false;
            }
        }else {
            if(PlayState.map.y + GamePanel.height / 2 - e.getSize() / 2 + dy > e.getBounds().getPos().y + e.getDy() + 2 )
            {
                up = true;
                down=false;
            } else if (PlayState.map.y + GamePanel.height / 2 - e.getSize() / 2 + dy < e.getBounds().getPos().y + e.getDy() - 2)
            {
                down=true;
                up=false;
            }else {
                dy=0;
                up=false;
                down=false;
            }

            if(PlayState.map.x + GamePanel.width / 2 - e.getSize() / 2 + dx > e.getBounds().getPos().x + e.getDx() + 2 )
            {
                left = true;
                right=false;
            } else if (PlayState.map.x + GamePanel.width / 2 - e.getSize() / 2 + dx < e.getBounds().getPos().x + e.getDx() - 2)
            {
                right=true;
                left=false;
            }else {
                dx=0;
                right=false;
                left=false;
            }
        }


    }

    public void render(Graphics g)
    {

//        g.setColor(Color.blue);
//        g.drawRect((int) colisionCam.getPos().x,(int) colisionCam.getPos().y,(int) colisionCam.getWidth(),(int) colisionCam.getHeight());
//        g.setColor(Color.MAGENTA);
//        g.drawLine(GamePanel.width / 2, 0, GamePanel.width/2 ,GamePanel.height);
//        g.drawLine(0, GamePanel.height / 2, GamePanel.width ,GamePanel.height/2);
    }

}
