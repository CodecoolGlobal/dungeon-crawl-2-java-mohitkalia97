package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    BOTTOMLEFT("bottomLeft"),
    BOTTOMRIGHT("bottomRight"),
    TOPLEFT("topLeft"),
    TOPRIGHT("topRight"),
    BOTTOMWALL("bottomWall"),
    TOPWALL("topWall"),
    LEFTWALL("leftWall"),
    RIGHTWALL("rightWall"),
    RABBLE("rabble"),
    CRYSTAL("crystal"),
    FLAME("flame"),
    GRASS("grass"),
    TREE("tree");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
