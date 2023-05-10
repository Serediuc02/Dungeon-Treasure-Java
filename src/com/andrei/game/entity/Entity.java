package com.andrei.game.entity;
import com.andrei.game.graphics.Animation;
import com.andrei.game.graphics.Sprite;
import com.andrei.game.util.AABB;

import com.andrei.game.util.Camera;
import com.andrei.game.util.TileCollision;
import com.andrei.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected final int UP = 3;
    protected final int DOWN = 2;
    protected final int RIGHT = 0;
    protected final int LEFT = 1;

    protected Animation ani;

    protected Sprite sprite;
    protected Vector2f pos;

    protected int size;
    protected int currentAnimation;
    protected int lastAni;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected int attackSpeed;
    protected int attackDuration;
    protected float dx;
    protected float dy;
    protected float maxSpeed =4f;
    protected float acc = 3f; //accelerare
    protected float deacc = 0.3f;
    protected AABB hitBounds;
    protected AABB bounds;
    public boolean xCol=false;
    public boolean yCol=false;
    protected TileCollision tc;

    public Vector2f getPos() {
        return pos;
    }
    public Entity(Sprite sprite, Vector2f origin, int size){
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        bounds = new AABB(origin,size,size);
        hitBounds = new AABB(origin, size, size);

        hitBounds.setXOffset(size/2);
        hitBounds.setYOffset(size/2);

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
        tc= new TileCollision(this);
    }
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }
    public void setSize(int i){
        size = i;
    }
    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public void setAcc(float f){
        acc = f;
    }
    public void setDeacc(float f){
        deacc = f;
    }
    public AABB getBounds(){
        return bounds;
    }
    public int getSize(){
        return size;
    }
    public float getDx(){return dx;}
    public float getDy(){return dy;}
    public Animation getAnimation(){
        return ani;
    }
    public void setAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);

    }
    public float getDeacc(){return deacc;}
    public float getAcc(){return acc;}
    public float getMaxSpeed(){return maxSpeed;}
    abstract public void animate();
    private void setHitBoxDirection(){
        if(up){
            hitBounds.setYOffset(-size / 2 + 80);
            hitBounds.setXOffset(31);
        }
        else if(down){
            hitBounds.setXOffset(31);
            hitBounds.setYOffset(size / 2 + 35);
        }
        else if(left){
            hitBounds.setXOffset(-size / 2 + 40 );
            hitBounds.setYOffset(46);
        }
        else if(right){
            hitBounds.setXOffset(size / 2 + 21);
            hitBounds.setYOffset(46);
        }
    }
    public void update(){
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public abstract void render(Graphics2D g);
    //public void input(KeyHandler key, MouseHandler mouse){}

}
