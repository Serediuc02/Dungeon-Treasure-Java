package com.andrei.game.util;

import com.andrei.game.util.Vector2f;
import com.andrei.game.tiles.TileMapObj;
import com.andrei.game.tiles.blocks.Block;
import com.andrei.game.tiles.blocks.HoleBlock;
import com.andrei.game.entity.Entity;
import com.andrei.game.graphics.Sprite;
public class TileCollision {
    private Entity e;
    private int tileId;
    public TileCollision(Entity e){
        this.e=e;
    }
    public boolean collisionTile(float ax, float ay){

        for(int corner = 0; corner < 4; corner++)
        {
            //dala in care se afla jucatorul
            int xt= (int) ((e.getBounds().getPos().x + ax) + (corner % 2) * e.getBounds().getWidth() + e.getBounds().getXOffset()) / 64;
            int yt= (int) ((e.getBounds().getPos().y + ay) + (corner / 2) * e.getBounds().getHeight()  + e.getBounds().getYOffset()) / 64;

            if(TileMapObj.event_blocks[xt + (yt * TileMapObj.height)] instanceof Block){
                Block block = TileMapObj.event_blocks[xt + (yt * TileMapObj.height)];
                if(block instanceof HoleBlock){
                    return collisionHole(ax,ay,xt,yt,block);
                }
                return block.update(e.getBounds());
            }
        }
        return false;
    }

    private boolean collisionHole(float ax, float ay,float xt,float yt,Block block){
        int nextXT = (int) ( (((e.getBounds().getPos().x + ax)  + e.getBounds().getXOffset()) / 64)+ e.getBounds().getWidth() / 64);
        int nextYT = (int) ( (((e.getBounds().getPos().y + ay)  + e.getBounds().getYOffset()) / 64)+ e.getBounds().getHeight() / 64);

        if(block.isInside(e.getBounds())){
            //
            System.out.println("Am cazut");
            return false;
        }
        else if((nextYT == yt+1) ||(nextXT == xt+1) || (nextYT == yt - 1) || (nextXT == xt - 1)){
            if(TileMapObj.event_blocks[nextXT + (nextYT *TileMapObj.height)] instanceof HoleBlock ){
                if(e.getBounds().getPos().x > block.getPos().x){
                    System.out.println("Am cazut");
                }
                return false;
            }
        }
        return false;
    }
}
