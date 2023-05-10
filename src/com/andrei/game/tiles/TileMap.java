package com.andrei.game.tiles;

import com.andrei.game.util.AABB;


import java.awt.*;

public abstract class TileMap {
    public abstract void render(Graphics2D g, AABB cam);

}
