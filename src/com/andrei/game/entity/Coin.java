package com.andrei.game.entity;

import com.andrei.game.graphics.Sprite;
import com.andrei.game.util.AABB;
import com.andrei.game.util.Camera;
import com.andrei.game.util.TileCollision;
import com.andrei.game.util.Vector2f;

import java.awt.*;

public class Coin extends Entity {
    protected boolean isVisible;
    protected boolean picked;
    public boolean once;


    Coin(Camera cam,Sprite sprite, Vector2f p, int size)
    {
        super(sprite,p,size);
        bounds.setHeight(11);
        bounds.setWidth(11);
        bounds.setYOffset(44);
        bounds.setXOffset(32);
        isVisible=false;
        picked=false;
        once = false;
        tc=new TileCollision(this);

    }
    @Override
    public void animate() {
    }
    public void setVisibleTrue(){
        isVisible=true;
    }
    public boolean isVisible(){return isVisible;}
    public boolean isPicked(){return picked;}
    public void setPos(Vector2f po){
        pos=po;
    }
    public void updateBounds(AABB bbox){
        this.bounds.getPos().x = bbox.getPos().x;
        this.bounds.getPos().y = bbox.getPos().y;
    }

    public boolean coinCollision(Player player){
        if(bounds.collides(player.getBounds()))
            return true;
        return false;
    }
    public void update(){
        super.update();
    }

    public void render(Graphics2D g)
    {
        if(isVisible)
        {
            g.drawImage(sprite.getSpriteSheet(),(int) (pos.getWorldVar().x+bounds.getXOffset())-2,(int) (pos.getWorldVar().y+bounds.getYOffset()-3),null);
            g.setColor(Color.cyan);
            g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), ((int) (pos.getWorldVar().y + bounds.getYOffset())), (int) bounds.getWidth(), (int) bounds.getHeight());
//            g.setColor(Color.RED);
//            g.drawRect((int) (pos.x), (int) (pos.y ), (int) bounds.getWidth(), (int) bounds.getHeight());

        }
    }

}
