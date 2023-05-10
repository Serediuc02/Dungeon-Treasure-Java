package com.andrei.game.util;
import com.andrei.game.entity.Entity;
//Axis-Aligned Bounding Box

public class AABB {
    private Vector2f pos;
    private float xOffset = 0;
    private float yOffset = 0;
    private float w;
    private float h;
    private float r;
    private int size;
    private float surfaceArea;

    public AABB(Vector2f pos,int w,int h){
        this.pos = pos;
        this.w = w;
        this.h = h;
        this.surfaceArea = w*h;
        size = Math.max(w,h);
    }
    public AABB(Vector2f pos, int r){
        this.pos = pos;
        this.r = r;
        this.surfaceArea = (float) Math.PI * (r * r);
        size = r;
    }

    public Vector2f getPos() {
        return pos;
    }
    public float getRadius()
    {
        return r;
    }
    public float getWidth(){
        return w;
    }
    public float getHeight(){
        return h;
    }
    public float getSize(){
        return size;
    }

    public void setBox(Vector2f pos, int w, int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w,h);
    }

    public void setCircle(Vector2f pos,int r){
        this.pos = pos;
        this.r= r;
        size=r;
    }

    public void setWidth(float f){
        w=f;
    }
    public void setHeight(float f){
        h=f;
    }
    public void setXOffset(float f){
        xOffset=f;
    }
    public void setYOffset(float f){
        yOffset=f;
    }
    public float getXOffset(){return xOffset;}
    public float getYOffset(){return  yOffset;}

    public boolean collides(float dx, float dy, AABB bBox) {
        float ax = ((pos.x + (xOffset)) + (this.w / 2)) + dx;
        float ay = ((pos.y + (yOffset)) + (this.h / 2)) + dy;
        float bx = ((bBox.getPos().x + (bBox.getXOffset())) + (bBox.getWidth() / 2));
        float by = ((bBox.getPos().y + (bBox.getYOffset())) + (bBox.getHeight() / 2));

        if (Math.abs(ax - bx) < (this.w / 2) + (bBox.getWidth() / 2)) {
            if (Math.abs(ay - by) < (this.h / 2) + (bBox.getHeight() / 2)) {
                return true;
            }
        }
        return false;
    }
    public boolean collides(AABB bBox) {
        return collides(0, 0, bBox);
    }

    //cerc atentie inamic
    public boolean colCircleBox(AABB aBox){
        float dx= Math.max(aBox.getPos().getWorldVar().x +aBox.getXOffset(), Math.min(pos.getWorldVar().x + (r/2), aBox.getPos().getWorldVar().x + aBox.getXOffset() + aBox.getWidth()));
        float dy= Math.max(aBox.getPos().getWorldVar().y +aBox.getXOffset(), Math.min(pos.getWorldVar().y + (r/2), aBox.getPos().getWorldVar().y + aBox.getYOffset() + aBox.getHeight()));

        dx= pos.getWorldVar().x + (r/2) - dx;
        dy= pos.getWorldVar().y + (r/2) - dy;

        if(Math.sqrt(dx*dx +dy *dy) < r/2) {
            return true;
        }
        return false;
    }
}
