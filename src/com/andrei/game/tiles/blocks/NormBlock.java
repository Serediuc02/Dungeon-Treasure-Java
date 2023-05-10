package com.andrei.game.tiles.blocks;
import com.andrei.game.util.AABB;
import com.andrei.game.util.Vector2f;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class NormBlock extends Block{
    public NormBlock(BufferedImage img, Vector2f pos, int w, int h){
        super(img,pos,w,h);
    }
    public boolean isInside(AABB p){
        return false;
    }
    @Override
    public boolean update(AABB p) {
        return false;
    }
    public void render(Graphics2D g){
        super.render(g);
    }
}
