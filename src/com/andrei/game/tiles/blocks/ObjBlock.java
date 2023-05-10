package com.andrei.game.tiles.blocks;

import com.andrei.game.util.AABB;
import com.andrei.game.util.Vector2f;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ObjBlock extends Block{

    public ObjBlock(BufferedImage img, Vector2f pos, int w, int h){
        super(img,pos,w,h);
    }
    @Override
    public boolean update(AABB p) {
        return true;
    }
    public boolean isInside(AABB p){
        return false;
    }
    public void render(Graphics2D g){
        super.render(g);
        g.setColor(Color.white);
        g.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y,w,h);
    }


}
