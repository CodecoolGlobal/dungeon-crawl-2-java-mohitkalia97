package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("exclamation", new Tile(19, 25));
        tileMap.put("gork", new Tile(18, 24));
        tileMap.put("mork", new Tile(1, 15));
        tileMap.put("bottomLeft", new Tile(18, 2));
        tileMap.put("bottomRight", new Tile(20, 2));
        tileMap.put("topLeft", new Tile(18, 0));
        tileMap.put("topRight", new Tile(20, 0));
        tileMap.put("bottomWall", new Tile(19, 2));
        tileMap.put("topWall", new Tile(19, 0));
        tileMap.put("leftWall", new Tile(18, 1));
        tileMap.put("rightWall", new Tile(20, 1));
        tileMap.put("rabble", new Tile(19, 1));
        tileMap.put("crystal", new Tile(17, 22));
        tileMap.put("flame", new Tile(15, 10));
        tileMap.put("grass", new Tile(5, 0));
        tileMap.put("tree", new Tile(1, 1));
        tileMap.put("treeAGAIN", new Tile(1, 1));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
