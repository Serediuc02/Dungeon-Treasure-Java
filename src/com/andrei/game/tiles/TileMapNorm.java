package com.andrei.game.tiles;

import com.andrei.game.graphics.Sprite;
import com.andrei.game.tiles.blocks.NormBlock;
import java.awt.Graphics2D;

import com.andrei.game.tiles.blocks.Block;
import com.andrei.game.util.AABB;
import com.andrei.game.util.Vector2f;

public class TileMapNorm extends TileMap{
    private Block[] blocks;
    private int tileHeight;
    private int tileWidth;
    private int height;
    private int width;

    public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
            blocks = new Block[(width * height)+64];
            this.tileWidth =tileWidth;
            this.tileHeight=tileHeight;
            this.height=height;
            this.width=width;
            String[] block = data.split(",");
            for(int i=0; i < (width * height); i++)
            {
                int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
                if(temp != 0)
                {
                    blocks[i] = new NormBlock(sprite.getSprite((int) ((temp-1) % tileColumns), (int) ((temp-1)) / tileColumns), new Vector2f((int) (i % width) * tileWidth,(int) (i/height) * tileHeight), tileWidth, tileHeight);
                }
            }
    }
    public void render(Graphics2D g, AABB cam){
        int x = (int) ((cam.getPos().getCamVar().x) / tileWidth);
        int y = (int) ((cam.getPos().getCamVar().y) / tileHeight);
        for(int i = x; i < x + (cam.getWidth() / tileWidth); i++)
        {
            for(int j = y; j < y + (cam.getHeight() / tileHeight); j++)
            {
                if(blocks[i +(j * height)] != null)
                {
                    blocks[i+ (j * height)].render(g);
                }

            }
        }
    }
}
